package com.goshapp.myrandusers.retrofit;


import com.goshapp.myrandusers.pojo.RandomUser;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("api/?results=20")
    Observable <RandomUser> getUser();
}
