package com.example.weatherapp.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiClient @Inject constructor(){
    // Clientを作成
    private val httpBuilder: OkHttpClient.Builder
        get() {
            // httpClientのBuilderを作る
            val httpClient = OkHttpClient.Builder()
            // create http client　headerの追加
            httpClient.addInterceptor(
                Interceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .header("Accept", "application/json")
                        .method(original.method, original.body)
                        .build()

                    return@Interceptor chain.proceed(request)
                }
            ).readTimeout(30, TimeUnit.SECONDS)

            return httpClient
        }

    fun createService(): ApiService {
        val client = httpBuilder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://weather.tsukumijima.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
