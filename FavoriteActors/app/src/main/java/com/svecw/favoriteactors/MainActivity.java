package com.svecw.favoriteactors;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Actors> actors;
    RecyclerView recyclerView;
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
        actors = new ArrayList<>();
        prepareData();
        recyclerView = findViewById(R.id.recyclerview);
        ActorsAdapter aa = new ActorsAdapter(this,actors);
        recyclerView.setAdapter(aa);
        /*recyclerView.setLayoutManager(
                new LinearLayoutManager(this));*/
        /*recyclerView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,false));*/
        recyclerView.setLayoutManager(
                new GridLayoutManager(this,2));
    }

    private void prepareData() {
        actors.add(new Actors(R.drawable.a,"Allu Arjun",1982));
        actors.add(new Actors(R.drawable.b,"Balayya",1960));
        actors.add(new Actors(R.drawable.c,"Charan",1985));
        actors.add(new Actors(R.drawable.d,"Dulquer Salman", 1986));
        actors.add(new Actors(R.drawable.f,"Fahad Fassil", 1982));
        actors.add(new Actors(R.drawable.g,"Gopichand", 1979));
        actors.add(new Actors(R.drawable.h,"Hrithik", 1974));
        actors.add(new Actors(R.drawable.j,"Jr NTR", 1983));
        actors.add(new Actors(R.drawable.k,"Pawan Kalyan", 1971));
        actors.add(new Actors(R.drawable.m,"Mahesh Babu", 1975));
        actors.add(new Actors(R.drawable.n,"Nani", 1984));
        actors.add(new Actors(R.drawable.p,"Prabhas", 1979));
    }
}