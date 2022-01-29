package com.mfrdev.myapplication.data.repositories;


import android.util.Log;

import com.mfrdev.myapplication.data.network.CommonApiService;
import com.mfrdev.myapplication.model.Cloth;
import com.mfrdev.myapplication.model.GlossaryRequestBody;
import com.mfrdev.myapplication.model.GlossaryResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class CommonRepository {
    private static final String COMMON_SERVICE_BASE_URL = "http://127.0.0.1:8080/cloth/";

    private final CommonApiService commonApiService;

    public CommonRepository() {
        commonApiService = new Retrofit.Builder()
                .client(getRequestHeader())
                .baseUrl(COMMON_SERVICE_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CommonApiService.class);
    }

    private static OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message
                -> Log.i("Tag", "getRequestHeader: "+ message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(33, TimeUnit.SECONDS)
                .writeTimeout(33, TimeUnit.SECONDS)
                .readTimeout(33, TimeUnit.SECONDS)
                .build();
    }

    public Flowable<Response<GlossaryResponseBody>> executeInsertGlossaryProduct(GlossaryRequestBody body) {
      return   commonApiService.insertGlossaryProduct(body);
    }

   public Flowable<Response<List<Cloth>>> getClothProduct(){
        return commonApiService.getClothProduct();
    }


}
