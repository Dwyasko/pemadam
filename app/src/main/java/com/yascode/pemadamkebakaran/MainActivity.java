package com.yascode.pemadamkebakaran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

//import retrofit2.Call;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PemadamAdapter.ClickHandler {

    RecyclerView recMain;
    ProgressBar progressBar;

    ApiInterface apiInterface;

    PemadamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recMain = findViewById(R.id.rec_main);
        progressBar = findViewById(R.id.progressbar);

        loadData();
    }

    private void loadData() {
        apiInterface = RestAPI.getApiInterface();
        Call<Pemadam> pemadam = apiInterface.getPemadam();
        pemadam.enqueue(new Callback<Pemadam>() {
            @Override
            public void onResponse(Call<Pemadam> call, Response<Pemadam> response) {
                Pemadam responsePemadam = response.body();
                setRecyclerView(responsePemadam);
            }

            @Override
            public void onFailure(Call<Pemadam> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal cuy", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerView(Pemadam responsePemadam) {
        progressBar.setVisibility(View.GONE);
        recMain.setVisibility(View.VISIBLE);
        recMain.setLayoutManager(new LinearLayoutManager(this));

        Data[] data = responsePemadam.data;

        adapter = new PemadamAdapter(data, this);
        recMain.setAdapter(adapter);
    }

    @Override
    public void onClick(Data data) {

    }
}
