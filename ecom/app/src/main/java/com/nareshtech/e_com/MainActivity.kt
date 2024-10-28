package com.nareshtech.e_com

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nareshtech.e_com.ui.theme.EcomTheme
import java.util.ArrayList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcomTheme {
                registrationScreen(applicationContext)
            }
        }
    }


    @Composable
    fun registrationScreen(context: Context){
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf("") }
        var languages = listOf("Telugu","Hindi","English")
        val selectedLanguages = remember { mutableStateListOf<String>() }
        val countries = listOf("India","Thailand","UK","USA")

        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())) {
            Text("Registration Form", style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp, fontWeight = FontWeight.Bold)

            // name
            OutlinedTextField(value = name,
                onValueChange = {name = it},
                label = { Text("Enter your name")},
                modifier = Modifier.fillMaxWidth()
            )

            //age
            OutlinedTextField(value = age,
                onValueChange = {age = it},
                label = { Text("Enter your age")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Gender", fontWeight = FontWeight.Bold)
                RadioButton(selected = gender == "Male",
                    onClick = { gender = "Male"})
                Text("Male")

                RadioButton(selected = gender == "Female",
                    onClick = { gender = "Female"})
                Text("Female")
            }

            Text(text = "Languages", fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                languages.forEach{ language ->
                        Checkbox(checked = selectedLanguages.contains(language),
                            onCheckedChange = {
                            if (it) selectedLanguages.add(language) else selectedLanguages.remove(language)
                        },
                            Modifier.weight(1f))

                        Text(text = language)
                    }
            }

            Button(onClick = {
                val intent = Intent(context,DetailsActivity::class.java)
                    .putExtra("name", name)
                    .putExtra("age",age)
                    .putExtra("gender", gender)
                    .putStringArrayListExtra("languages", ArrayList(languages))
                    .addFlags(FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }) {
                Text(text = "Register")
            }
        }
    }

}