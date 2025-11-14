package com.andres.rangel.vitalyn.authentication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRetrofitProvider {
    val api: ILoginApiService =
        Retrofit.Builder()
            .baseUrl("http://[URL_API].com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ILoginApiService::class.java)
}