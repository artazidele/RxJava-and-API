package com.example.rxjavaandapi.model

import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ExerciseClient {

    private val exerciseService   =  Retrofit.Builder()
        .baseUrl("https://exercisedb.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getHttpClient())
        .build().create(ExerciseService::class.java)

    companion object{
        lateinit var INSTANCE: ExerciseClient
        fun getInstance(): ExerciseClient {
            INSTANCE = ExerciseClient()
            return INSTANCE
        }
    }


    fun getExercises(): Observable<ExerciseResponse> {
        return exerciseService.getExercisesList()
    }
    private fun getHttpClient(): OkHttpClient? {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request()
                    val newRequest: Request = request.newBuilder()
                        .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key","75be40b2c2msh83aae7a3af8fcdfp1ea10bjsna4248eaa1ddb")
                        .build()
                    return chain.proceed(newRequest)
                }
            })
            .build()
    }
}