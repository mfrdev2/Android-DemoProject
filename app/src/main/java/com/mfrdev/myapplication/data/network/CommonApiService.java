package com.mfrdev.myapplication.data.network;

import com.mfrdev.myapplication.model.Cloth;
import com.mfrdev.myapplication.model.GlossaryRequestBody;
import com.mfrdev.myapplication.model.GlossaryResponseBody;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommonApiService {
    @POST("cloth/products")
    Flowable<Response<GlossaryResponseBody>> insertGlossaryProduct(@Body GlossaryRequestBody body);
    @GET("products")
    Flowable<Response<List<Cloth>>> getClothProduct();
}
