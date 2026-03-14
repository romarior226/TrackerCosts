package com.example.trackercosts.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://69b55e9dbe587338e715b801.mockapi.io/trackerCostApi/"

    val api: ExpenseApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExpenseApi::class.java)
    }
}