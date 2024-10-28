package com.nareshtech.e_com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nareshtech.e_com.ui.theme.EcomTheme
import com.nareshtech.e_com.ui.theme.TextStyles

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcomTheme {
                Column(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())) {
                    Text(text = intent.getStringExtra("name")?:"", style = TextStyles)
                    Text(text = intent.getStringExtra("age")?:"")
                    Text(text = (intent.getStringArrayListExtra("languages")?: arrayListOf<String>()).joinToString(", "))
                }
            }
        }
    }
}