package com.hp.warriors.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hp.warriors.entity.stock.ProfitAlgorithm;
import com.hp.warriors.entity.stock.Stock;
import com.hp.warriors.mapper.stock.ProfitAlgorithmMapper;
import com.hp.warriors.mapper.stock.StockMapper;
import com.hp.warriors.utils.DateUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/profit/algorithm")
public class ProfitAlgorithmController {

    @Autowired
    private ProfitAlgorithmMapper profitAlgorithmMapper;

    @Autowired
    private StockMapper stockMapper;

    private static final String price = "https://stock.xueqiu.com/v5/stock/chart/kline.json?symbol=%s&begin=1615398456012&period=day&type=before&count=%s&indicator=kline,pe,pb,ps,pcf,market_capital,agt,ggt,balance";

    private static final String priceInfo = "{\"data\":{\"symbol\":\"SZ300003\",\"column\":[\"timestamp\",\"volume\",\"open\",\"high\",\"low\",\"close\",\"chg\",\"percent\",\"turnoverrate\",\"amount\",\"volume_post\",\"amount_post\",\"pe\",\"pb\",\"ps\",\"pcf\",\"market_capital\",\"balance\",\"hold_volume_cn\",\"hold_ratio_cn\",\"net_volume_cn\",\"hold_volume_hk\",\"hold_ratio_hk\",\"net_volume_hk\"],\"item\":[[1608739200000,27603741,27.49,27.59,26.5,26.64,-0.84,-3.06,1.79,7.43441009E8,6500,173160.0,23.0443,4.7823,5.79,20.7905,4.807404095688E10,2.053994014E9,null,null,null,null,null,null],[1608825600000,20375072,26.68,26.68,26.08,26.3,-0.34,-1.28,1.32,5.36521125E8,3200,84160.0,22.7502,4.7212,5.7161,20.5251,4.74604833771E10,2.030653271E9,null,null,null,null,null,null],[1609084800000,19800037,26.4,26.95,26.26,26.48,0.18,0.68,1.29,5.26468698E8,500,13240.0,22.9059,4.7535,5.7552,20.6656,4.778530797816E10,2.023053066E9,62636992,3.47,-115224,null,null,null],[1609171200000,18320570,26.3,27.06,26.09,26.84,0.36,1.36,1.19,4.88639922E8,600,16104.0,23.2173,4.8182,5.8335,20.9466,4.843495718028E10,2.019504416E9,63582577,3.52,945585,null,null,null],[1609257600000,18862731,26.55,27.18,26.4,27.03,0.19,0.71,1.22,5.05179604E8,1200,32436.0,23.3817,4.8523,5.8748,21.0948,4.877782759251E10,2.032186712E9,63686488,3.52,103911,null,null,null],[1609344000000,22035085,26.9,27.49,26.79,27.18,0.15,0.55,1.42,5.96630992E8,200,5436.0,23.5114,4.8792,5.9074,21.2119,4.904851476006E10,2.028245517E9,64595628,3.57,909140,null,null,null],[1609689600000,31137485,27.12,27.24,26.59,26.77,-0.41,-1.51,2.02,8.34707605E8,5600,149912.0,23.1568,4.8056,5.8182,20.8919,4.830863650209E10,2.024608431E9,65565969,3.63,970341,null,null,null],[1609776000000,42717427,26.7,27.83,26.57,27.76,0.99,3.7,2.75,1.160245144E9,1400,38864.0,24.0132,4.9833,6.0334,21.6645,5.009517180792E10,2.034337235E9,null,null,null,null,null,null],[1609862400000,31804450,27.82,27.97,27.2,27.37,-0.39,-1.4,2.07,8.74725439E8,900,24633.0,23.6758,4.9133,5.9487,21.3602,4.939138517229E10,2.005879487E9,59491651,3.29,-6074318,null,null,null],[1609948800000,29444970,27.25,27.3,26.3,26.59,-0.78,-2.85,1.91,7.83643759E8,2300,61157.0,23.0011,4.7733,5.7791,20.7514,4.798381190103E10,2.035889824E9,55265105,3.06,-4226546,null,null,null],[1610035200000,49739921,26.59,28.58,26.59,28.16,1.57,5.9,3.22,1.385131087E9,4900,137984.0,24.3592,5.0551,6.1204,21.9767,5.081700425472E10,2.029542238E9,58674843,3.25,3409738,null,null,null],[1610294400000,38287715,28.31,28.9,27.88,28.03,-0.13,-0.46,2.48,1.084859234E9,2300,64469.0,24.2467,5.0318,6.0921,21.8753,5.058240870951E10,1.977017289E9,60295691,3.34,1620848,null,null,null],[1610380800000,39209122,28.01,29.16,27.93,28.69,0.66,2.35,2.54,1.124693676E9,1400,40166.0,24.8176,5.1503,6.2355,22.3903,5.177343224673E10,2.023575899E9,58923419,3.26,-1372272,null,null,null],[1610467200000,28104171,28.84,28.84,28.01,28.27,-0.42,-1.46,1.82,7.94341486E8,null,null,24.4543,5.0749,6.1443,22.0626,5.101550817759E10,2.03288408E9,57651366,3.19,-1272053,null,null,null],[1610553600000,26260166,28.28,28.58,27.66,28.22,-0.05,-0.18,1.7,7.38800679E8,null,null,24.4111,5.0659,6.1334,22.0235,5.092527912174E10,2.012812254E9,57621212,3.19,-30154,null,null,null],[1610640000000,25400504,28.12,28.12,27.41,27.52,-0.7,-2.48,1.64,7.02860281E8,2200,60544.0,23.8056,4.9402,5.9813,21.4772,4.966207233984E10,2.016961744E9,56196568,3.11,-1424644,null,null,null],[1610899200000,17675749,27.4,28.04,27.11,27.69,0.17,0.62,1.15,4.88247716E8,null,null,23.9526,4.9707,6.0182,21.6099,4.996885112973E10,2.018394301E9,56458530,3.12,261962,null,null,null],[1610985600000,30988606,27.78,28.7,27.41,28.5,0.81,2.93,1.99,8.73777943E8,11100,316350.0,24.6533,5.1162,6.1942,22.2421,5.14305618345E10,2.006055084E9,56859964,3.15,401434,null,null,null],[1611072000000,29657424,28.57,28.98,28.09,28.45,-0.05,-0.18,1.92,8.45451653E8,600,17070.0,24.61,5.1072,6.1834,22.203,5.134033277865E10,2.017152118E9,56793831,3.14,-66133,null,null,null],[1611158400000,36792301,28.55,29.49,28.52,28.57,0.12,0.42,2.39,1.065065932E9,1700,48569.0,24.7138,5.1287,6.2095,22.2967,5.155688251269E10,2.044527953E9,57306144,3.17,512313,null,null,null],[1611244800000,64483289,28.57,30.17,28.35,29.96,1.39,4.87,4.17,1.906068091E9,6600,197736.0,25.9162,5.3782,6.5116,23.3815,5.406525026532E10,2.077923907E9,58082472,3.21,776328,null,null,null],[1611504000000,44113268,30.27,30.48,29.34,29.87,-0.09,-0.3,2.86,1.315181733E9,6600,197142.0,25.8384,5.3621,6.492,23.3112,5.390283796479E10,2.092428774E9,57384397,3.17,-698075,null,null,null],[1611590400000,35543966,29.9,29.9,28.53,28.61,-1.26,-4.22,2.29,1.031713978E9,1000,28610.0,24.7484,5.1359,6.2182,22.3279,5.162906575737E10,2.12496195E9,57305203,3.17,-79194,null,null,null],[1611676800000,38713161,28.47,28.76,27.55,27.8,-0.81,-2.83,2.51,1.081124894E9,100,2780.0,24.0478,4.9905,6.0421,21.6958,5.01673550526E10,2.149219528E9,57525621,3.18,220418,null,null,null],[1611763200000,42153110,27.39,27.44,26.31,26.58,-1.22,-4.39,2.74,1.131482313E9,4900,130242.0,22.9924,4.7715,5.777,20.7436,4.796576608986E10,2.108843366E9,57549864,3.18,24243,null,null,null],[1611849600000,24090495,26.84,26.97,26.12,26.9,0.32,1.2,1.55,6.410844E8,null,null,23.2692,4.8289,5.8465,20.9934,4.85432320473E10,2.074756908E9,57346352,3.17,-203512,null,null,null],[1612108800000,49106233,28.0,28.8,27.38,28.45,1.55,5.76,3.19,1.381652485E9,2900,82505.0,24.61,5.1072,6.1834,22.203,5.134033277865E10,2.123485346E9,61352855,3.39,4006503,null,null,null],[1612195200000,44426412,28.4,29.65,28.2,29.26,0.81,2.85,2.88,1.296808965E9,2100,61446.0,25.3107,5.2526,6.3594,22.8352,5.280204348342E10,2.146257566E9,63057580,3.49,1704725,null,null,null],[1612281600000,33267357,29.22,29.38,28.53,28.86,-0.4,-1.37,2.16,9.61683823E8,1400,40404.0,24.9647,5.1808,6.2725,22.523,5.208021103662E10,2.15060188E9,63529927,3.52,472347,null,null,null],[1612368000000,44813695,28.86,29.98,28.55,29.65,0.79,2.74,2.9,1.323060178E9,2600,77090.0,25.6481,5.3226,6.4442,23.1395,5.350583011905E10,2.113212819E9,65244229,3.61,1714302,null,null,null],[1612454400000,33629262,29.87,30.18,28.7,28.71,-0.94,-3.17,2.15,9.89460454E8,2300,66033.0,24.8349,5.1538,6.2399,22.4059,5.180952386907E10,2.100537914E9,65283432,3.61,39203,null,null,null],[1612713600000,60541189,29.0,31.11,29.0,30.96,2.25,7.84,3.93,1.848169361E9,2500,77400.0,26.7812,5.5578,6.7289,24.1619,5.586983138232E10,2.114964895E9,66450764,3.68,1167332,null,null,null],[1612800000000,73108615,31.12,32.97,30.58,32.23,1.27,4.1,4.75,2.347626785E9,3600,116028.0,27.8798,5.7857,7.0049,25.153,5.816164940091E10,2.081185857E9,72197816,4.0,5747052,null,null,null],[1612886400000,66138776,32.8,33.85,32.42,33.72,1.49,4.62,4.27,2.206493584E9,7800,263016.0,29.169,6.054,7.328772220373344,26.315865515733034,6.0850475265E10,2.082608842E9,81342475,4.5,9144659,null,null,null],[1613577600000,68545209,34.61,34.68,32.32,32.53,-1.19,-3.53,4.45,2.281223914E9,1928,62717.0,28.1393,5.8396,7.0701,25.3872,5.870302373601E10,2.139808227E9,92066752,5.1,10724277,null,null,null],[1613664000000,46822962,32.99,33.99,32.8,33.05,0.52,1.6,3.03,1.560704206E9,600,19830.0,28.5892,5.9329,7.1832,25.793,5.964140591685E10,2.135515666E9,91201133,5.05,-865619,null,null,null],[1613923200000,51170440,33.08,33.08,31.62,31.65,-1.4,-4.24,3.31,1.647412237E9,5200,164580.0,27.3781,5.6816,6.8789,24.7004,5.711499235305E10,2.181965119E9,92147883,5.1,946750,null,null,null],[1614009600000,31787771,31.5,32.34,31.36,31.93,0.28,0.88,2.06,1.017325938E9,null,null,27.6203,5.7319,6.9397,24.9189,5.762027506581E10,2.188445681E9,91892071,5.09,-255812,null,null,null],[1614096000000,38385266,32.03,32.88,31.7,32.05,0.12,0.38,2.49,1.239151763E9,6700,214735.0,27.7241,5.7534,6.9658,25.0126,5.783682479985E10,2.19599969E9,90588918,5.01,-1303153,null,null,null],[1614182400000,35290384,32.1,32.23,30.82,31.02,-1.03,-3.21,2.28,1.10186051E9,100,3102.0,26.8331,5.5685,6.7419,24.2087,5.597810624934E10,2.221246251E9,90944628,5.03,355710,null,null,null],[1614268800000,37168276,30.5,32.15,30.41,31.83,0.81,2.61,2.43,1.163632836E9,700,22281.0,27.5338,5.7139,6.918,24.8409,5.743981695411E10,2.182866233E9,89778571,4.97,-1166057,null,null,null],[1614528000000,29585126,31.98,32.89,31.64,31.95,0.12,0.38,1.91,9.54066063E8,null,null,27.6376,5.7355,6.9441,24.9345,5.765636668815E10,2.189630333E9,89437055,4.95,-341516,null,null,null],[1614614400000,24218128,31.95,31.95,31.01,31.08,-0.87,-2.72,1.57,7.58163432E8,3300,102564.0,26.885,5.5793,6.755,24.2555,5.608638111636E10,2.211999146E9,87998933,4.87,-1438122,null,null,null],[1614700800000,18864842,31.0,31.77,30.84,31.62,0.54,1.74,1.22,5.90942824E8,700,22134.0,27.3522,5.6762,6.8724,24.677,5.706085491954E10,2.191999484E9,87198375,4.83,-800558,null,null,null],[1614787200000,21180706,31.3,31.88,30.93,31.17,-0.45,-1.42,1.37,6.60546544E8,1100,34287.0,26.9629,5.5955,6.7746,24.3258,5.624879341689E10,2.163880573E9,86527192,4.79,-671183,null,null,null],[1614873600000,23482023,30.81,31.16,30.24,30.76,-0.41,-1.32,1.52,7.21294849E8,100,3076.0,26.6082,5.5219,6.6854,24.0058,5.550891515892E10,2.163012954E9,87347086,4.84,819894,null,null,null],[1615132800000,36582627,30.99,31.22,29.12,29.21,-1.55,-5.04,2.37,1.097734408E9,3100,90551.0,25.2674,5.2436,6.3486,22.7962,5.271181442757E10,2.195172136E9,87362173,4.84,15087,null,null,null],[1615219200000,31309563,29.07,29.75,27.88,28.5,-0.71,-2.43,2.03,9.00502253E8,2900,82650.0,24.6533,5.1162,6.1942,22.2421,5.14305618345E10,null,null,null,null,null,null,null]]},\"error_code\":0,\"error_description\":\"\"}";

