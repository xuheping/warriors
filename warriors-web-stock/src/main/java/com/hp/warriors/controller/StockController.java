package com.hp.warriors.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hp.warriors.entity.stock.MainInflows;
import com.hp.warriors.entity.stock.SnowProfit;
import com.hp.warriors.entity.stock.Stock;
import com.hp.warriors.mapper.stock.MainInflowsMapper;
import com.hp.warriors.mapper.stock.SnowProfitMapper;
import com.hp.warriors.mapper.stock.StockMapper;
import com.hp.warriors.utils.DateUtil;
import com.hp.warriors.utils.HttpUtils;
import com.hp.warriors.utils.RestTemplateUtils;
import com.hp.warriors.wrapper.NEStockResp;
import com.hp.warriors.wrapper.SnowResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {

    private static String SNOW_DAY_K = "https://stock.xueqiu.com/v5/stock/chart/kline.json?symbol=%s&begin=1612749850161&period=day&type=before&count=-284&indicator=kline,pe,pb,ps,pcf,market_capital,agt,ggt,balance";

    @Autowired
    private MainInflowsMapper mainInflowsMapper;

    @Autowired
    private SnowProfitMapper snowProfitMapper;

    @Autowired
    private StockMapper stockMapper;

    ExecutorService executors = Executors.newFixedThreadPool(8);

    @GetMapping("/initStock")
    public JSONObject initStock(Integer type) {
        switch (type) {
            case 1://净利润
                snowProfit();
                break;
            case 2://价格表
                netEastStock();
                break;
            case 3://资金流入
                mainInflows();
                break;
            case 4:
                pressureSupport("");
                break;
            case 5://压力位
                pressureSupport();
                break;
        }
        return null;
    }

    //http://quotes.money.163.com/old/#query=EQA&DataType=HS_RANK&sort=PERCENT&order=desc&count=24&page=0
    private static String NETEAST_STOCK = "http://quotes.money.163.com/hs/service/diyrank.php?host=http%3A%2F%2Fquotes.money.163.com%2Fhs%2Fservice%2Fdiyrank.php&page=0&query=STYPE%3AEQA&fields=NO%2CSYMBOL%2CNAME%2CPRICE%2CPERCENT%2CUPDOWN%2CFIVE_MINUTE%2COPEN%2CYESTCLOSE%2CHIGH%2CLOW%2CVOLUME%2CTURNOVER%2CHS%2CLB%2CWB%2CZF%2CPE%2CMCAP%2CTCAP%2CMFSUM%2CMFRATIO.MFRATIO2%2CMFRATIO.MFRATIO10%2CSNAME%2CCODE%2CANNOUNMT%2CUVSNEWS&sort=PERCENT&order=desc&count=1000&type=query";

    public void netEastStock() {
        try {
            AtomicInteger ai = new AtomicInteger(0);
            List<String> list = FileUtils.readLines(new File("/Users/heping/work/warriors/warriors-web-atlanta/src/main/resources/NetEastStockInfo.txt"));
            ExecutorService executors = Executors.newFixedThreadPool(8);
            for (String line : list) {
                executors.submit(() -> {
                    NEStockResp neStockResp = JSON.parseObject(line, NEStockResp.class);
                    if (CollectionUtils.isEmpty(neStockResp.getList())) {
                        return;
                    }
                    neStockResp.getList().forEach(NEstock -> {
                        Stock stock = stockMapper.get(NEstock.getSYMBOL());
                        if (Objects.isNull(stock)) {
                            stock = new Stock();
                        }
                        stock.setCode(NEstock.getSYMBOL());
                        stock.setName(NEstock.getNAME());
                        stock.setMarketValue(NEstock.getTCAP());
                        stock.setPrice(NEstock.getPRICE());
                        stock.setPe(NEstock.getPE());
                        if (Objects.isNull(stock.getId())) {
                            stockMapper.insert(stock);
                            log.info("更新数据第{}条,stock code is {}", ai.getAndIncrement(), stock.getCode());
                        } else {
                            stockMapper.update(stock);
                            log.info("更新数据第{}条,stock code is {}", ai.getAndIncrement(), stock.getCode());
                        }
                    });
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //利润表   https://xueqiu.com/snowman/S/SZ002555/detail#/GSLRB
    private static String SNOW_SEASON_EARN = "https://stock.xueqiu.com/v5/stock/finance/cn/income.json?symbol=SZ002555&type=all&is_detail=true&count=5&timestamp=1614008852146";

    public void snowProfit() {
        List<Stock> stocks = stockMapper.listAll();
        for (Stock stock : stocks) {
            executors.submit(() -> {
                String url = "https://stock.xueqiu.com/v5/stock/finance/cn/income.json/?symbol=" + stock.getStrCode() + "&type=all&is_detail=true&count=20&timestamp=1612712866008";
                ResponseEntity<String> responseEntity = RestTemplateUtils.get(url, String.class);
                String result = responseEntity.getBody();
                SnowResult snowResult = JSON.parseObject(result, SnowResult.class);
                if (Objects.isNull(snowResult) || Objects.isNull(snowResult.getData()) || CollectionUtils.isEmpty(snowResult.getData().getList())) {
                    return;
                }
                snowResult.getData().getList().forEach(profitWrapper -> {
                    try {
                        SnowProfit snowProfit = new SnowProfit();
                        snowProfit.setCode(stock.getCode());
                        snowProfit.setGrossRevenue(profitWrapper.getTotal_revenue()[0]);
                        snowProfit.setNetProfit(profitWrapper.getNet_profit()[0]);
                        snowProfit.setNonNetProfit(profitWrapper.getNet_profit_after_nrgal_atsolc()[0]);
                        snowProfit.setReportDate(new Date(profitWrapper.getReport_date()));
                        snowProfit.setReportName(profitWrapper.getReport_name());
                        snowProfit.setResearchCost(profitWrapper.getRad_cost()[0]);
                        snowProfit.setSeasonDate(DateUtil.sdfFormatDate(new Date(profitWrapper.getReport_date())));
                        SnowProfit existProfit = snowProfitMapper.getByCodeAndReportDate(snowProfit.getCode(), snowProfit.getReportDate());
                        if (Objects.nonNull(existProfit)) {
                            snowProfit.setId(existProfit.getId());
                            snowProfitMapper.update(snowProfit);
                        } else {
                            snowProfitMapper.insert(snowProfit);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });
        }
    }

    //主力接口
    private static String MAIN_INFLOWS = "http://quotes.money.163.com/trade/lszjlx_002555,0.html";

    public void mainInflows() {
        List<Stock> stocks = stockMapper.listAll();
        for (Stock stock : stocks) {
            executors.submit(() -> {
                String url = "http://quotes.money.163.com/trade/lszjlx_" + stock.getCode() + ",0.html";
                ResponseEntity<String> responseEntity = RestTemplateUtils.get(url, String.class);
                String html = responseEntity.getBody();
                /**
                 * 下面是Jsoup展现自我的平台
                 */
                Document document = Jsoup.parse(html);
                Element table = document.getElementsByClass("table_bg001").first();
                Elements trElements = table.getElementsByTag("tr");
                for (Element trElement : trElements) {
                    try {
                        Elements tdElements = trElement.getElementsByTag("td");
                        if (CollectionUtils.isEmpty(tdElements)) {
                            continue;
                        }

                        MainInflows mainInflows = new MainInflows();
                        mainInflows.setCode(stock.getCode());

                        Date createDate = DateUtil.sdfParseDate(tdElements.first().text());
                        mainInflows.setCreateDate(createDate);

                        String changeHand = tdElements.get(3).text().replaceAll("%", "");
                        mainInflows.setChangHand(new BigDecimal(changeHand));

                        String last = tdElements.get(tdElements.size() - 1).text();
                        last = StringUtils.leftPad(last.replaceAll(",", ""), 4, '0');
                        mainInflows.setNetInflow(new BigDecimal(last));

                        String price = tdElements.get(1).text();
                        mainInflows.setPrice(new BigDecimal(price));

                        String riseFall = tdElements.get(2).text().replaceAll("%", "");
                        mainInflows.setRiseFall(new BigDecimal(riseFall));

                        MainInflows exist = mainInflowsMapper.getByCodeAndDate(stock.getCode(), createDate);
                        if (Objects.isNull(exist)) {
                            mainInflowsMapper.insert(mainInflows);
                            continue;
                        }
                        mainInflows.setId(exist.getId());
                        mainInflowsMapper.update(mainInflows);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static final String PRESSURE_SUPPORT = "http://www.iwencai.com/stockpick/search?typed=1&preParams=&ts=1&f=1&qs=result_rewrite&selfsectsn=&querytype=stock&searchfilter=&tid=stockpick&w=002555";

    public void pressureSupport(String code) {
        String url = "";
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        String html = "";
        try {
            html = httpUtils.getHtmlPageResponse(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 下面是Jsoup展现自我的平台
         */
        Document document = Jsoup.parse(html);
        Element table = document.getElementsByClass("pressuresupport").first();
        Elements trElements = table.getElementsByTag("tr");
        for (Element trElement : trElements) {
            try {
                Elements tdElements = trElement.getElementsByTag("td");
                if (CollectionUtils.isEmpty(tdElements)) {
                    continue;
                }
                Stock stock = stockMapper.get(code);
                if (Objects.isNull(stock)) {
                    continue;
                }
                System.out.println("support:" + tdElements.get(1).text());
                System.out.println("pressure:" + tdElements.get(2).text());
                System.out.println("win:" + tdElements.get(3).text());
                System.out.println("sale:" + tdElements.get(4).text());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static final String PRESSURE_SUPPORT_HOUTAI = "http://www.iwencai.com/diag/block-detail?pid=10331&codes=002555&codeType=stock&info=%7B%22view%22%3A%7B%22nolazy%22%3A1%2C%22parseArr%22%3A%7B%22_v%22%3A%22new%22%2C%22dateRange%22%3A%5B%5D%2C%22staying%22%3A%5B%5D%2C%22queryCompare%22%3A%5B%5D%2C%22comparesOfIndex%22%3A%5B%5D%7D%2C%22asyncParams%22%3A%7B%22tid%22%3A137%7D%7D%7D";

    public void pressureSupport() {
        List<Stock> stocks = stockMapper.listAll();
        for (Stock stock : stocks) {
            if (!Objects.equals(stock.getDealFlag(), 1)) {
                executors.submit(() -> {
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    try {
                        List<String> stringList = FileUtils.readLines(new File("/Users/heping/work/warriors/warriors-web-atlanta/src/main/resources/iwencai.txt"));
                        //創建一個httpGet方法
                        HttpGet httpGet = new HttpGet("http://www.iwencai.com/diag/block-detail?codeType=stock&codes=" + stock.getCode() + "&info=%7B%22view%22%3A%7B%22nolazy%22%3A1%2C%22parseArr%22%3A%7B%22_v%22%3A%22new%22%2C%22dateRange%22%3A%5B%2220210216%22%2C%2220210216%22%5D%2C%22staying%22%3A%5B%5D%2C%22queryCompare%22%3A%5B%5D%2C%22comparesOfIndex%22%3A%5B%5D%7D%7D%7D&logid=b5ed6991070135dbd033480282d8c35e&pid=10331&w=002555&callback=jQuery183013743300806346848_1613440156893&_=1613440158343");
                        httpGet.setHeader("Cookie", stringList.get(0));
                        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36");
                        CloseableHttpResponse response = httpClient.execute(httpGet);
                        HttpEntity entity = response.getEntity();
                        if (!Objects.equals(response.getStatusLine().getStatusCode(), 200)) {
                            return;
                        }
                        String resultContent = EntityUtils.toString(entity, "UTF-8");
                        resultContent = resultContent.substring(resultContent.indexOf("(") + 1);
                        resultContent = resultContent.substring(0, resultContent.indexOf(")"));
                        JSONObject json = JSON.parseObject(resultContent);

                        if (Objects.nonNull(json.getJSONObject("data")) && Objects.nonNull(json.getJSONObject("data").getJSONObject("data"))
                                && Objects.nonNull(json.getJSONObject("data").getJSONObject("data").getJSONObject("result"))) {
                            JSONObject result = json.getJSONObject("data").getJSONObject("data").getJSONObject("result");

                            stock.setPressure(result.getBigDecimal("pressure"));
                            stock.setStopProfit(result.getBigDecimal("stopProfit"));
                            stock.setSupport(result.getBigDecimal("support"));
                            stock.setStopLoss(result.getBigDecimal("stopLoss"));

                            stock.setSupportDiffPer(result.getBigDecimal("supportDiffPer"));
                            stock.setStopProfitDiffPer(result.getBigDecimal("stopProfitDiffPer"));
                            stock.setPressureDiffPer(result.getBigDecimal("pressureDiffPer"));
                            stock.setStopLossDiffPer(result.getBigDecimal("stopLossDiffPer"));
                        }
                        stock.setDealFlag(1);
                        stockMapper.update(stock);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

}
