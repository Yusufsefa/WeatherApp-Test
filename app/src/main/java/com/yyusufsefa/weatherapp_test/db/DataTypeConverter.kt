package com.yyusufsefa.weatherapp_test.db

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yyusufsefa.weatherapp_test.data.response.*
import java.lang.reflect.Type

object DataTypeConverter {

    @androidx.room.TypeConverter
    @JvmStatic
    fun cityToString(item: City) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToCity(value: String?): City? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<City?>() {}.type
        return Gson().fromJson<City>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun coordToString(item: Coord) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToCoord(value: String?): Coord? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Coord?>() {}.type
        return Gson().fromJson<Coord>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun cloudsToString(item: Clouds) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToClouds(value: String?): Clouds? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Clouds?>() {}.type
        return Gson().fromJson<Clouds>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun mainToString(item: Main) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToMain(value: String?): Main? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Main?>() {}.type
        return Gson().fromJson<Main>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun sysToString(item: Sys) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToSys(value: String?): Sys? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Sys?>() {}.type
        return Gson().fromJson<Sys>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun windToString(item: Wind) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToWind(value: String?): Wind? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Wind?>() {}.type
        return Gson().fromJson<Wind>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun listObjectToString(array: List<ListObject>) =
        if (array.isEmpty()) "" else Gson().toJson(array)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToListObject(value: String?): List<ListObject>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<ListObject>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun listWeatherToString(array: List<Weather>) =
        if (array.isEmpty()) "" else Gson().toJson(array)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToListWeather(value: String?): List<Weather>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(value, listType)
    }

}
