package com.andres.rangel.vitalyn.authentication.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRetrofitMockProvider {
    val retrofit: Retrofit? by lazy {
        runCatching {
            val client = OkHttpClient.Builder()
                .addInterceptor(LoginMockInterceptor())
                .build()

            Retrofit.Builder()
                .baseUrl("http://mockapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }.getOrNull()
    }
}