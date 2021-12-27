package com.example.countryapp3.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp3.model.Country
import com.example.countryapp3.services.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application) {

    val countryLİveData = MutableLiveData<Country>()

    fun getDataFromRomm(Uuid:Int) {

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
          val country =  dao.getCountry(Uuid)
            countryLİveData.value = country
        }

    }
}