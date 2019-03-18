package com.takehome.doordash.mgodsay.doordashdiscover.network;

import com.takehome.doordash.mgodsay.doordashdiscover.model.Restaurant;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface DoorDashApi
{
    @GET("restaurant/")
    Single <List<Restaurant>> discover(@QueryMap Map<String, String> paramsMap);

    @GET("restaurant/{id}/")
    Single <Restaurant> getRestaurant(@Path("id")String id);

}
