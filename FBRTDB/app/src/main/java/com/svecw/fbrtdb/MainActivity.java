package com.svecw.fbrtdb;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText person_name, person_age;
    TextView result;

    DatabaseReference databaseReference;

    class Person{
        String name;
        int age;
        Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        person_name = findViewById(R.id.person_name);
        person_age = findViewById(R.id.person_age);
        result = findViewById(R.id.textView);
        databaseReference =
                FirebaseDatabase.getInstance().getReference("persons");
    }

    public void saveData(View view) {
        // Perform saving of data
        String name = person_name.getText().toString();
        int age = Integer.parseInt(person_age.getText().toString());
        Person p = new Person(name, age);
        databaseReference.push().setValue(p);
    }

    public void loadData(View view) {
        // loading of data
    }
}