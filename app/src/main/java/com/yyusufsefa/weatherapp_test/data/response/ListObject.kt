package com.yyusufsefa.weatherapp_test.data.response

import android.graphics.Color
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.DayOfWeek
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity(tableName = "listObject_table")
data class ListObject(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val _id: Long? = null,

    @ColumnInfo(name = "clouds")
    val clouds: Clouds,

    @ColumnInfo(name = "dt")
    val dt: Long,

    @ColumnInfo(name = "dt_txt")
    val dt_txt: String,

    @ColumnInfo(name = "main")
    val main: Main,

    @ColumnInfo(name = "sys")
    val sys: Sys,

    @ColumnInfo(name = "weather")
    val weather: List<Weather>,

    @ColumnInfo(name = "wind")
    val wind: Wind,

    @ColumnInfo(name = "cityName")
    var cityName: String = "Empty"
) : BaseItem(), Parcelable {

    fun getDay(): String? {
        return getDateTime(dt).toString()
    }

    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            org.threeten.bp.LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    fun getColorForDay(): Int {
        return when (getDateTime(dt)) {
            DayOfWeek.MONDAY -> Color.parseColor("#6d5df1")
            DayOfWeek.TUESDAY -> Color.parseColor("#35e2b3")
            DayOfWeek.WEDNESDAY -> Color.parseColor("#ff0f97")
            DayOfWeek.THURSDAY -> Color.parseColor("#ffb20f")
            DayOfWeek.FRIDAY -> Color.parseColor("#69afcd")
            DayOfWeek.SATURDAY -> Color.parseColor("#e1a98b")
            DayOfWeek.SUNDAY -> Color.parseColor("#fef200")
            else -> Color.parseColor("#28E0AE")
        }
    }

    fun getHour(): String {
        return dt_txt.substringAfter(" ").substringBeforeLast(":")
    }

    fun getDate(): String {
        return dt_txt.substringBefore(" ").substringBeforeLast(" ")
    }
}
