package com.yascode.pemadamkebakaran;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

//Pastikan implementasi OnMapReadyCallBack
public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView txtKelurahan, txtALamat, txtRtRw, txtName;

    Button btnDirection;

    String[] Datas;
    int No;
    double Lat, Lng;

    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        txtALamat = findViewById(R.id.txt_alamat);
        txtName = findViewById(R.id.txt_name);
        txtKelurahan = findViewById(R.id.txt_kelurahan);
        txtRtRw = findViewById(R.id.txt_rtrw);

        if (bundle != null) {
            Datas = bundle.getStringArray(Constants.DATAS);
            No = bundle.getInt(Constants.NOMOR);
            Lat = bundle.getDouble(Constants.LAT);
            Lng = bundle.getDouble(Constants.LNG);

            txtALamat.setText(Datas[0]);
        }


        //konfigurasi Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //TODO : Belajar mengenai map dari video udacity ^^

        mGoogleMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        googleMap.setMyLocationEnabled(true);


        /*mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);

        String lastLat = Util.getStringPreference(MenuActivity.this, Constants.LatJemput);
        String lastLong = Util.getStringPreference(MenuActivity.this, Constants.LongJemput);
        String lastLokasi = Util.getStringPreference(MenuActivity.this, Constants.LokasiJemput);
        String lastRemark = Util.getStringPreference(MenuActivity.this, Constants.RemarkJemput);

        if ((lastLat != null && !lastLat.equals(""))
                &&
                (lastLong != null && !lastLong.equals(""))) {

            LatLng lastLoc = new LatLng(Double.parseDouble(lastLat), Double.parseDouble(lastLong));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lastLoc));

            CameraPosition lastPosition = new CameraPosition.Builder()
                    .target(lastLoc).zoom(19f).tilt(70).build();

            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(lastPosition));

            resultLocationPickup.setText(lastLokasi);
            resultLocationDetailPickup.setText(lastRemark);
        }*/
    }
}
