package com.svecw.jokes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    EditText et;

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

        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);
        et = findViewById(R.id.book_name);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    public void getJoke(View view) {
        // TODO 2: Handle the progress bar
        progressBar.setVisibility(View.VISIBLE);
        String name = et.getText().toString();
        // TODO 1: Do networking and get the random joke
        new FetchJoke().execute("https://www.googleapis.com/books/v1/volumes?q="+name);
    }

    // TODO 1: in action
    class FetchJoke extends AsyncTask<String,Void,String>{

        // TODO 1.1: Basic requirement is Internet Permission
        @Override
        protected String doInBackground(String... strings) {
            // TODO 1.2: Read the url
            String url = strings[0];
            // TODO 1.3: Establish the connection
            try {
                URL u = new URL(url);
                HttpsURLConnection connection = (HttpsURLConnection)
                        u.openConnection();
                // TODO 1.4: Read the input stream
                InputStream is = connection.getInputStream();
                // TODO 1.5: Start reading the data
                Scanner s = new Scanner(is);
                StringBuilder sb = new StringBuilder();
                while(s.hasNext()){
                    sb.append(s.nextLine());
                }
                Log.v("MAIN",sb.toString());
                return sb.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            /*try {
                JSONObject obj = new JSONObject(s);
                String joke = obj.getString("value");
                result.setText(joke);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }*/

            Gson g = new Gson();
            Books b = g.fromJson(s,Books.class);
            BooksAdapter adapter = new BooksAdapter(MainActivity.this,b.getItems());
            recyclerView.setAdapter(adapter);
        }
    }
}