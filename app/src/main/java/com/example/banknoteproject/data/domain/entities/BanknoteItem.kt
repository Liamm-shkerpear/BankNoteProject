package com.example.banknoteproject.data.domain.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.banknoteproject.data.source.local.converter.BanknoteConverters
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "banknote_item")
@TypeConverters(BanknoteConverters::class)
@Parcelize
data class BanknoteItem(
    @PrimaryKey
    @SerializedName("_id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("country_region") val countryRegion: String,
    @SerializedName("images") val images: List<String>?,
    @SerializedName("pricing") val pricing: List<Pricing>?,
    @SerializedName("features") val features: List<Feature>?,
    @Embedded(prefix = "obverse_")
    @SerializedName("obverse") val obverse: Obverse?,
    @Embedded(prefix = "reverse_")
    @SerializedName("reverse") val reverse: Reverse?
) : Parcelable

@Parcelize
data class Pricing(
    @SerializedName("price") val price: String
) : Parcelable

@Parcelize
data class Feature(
    @SerializedName("title") val title: String,
    @SerializedName("value") val value: String
) : Parcelable

@Parcelize
data class BanknoteResponse(
    @SerializedName("data") val data: List<BanknoteItem>
) : Parcelable

@Parcelize
data class Obverse(
    @SerializedName("description") val description: String,
    @SerializedName("lettering") val lettering: String
) : Parcelable

@Parcelize
data class Reverse(
    @SerializedName("description") val description: String,
    @SerializedName("lettering") val lettering: String
) : Parcelable
