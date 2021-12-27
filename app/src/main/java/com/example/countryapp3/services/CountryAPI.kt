package com.example.countryapp3.services

import com.example.countryapp3.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getDataiki():Single<List<Country>>


}