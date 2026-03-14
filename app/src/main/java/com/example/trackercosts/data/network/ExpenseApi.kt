package com.example.trackercosts.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ExpenseApi {
    @GET("expanse")
    suspend fun getExpanse(@Query("limit") limit: Int = 50): List<ExpenseDto>
}