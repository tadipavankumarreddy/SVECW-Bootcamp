package com.nareshtech.myapplication

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "settings")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun saveData(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { settings->
                settings[stringPreferencesKey("my_key")] = "Pavan Kumar"
            }
        }
    }
    fun loadData(view: View) {
        val preferences = dataStore.data.map {
                preferences ->
            preferences[stringPreferencesKey("my_key")]?:"Default value"
        }
        var data = ""
        CoroutineScope(Dispatchers.IO).launch {
            data = preferences.first()
            runOnUiThread {
                findViewById<TextView>(R.id.textView).text = data
            }
        }
    }
}