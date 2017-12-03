package com.yascode.pemadamkebakaran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

//Pastikan implementasi OnMapReadyCallBack
public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView txtKelurahan, txtALamat, txtRtRw, txtName;

    Button btnDirection;

    String Alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Alamat = getIntent().getStringExtra("alamat");

        txtALamat = findViewById(R.id.txt_alamat);
        txtName = findViewById(R.id.txt_name);
        txtKelurahan = findViewById(R.id.txt_kelurahan);
        txtRtRw = findViewById(R.id.txt_rtrw);

        txtALamat.setText(Alamat);

        //konfigurasi Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //TODO : Belajar mengenai map dari video udacity ^^
    }
}
