package com.example.admin1.retrofitgsondemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin1 on 12/10/16.
 */

public interface RefugeObjectApi {
    @GET("/api/refuges/get-other-refuges?time=2016-04-07 09:14:20&language=english")
    Call<Refuge> getRefuges();
}
