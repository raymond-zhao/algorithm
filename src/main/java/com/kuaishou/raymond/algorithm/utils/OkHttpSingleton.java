package com.kuaishou.raymond.algorithm.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: raymond
 * @Datetime: 2023/9/27 14:32
 * Description:
 */
public enum OkHttpSingleton {

    INSTANCE;

    final OkHttpClient okHttpClient;
    
    final int connectTimeout = 5;
    
    final int readTimeout = 20;
    
    final int callTimeout = 5;

    OkHttpSingleton() {
        this.okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .callTimeout(callTimeout, TimeUnit.SECONDS)
                .build();
    }

    OkHttpClient getClient() {
        return okHttpClient;
    }

    Response call(Request request) throws IOException {
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response;
        }
    }
}
