package com.example.dog24.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.dog24.model.DogApiService
import com.example.dog24.model.DogBreed
import com.example.dog24.model.DogDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

//ViewModel just get data, he know nothing about the view

class ListViewMode(application: Application) : BaseViewModel(application) {

    private val dogsService = DogApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
        disposable.add(
            dogsService.getDods()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(dogList: List<DogBreed>) {
                        storeDogsLocally(dogList)

                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun dogRetrived(dogList: List<DogBreed>) {
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list: List<DogBreed>) {
        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i<list.size){
                list[i].uuid=result[i].toInt()
                ++i
            }
            dogRetrived(list)
        }
    }

    private fun fetchFromRemote() {
        loading.value = true

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}