    @GetMapping("/sync")
    public void sync() {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<Stock> stocks = stockMapper.listAll();
        for (Stock stock : stocks) {
            executorService.submit(() -> {
                String stockCode = stock.getStrCode();
                Integer totalAmount = 100000;
                Integer highSpotDay = -200;
                Integer buyDay = -22;
                //近期高点
                BigDecimal maxPrice = getMaxPrice(stockCode, highSpotDay);
                //计算结果
                ProfitAlgorithm profitAlgorithm = calculate(maxPrice, stockCode, buyDay, totalAmount);
                profitAlgorithm.setCode(stock.getCode());
                profitAlgorithmMapper.deleteByCode(profitAlgorithm.getCode());
                profitAlgorithmMapper.insert(profitAlgorithm);
            });
        }
    }

    public static BigDecimal getMaxPrice(String stockCode, Integer day) {
        //近期高点
        BigDecimal maxPrice = new BigDecimal(0);
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(String.format(price, stockCode, day));
            httpGet.setHeader("Cookie", "s=cd19rbv9jp; device_id=9f047499525937484be7cdb3eebc0da4; xq_a_token=a4b3e3e158cfe9745b677915691ecd794b4bf2f9; xqat=a4b3e3e158cfe9745b677915691ecd794b4bf2f9; xq_r_token=b80d3232bf315f8710d36ad2370bc777b24d5001; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTYxNzc2MzQxOCwiY3RtIjoxNjE1MjEwNjk1NTkxLCJjaWQiOiJkOWQwbjRBWnVwIn0.HQXplSdZ8hBJaBNUpYbY3DCZqbE4wewhEStQFWDLFdbAqVUQwpEV99if8EXQs0lBe3C5DIbbVZ0257Jx3NzFE7H2KBaFS068j2ltGP0BZMNkNtzR1cnF8vzng2lleQGiC6aeGLRp0iSuow6OHSfwfVrLF6dPNx9lqIPUbV3rbBcHjhdMrllYjus7UbsU1XdHQpINVdoYWxkJDGOfjbUeOXBEhUDFZDGY0Q-1-T_tFhNx7q9HhkW_QxM-JqLuhHDsz9YRQ_oK39e91suOmMotFAyblcnesMr7CjqPv4kEQnfLre4NXEcLzFJCsW4tANPmHJfGTpVruUNBIjoVsJoQKQ; u=921615210721214; Hm_lvt_1db88642e346389874251b5a1eded6e3=1615210722,1615297954,1615301951,1615311012; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1615311889");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (!Objects.equals(response.getStatusLine().getStatusCode(), 200)) {
                return BigDecimal.ZERO;
            }
            String resultContent = EntityUtils.toString(entity, "UTF-8");
            JSONObject json = JSON.parseObject(resultContent);
            List<Object> items = (List<Object>) json.getJSONObject("data").getJSONArray("item");
            for (Object item : items) {
                List<BigDecimal> priceList = (List<BigDecimal>) item;
                if (maxPrice.compareTo(priceList.get(3)) < 0) {
                    maxPrice = priceList.get(3);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxPrice;
    }

    public static ProfitAlgorithm calculate(BigDecimal maxPrice, String stockCode, Integer day, Integer startMoney) {
        ProfitAlgorithm profitAlgorithm = new ProfitAlgorithm();
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(String.format(price, stockCode, day));
            httpGet.setHeader("Cookie", "s=cd19rbv9jp; device_id=9f047499525937484be7cdb3eebc0da4; xq_a_token=a4b3e3e158cfe9745b677915691ecd794b4bf2f9; xqat=a4b3e3e158cfe9745b677915691ecd794b4bf2f9; xq_r_token=b80d3232bf315f8710d36ad2370bc777b24d5001; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTYxNzc2MzQxOCwiY3RtIjoxNjE1MjEwNjk1NTkxLCJjaWQiOiJkOWQwbjRBWnVwIn0.HQXplSdZ8hBJaBNUpYbY3DCZqbE4wewhEStQFWDLFdbAqVUQwpEV99if8EXQs0lBe3C5DIbbVZ0257Jx3NzFE7H2KBaFS068j2ltGP0BZMNkNtzR1cnF8vzng2lleQGiC6aeGLRp0iSuow6OHSfwfVrLF6dPNx9lqIPUbV3rbBcHjhdMrllYjus7UbsU1XdHQpINVdoYWxkJDGOfjbUeOXBEhUDFZDGY0Q-1-T_tFhNx7q9HhkW_QxM-JqLuhHDsz9YRQ_oK39e91suOmMotFAyblcnesMr7CjqPv4kEQnfLre4NXEcLzFJCsW4tANPmHJfGTpVruUNBIjoVsJoQKQ; u=921615210721214; Hm_lvt_1db88642e346389874251b5a1eded6e3=1615210722,1615297954,1615301951,1615311012; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1615311889");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (!Objects.equals(response.getStatusLine().getStatusCode(), 200)) {
                return profitAlgorithm;
            }
            String resultContent = EntityUtils.toString(entity, "UTF-8");

            int buyCount = 0;
            int saleCount = 0;


            //操作本金
            BigDecimal cash = new BigDecimal(startMoney);

            JSONObject json = JSON.parseObject(resultContent);
            List<Object> items = (List<Object>) json.getJSONObject("data").getJSONArray("item");
            for (Object item : items) {
                List<BigDecimal> priceList = (List<BigDecimal>) item;
                if (maxPrice.compareTo(priceList.get(3)) < 0) {
                    maxPrice = priceList.get(3);
                }
            }

            BigDecimal buyPrice = BigDecimal.ZERO;//买入价格
            BigDecimal firstBuyPrice = BigDecimal.ZERO;//首次买入价格
            BigDecimal currentPrice = BigDecimal.ZERO;
            BigDecimal sum = BigDecimal.ZERO;//收益
            BigDecimal earnRate = BigDecimal.ZERO;//收益率
            BigDecimal lastOpen = BigDecimal.ZERO;//昨开
            BigDecimal lastHigh = BigDecimal.ZERO;//昨高
            BigDecimal lastLow = BigDecimal.ZERO;//昨低
            BigDecimal lastClose = BigDecimal.ZERO;//昨收
            Date startDate = null;
            for (Object object : items) {
                if (earnRate.compareTo(new BigDecimal("0.07")) > 0) {
                    continue;
                }
                List<Object> result = (List<Object>) object;
                BigDecimal todayOpen = new BigDecimal(result.get(2).toString());
                BigDecimal todayHigh = new BigDecimal(result.get(3).toString());
                BigDecimal todayLow = new BigDecimal(result.get(4).toString());
                BigDecimal todayClose = new BigDecimal(result.get(5).toString());

                if (Objects.isNull(startDate)) {
                    startDate = new Date(Long.parseLong(result.get(0).toString()));
                }

                if (lastOpen.compareTo(BigDecimal.ZERO) != 0) {
                    //卖出
                    if (buyPrice.compareTo(new BigDecimal(0)) > 0) {
                        if (todayLow.compareTo(lastLow) < 0) {
                            if (todayOpen.compareTo(lastLow) < 0) {
                                sum = sum.add(todayOpen.subtract(buyPrice));
                                buyPrice = new BigDecimal(0);
                                saleCount++;
                                earnRate = sum.divide(firstBuyPrice, 3, BigDecimal.ROUND_HALF_UP);
                                System.out.println(" - " + todayHigh + " = " + (buyPrice.subtract(todayHigh)) + "|earnRate:" + earnRate + " 】" + DateUtil.sdfFormatDate(new Date(Long.parseLong(result.get(0).toString()))));
                            } else {
                                sum = sum.add(lastLow.subtract(buyPrice));
                                buyPrice = new BigDecimal(0);
                                saleCount++;
                                earnRate = sum.divide(firstBuyPrice, 3, BigDecimal.ROUND_HALF_UP);
                                System.out.println(" - " + lastLow + " = " + (buyPrice.subtract(lastLow)) + "|earnRate:" + earnRate + "】" + DateUtil.sdfFormatDate(new Date(Long.parseLong(result.get(0).toString()))));
                            }
                        }
                    } else {
                        //买入
                        BigDecimal divideBigDecimal = lastClose.divide(maxPrice, 3, BigDecimal.ROUND_HALF_UP);
                        if (divideBigDecimal.compareTo(new BigDecimal(0.8)) < 0 && todayHigh.compareTo(lastHigh) > 0) {
                            if (todayOpen.compareTo(lastHigh) > 0) {
                                buyPrice = todayOpen;
                                buyCount++;
                                System.out.print(DateUtil.sdfFormatDate(new Date(Long.parseLong(result.get(0).toString()))) + "-buy-【" + buyPrice);
                            } else {
                                buyPrice = lastHigh;
                                buyCount++;
                                System.out.print(DateUtil.sdfFormatDate(new Date(Long.parseLong(result.get(0).toString()))) + "-buy-【" + buyPrice);
                            }
                        }
                    }
                }

                if (object == items.get(items.size() - 1) && buyPrice.compareTo(BigDecimal.ZERO) > 0) {
                    sum = sum.add(todayClose.subtract(buyPrice));
                    saleCount++;
                    if (firstBuyPrice.compareTo(BigDecimal.ZERO) > 0) {
                        earnRate = sum.divide(firstBuyPrice, 3, BigDecimal.ROUND_HALF_UP);
                    }
                    System.out.println(" - " + todayClose + "|earnRate:" + earnRate + "】" + DateUtil.sdfFormatDate(new Date(Long.parseLong(result.get(0).toString()))));
                }

                lastOpen = new BigDecimal(result.get(2).toString());
                lastHigh = new BigDecimal(result.get(3).toString());
                lastLow = new BigDecimal(result.get(4).toString());
                lastClose = new BigDecimal(result.get(5).toString());
                currentPrice = new BigDecimal(result.get(5).toString());
                if (buyPrice.compareTo(BigDecimal.ZERO) > 0 && firstBuyPrice.compareTo(BigDecimal.ZERO) == 0) {
                    firstBuyPrice = buyPrice;
                }
            }
            List<Object> l = (List<Object>) items.get(0);

            Integer exchangeMoney = cash.divide(new BigDecimal(-10000)).intValue() * 20 * saleCount;
            BigDecimal earnMoney = new BigDecimal(earnRate.doubleValue() * startMoney.intValue() + exchangeMoney.intValue());
            BigDecimal rate = BigDecimal.ZERO;
            if (firstBuyPrice.compareTo(BigDecimal.ZERO) > 0) {
                rate = currentPrice.subtract(firstBuyPrice).divide(firstBuyPrice, 3, BigDecimal.ROUND_HALF_UP);
            }
            profitAlgorithm.setBuyPrice(firstBuyPrice);
            profitAlgorithm.setCash(cash);
            profitAlgorithm.setCurrentPrice(currentPrice);
            profitAlgorithm.setEarnMoney(earnMoney);
            profitAlgorithm.setEarnRate(earnRate);
            profitAlgorithm.setEndDate(new Date());
            profitAlgorithm.setStartDate(startDate);
            profitAlgorithm.setRate(rate);

            System.out.println("=== start: " + DateUtil.sdfFormatDate(new Date(Long.parseLong(l.get(0).toString()))) + "  ,  buyCount:" + buyCount + "  ,  saleCount:" + saleCount);
            System.out.println("=== firstBuyPrice: " + firstBuyPrice + "  , nowPrice:" + currentPrice + "  , sum:" + sum);
            System.out.println("=== exchange money: " + cash.divide(new BigDecimal(10000)).intValue() * 20 * saleCount);
            System.out.println("=== earn rate: " + earnRate);
            System.out.println("=== earn money: " + earnMoney);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return profitAlgorithm;
    }
}
