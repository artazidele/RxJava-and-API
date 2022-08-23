package com.example.rxjavaandapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaandapi.R
import com.example.rxjavaandapi.model.Exercise
import com.example.rxjavaandapi.model.ExerciseClient
import com.example.rxjavaandapi.presenter.ContractMain
import com.example.rxjavaandapi.presenter.MainPresenterImpl

class ExercisesActivity : AppCompatActivity(), ContractMain.MainView {
    lateinit var mainPresenterImpl: MainPresenterImpl
    lateinit var progressBar: ProgressBar
    lateinit var exerciseAdapter: ExerciseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)
        progressBar = findViewById(R.id.progress_bar)
        val recyclerView: RecyclerView =findViewById(R.id.exercises_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = ExerciseAdapter()
        recyclerView.adapter = exerciseAdapter
        val exerciseClient = ExerciseClient.getInstance()
        mainPresenterImpl=
            MainPresenterImpl(
                exerciseClient,
                this
            )
        mainPresenterImpl.displayResult()
    }

    override fun showProgress() {
        progressBar.visibility = VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = GONE
    }

    override fun setDataToAdapter(listExercises: List<Exercise>) {
        listExercises.forEach { exercise -> exerciseAdapter.bindViewModel(exercise) }
    }
}