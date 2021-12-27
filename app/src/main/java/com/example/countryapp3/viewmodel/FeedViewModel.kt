package com.example.countryapp3.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp3.model.Country
import com.example.countryapp3.services.CountryAPIService
import com.example.countryapp3.services.CountryDatabase
import com.example.countryapp3.utils.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application): BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private val customPreferences = CustomSharedPreferences(getApplication())
    private val refleshtime =10*60*1000*1000*1000L

    val countries = MutableLiveData<List<Country>>()
    val countryLoading = MutableLiveData<Boolean>()
    val countryError = MutableLiveData<Boolean>()

    fun refreshData(){
        val updateTime = customPreferences.getTime()
        if (updateTime !=null && updateTime!=0L && System.nanoTime() - updateTime < refleshtime){
            getDataFromSQLite()
        }else{
            getDataFromApı()

        }
    }

    private fun getDataFromSQLite() {
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"SQL LİTE", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDataFromApı(){
        countryLoading.value = true

        disposable.add(
            countryAPIService.getDataFromApı()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                      storeInSQLite(t)
                        Toast.makeText(getApplication(),"FROM API", Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                    e.printStackTrace()
                        }

                })
        )
    }

    private fun showCountries(countryList:List<Country>){
        countries.value = countryList
        countryLoading.value = false
        countryError.value = false
    }

    private fun storeInSQLite(list:List<Country>){
        launch {
        val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
          val listLong =  dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i<list.size){
                list[i].uuid = listLong[i].toInt()
                i = i+1
                showCountries(list)
            }
            customPreferences.saveTime(System.nanoTime())
        }
    }

}