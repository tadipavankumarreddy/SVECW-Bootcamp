package com.nareshtech.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Human::class], version = 1)
abstract class HumanDatabase:RoomDatabase() {
   abstract fun humanDAO():HumanDAO
}