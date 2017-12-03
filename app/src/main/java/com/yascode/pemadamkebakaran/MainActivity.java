package com.yascode.pemadamkebakaran;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

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

        final Call<Pemadam> pemadam = apiInterface.getPemadam(
                getResources().getString(R.string.token)
        );


        pemadam.enqueue(new Callback<Pemadam>() {
            @Override
            public void onResponse(@NonNull Call<Pemadam> call, Response<Pemadam> response) {
                Pemadam responsePemadam = response.body();
                Log.d("status", responsePemadam.getStatus());
                setRecyclerView(responsePemadam);
            }

            @Override
            public void onFailure(Call<Pemadam> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage()
                        , Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setRecyclerView(Pemadam responsePemadam) {
        progressBar.setVisibility(View.GONE);
        recMain.setVisibility(View.VISIBLE);
        recMain.setLayoutManager(new LinearLayoutManager(this));

        List<Data> data = responsePemadam.data;

        adapter = new PemadamAdapter(data, this);
        recMain.setAdapter(adapter);
    }

    @Override
    public void onClick(Data data) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        String[] datas = new String[]{data.getALAMAT(), data.getPOS_PEMADAM(),
                data.getKELURAHAN(), data.getRT_RW()};

        Bundle bundle = new Bundle();
        
        bundle.putStringArray("datas", datas);
        bundle.putInt("nomor", data.getNO());
        bundle.putDouble("LAT", data.getLAT());
        bundle.putDouble("LONG", data.getLNG());

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
