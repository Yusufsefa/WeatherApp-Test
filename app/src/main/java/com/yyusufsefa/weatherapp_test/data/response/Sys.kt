package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "sys_table")
data class Sys(
    @ColumnInfo(name = "pod")
    val pod: String
) : Parcelable