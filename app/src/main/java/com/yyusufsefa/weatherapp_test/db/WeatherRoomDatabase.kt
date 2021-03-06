package com.yyusufsefa.weatherapp_test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yyusufsefa.weatherapp_test.data.response.HeaderObject
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.db.dao.WeatherDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [
        ListObject::class,
        HeaderObject::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(DataTypeConverter::class)
abstract class WeatherRoomDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(
            context: Context
        ): WeatherRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "weather_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
