package com.example.countryapp3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Country(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName: String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion:String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage: String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    var imageUrl: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
