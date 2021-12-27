package com.example.countryapp3.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countryapp3.model.Country

@Dao
interface CountryDao {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :CountryId")
    suspend fun getCountry(CountryId: Int): Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()
}

