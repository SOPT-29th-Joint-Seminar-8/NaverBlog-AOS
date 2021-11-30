package co.kr.naverblog_andoid.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://asia-northeast3-naverblog-server.cloudfunctions.net/api/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val feedService = retrofit.create(FeedService::class.java)
}