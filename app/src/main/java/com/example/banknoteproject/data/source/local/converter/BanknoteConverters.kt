package com.example.banknoteproject.data.source.local.converter

import androidx.room.TypeConverter
import com.example.banknoteproject.data.domain.entities.Feature
import com.example.banknoteproject.data.domain.entities.Pricing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BanknoteConverters {
    private val gson = Gson()
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return gson.toJson(value)
    }
    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
    }
    @TypeConverter
    fun fromPricing(value: List<Pricing>?): String? {
        return gson.toJson(value)
    }
    @TypeConverter
    fun toPricing(value: String?): List<Pricing>? {
        return gson.fromJson(value, object : TypeToken<List<Pricing>>() {}.type)
    }
    @TypeConverter
    fun fromFeature(value: List<Feature>?): String? {
        return gson.toJson(value)
    }
    @TypeConverter
    fun toFeature(value: String?): List<Feature>? {
        return gson.fromJson(value, object : TypeToken<List<Feature>>() {}.type)
    }

}
