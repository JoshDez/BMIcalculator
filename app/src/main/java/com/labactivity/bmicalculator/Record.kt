package com.labactivity.bmicalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class Record(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "courtesy") val courtesy:String,
    @ColumnInfo(name = "bmi") val bmi:Float
)
