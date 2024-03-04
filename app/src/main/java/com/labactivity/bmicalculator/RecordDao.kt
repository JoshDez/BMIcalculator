package com.labactivity.bmicalculator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {
    //Retrieve all of the records
    @Query("SELECT * FROM records")
    suspend fun getAllRecords():List<Record>

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecord(Record: Record)

    //Delete
    @Query("DELETE FROM records WHERE id = :recordId")
    suspend fun deleteRecord(recordId:Long)
}