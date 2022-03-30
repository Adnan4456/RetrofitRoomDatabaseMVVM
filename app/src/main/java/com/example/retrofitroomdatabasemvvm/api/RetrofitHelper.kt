package com.example.retrofitwithmvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val  BASE_URL ="https://quotable.io/"

    //This function will return object of retrofit
    fun getInstance(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Now link this object with QuotesAPI interface.In MainActivity
    }
}