package com.andres.rangel.vitalyn.authentication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRetrofitProvider {
    val retrofit: Retrofit? by lazy {
        runCatching {
            Retrofit.Builder()
                .baseUrl("http://[URL_API].com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }.getOrNull()
    }
}
