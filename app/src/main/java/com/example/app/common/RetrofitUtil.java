package com.example.app.common;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author Tao.ZT.Zhang
 * Date   2017/10/31
 */

public class RetrofitUtil {
    private static volatile  RetrofitUtil instance;
    public Retrofit getRetrofit() {
        return retrofit;
    }

    private final Retrofit retrofit;

    private OkHttpClient getOkHttpClient(){
       OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
        return okHttpClient;
    }
    private RetrofitUtil(){
        retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public static RetrofitUtil getInstance(){
        if (instance == null){
            synchronized (RetrofitUtil.class){
                if (instance == null){
                    return  new RetrofitUtil();
                }
            }
        }
        return instance;
    }
}
