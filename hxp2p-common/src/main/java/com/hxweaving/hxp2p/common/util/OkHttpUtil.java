package com.hxweaving.hxp2p.common.util;

import com.alibaba.fastjson2.JSON;
import com.hxweaving.hxp2p.common.domain.CommonErrorCode;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS).build();

    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static RestResponse post(String url, String json) {
        RequestBody body = RequestBody.create(JSON_TYPE, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null){
                RestResponse restResponse = JSON.parseObject(responseBody.string(), RestResponse.class);
                return restResponse;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RestResponse.validfail(CommonErrorCode.E_100106.getDesc());
    }
}
