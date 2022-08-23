package com.example.rxjavaandapi.model

import com.example.rxjavaandapi.model.Exercise
import io.reactivex.Observable
import retrofit2.http.GET

interface ExerciseService {

    @GET("exercises")
    fun getExercisesList(): Observable<ExerciseResponse>
}
