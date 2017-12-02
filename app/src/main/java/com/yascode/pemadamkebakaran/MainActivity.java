package com.yascode.pemadamkebakaran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    RecyclerView recMain;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recMain = findViewById(R.id.rec_main);
        progressBar = findViewById(R.id.progressbar);
    }
}
