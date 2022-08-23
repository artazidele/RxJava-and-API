package com.example.rxjavaandapi.presenter

import com.example.rxjavaandapi.model.Exercise

interface ContractMain {
    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setDataToAdapter(listExercises : List<Exercise>)

    }

    interface MainPresenter {
        fun displayResult()
    }
}