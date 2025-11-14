package com.andres.rangel.vitalyn.authentication.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRetrofitMockProvider {
    private val client = OkHttpClient.Builder()
        .addInterceptor(LoginMockInterceptor())
        .build()

    val api: ILoginApiService =
        Retrofit.Builder()
            .baseUrl("http://mockapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ILoginApiService::class.java)
}
