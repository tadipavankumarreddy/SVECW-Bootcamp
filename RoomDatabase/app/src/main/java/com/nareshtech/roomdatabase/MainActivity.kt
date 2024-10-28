package com.nareshtech.roomdatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var database:HumanDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = Room.databaseBuilder(this, HumanDatabase::class.java,"database-name").
            allowMainThreadQueries().build()
    }

    fun saveData(view: View) {
        val name = findViewById<TextInputLayout>(R.id.textInputName).editText?.text?.toString()
        val age = findViewById<TextInputLayout>(R.id.textInputAge).editText?.text?.toString()!!.toInt()
        database.humanDAO().insertData(Human(0,name,age))
    }
    fun loadData(view: View) {
        val data = database.humanDAO().getAllData()
        for(i in data){
            Log.d("MAIN",i.toString())
        }
    }
}