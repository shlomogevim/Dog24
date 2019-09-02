package com.example.dog24.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dog24.model.DogBreed

class DetailViewModel:ViewModel() {
    var dog=MutableLiveData<DogBreed>()

    fun fetch() {
        val dog1=DogBreed("1","Corgi","15 years","breedGroup","bredFor","temprament","")

        dog.value=dog1
    }
}

/*
class ListViewMode:ViewModel() {
    val dogs=MutableLiveData<List<DogBreed>>()
    val dogsLoadError=MutableLiveData<Boolean>()
    val loading=MutableLiveData<Boolean>()


    fun refresh(){
        val dog1=DogBreed("1","Corgi","15 years","breedGroup","bredFor","temprament","")
        val dog2=DogBreed("2","Corgi2","152 years","breedGroup2","bredFor","temprament","")
        val dog3=DogBreed("3","Corgi3","153 years","breedGroup3","bredFor","temprament","")
        val dogsList= arrayListOf(dog1,dog2,dog3)

        dogs.value=dogsList
        dogsLoadError.value=false
        loading.value=false
    }
}*/
