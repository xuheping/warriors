//package com.hp.warriors.http.api;
//
//import com.alibaba.fastjson.JSONObject;
//import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
//import org.springframework.stereotype.Component;
//import retrofit2.http.GET;
//import retrofit2.http.Query;
//
//@Component
//@RetrofitClient(baseUrl = "${iwencai.url}")
//public interface IWenCaiApi {
//
//    @GET("/diag/block-detail")
//    Object blockDetail(@Query("pid") String pid, @Query("codes") String codes, @Query("codeType") String codeType, @Query("info") JSONObject info);
//}
