package com.nareshtech.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "human")
data class Human(
    @PrimaryKey(autoGenerate = true)
    val person_id:Int,

    @ColumnInfo(name = "human_name")
    val humanName:String?,

    @ColumnInfo(name = "human_age")
    val humanAge:Int?
)
