package com.nareshtech.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HumanDAO {

    @Insert
    fun insertData(human: Human):Unit

    @Query("select * from human")
    fun getAllData():List<Human>
}