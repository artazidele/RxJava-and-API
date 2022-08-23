package com.example.rxjavaandapi.presenter

import com.example.rxjavaandapi.model.ExerciseClient
import com.example.rxjavaandapi.model.ExerciseResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl (exerciseClient: ExerciseClient, mainView: ContractMain.MainView):
    ContractMain.MainPresenter {
    private val covidClient = exerciseClient
    private val mainView = mainView
    override fun displayResult(){
        mainView.showProgress()
        covidClient.getExercises()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ExerciseResponse> {
                override fun onNext(exercises: ExerciseResponse) {
                    mainView.setDataToAdapter(exercises.response)
                }
                override fun onError(e: Throwable) {
                    mainView.hideProgress()
                }
                override fun onComplete() {
                    mainView.hideProgress()
                }
                override fun onSubscribe(d: Disposable) {
                }
            })
    }
}