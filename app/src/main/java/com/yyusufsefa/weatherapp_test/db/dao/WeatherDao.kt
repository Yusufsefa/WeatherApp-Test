package com.yyusufsefa.weatherapp_test.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yyusufsefa.weatherapp_test.data.response.ListObject

@Dao
interface WeatherDao {

    @Query("SELECT * from listObject_table")
    fun getAllWeather(): LiveData<List<ListObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listObject: List<ListObject>)

    @Query("DELETE FROM listObject_table")
    suspend fun deleteAll()
}