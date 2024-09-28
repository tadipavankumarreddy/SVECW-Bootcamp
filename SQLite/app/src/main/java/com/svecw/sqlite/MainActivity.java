package com.svecw.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        DBhelper dBhelper = new DBhelper(this);

        ContentValues cv = new ContentValues();
        cv.put("name","Pavan");
        cv.put("age",18);
        dBhelper.insertData(cv);

        ContentValues cv1 = new ContentValues();
        cv1.put("name","Naga sri");
        cv1.put("age",28);
        dBhelper.insertData(cv1);

        ContentValues cv2 = new ContentValues();
        cv2.put("name","Ambajirao");
        cv2.put("age",38);
        dBhelper.insertData(cv2);

        Cursor c = dBhelper.readData();
        c.moveToFirst();
        StringBuilder sb = new StringBuilder();
        do{
            sb.append(c.getInt(0)+" "+c.getString(1)+" "+c.getInt(2)+"\n");
        }while (c.moveToNext());

        TextView v = findViewById(R.id.textview);
        v.setText(sb.toString());
    }
}