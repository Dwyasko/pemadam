package com.yascode.pemadamkebakaran;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    TextView txtKelurahan, txtALamat, txtRtRw, txtName;

    Button btnDirection;
    double LAT, LNG;

    GoogleMap mGoogleMap;

    GoogleApiClient googleApiClient;
    private Marker markerPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //menerima bundle yang kita kirim dari Intent Sebelumnya
        Bundle bundle = getIntent().getExtras();

        //googleApiClient diperlukan apabila kita ingin mendapatkan data lokasi kita
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        if (checkPlayServices()) {
            //mengkoneksikan aplikasi dengan google play service untuk mendapatkan akses API yang kita inginkan
            // - dalam hal ini Lokasi (LocationService.API)
            googleApiClient.connect();
        }

        txtALamat = findViewById(R.id.txt_alamat);
        txtName = findViewById(R.id.txt_name);
        txtKelurahan = findViewById(R.id.txt_kelurahan);
        txtRtRw = findViewById(R.id.txt_rtrw);
        btnDirection = findViewById(R.id.btn_direction);

        if (bundle != null) {
            setView(bundle);
            LAT = bundle.getDouble(Constants.LAT);
            LNG = bundle.getDouble(Constants.LNG);
        }

        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.google.com/maps/search/?api=1&query=" + LAT + "," + LNG + "";

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void setView(Bundle bundle) {
        txtName.append(bundle.getString(Constants.NAMA));
        txtALamat.append(bundle.getString(Constants.ALAMAT));
        txtKelurahan.append(bundle.getString(Constants.KELURAHAN));
        txtRtRw.append(bundle.getString(Constants.RTRW));
    }

    private void setMap() {
        //melakukan cek permission secara runtime. - cek permission secara Runtime diperlukan apanila aplikasi dijalankan pada
        // Android versi <= M (Marshmallow). cek permission secara runtime akan menampilkan dialog perijinan akses (dalam hal ini Lokasi).
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1234);
            return;
        }

        //create object Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);

        //Menampilkan peta - sebelumnya map fragment akan memproses string API_KEY kita.
        mapFragment.getMapAsync(this);
    }

    //setelah memanggil getMapAsync, ketika konfigurasi API_KEY dan lainnya sudah sesuai, method onMapReady akan dipanggil.
    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        //untuk mendapatkan lokasi kita secara realtime - pada praktik ini belum akan diimplementasikan, karena lokasi yang ditampilkan dalam map
        // adalah lokasi pos pemadam yang kita dapat dari API.
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        //Membuat Objek LatLng untuk menunjukkan koordinat lokasi (Latitude, Longitude) Pos Pemadam yang kita dapatkan dari API.
        LatLng latLng = new LatLng(LAT, LNG);

        //mengatur posisi kamera sesuai dengan LatLng yang sudah kita set.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(19f).tilt(70).build();
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        createMarker();
    }

    private void createMarker() {

        LatLng latLng = new LatLng(LAT, LNG);

        markerPick = mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

    }

    //untuk menangkap action user pada saat dialog request perijinan muncul (diijinkan atau ditolak).
    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1234) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setMap();
            } else {
                Toast.makeText(this, "Perijinan diperlukan untuk mengakses detail lokasi", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }


    //melakukan pengecekan apakah Google Play Service pada device/emulator kita bisa dijalankan dan sesuai dengan kriteria GoogleApiClient.
    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(resultCode)) {
                googleAPI.getErrorDialog(this, resultCode,
                        Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }

    //setelah koneksi ke Google Play Service berhasil, method di bawah ini akan dipanggil, dan saat itu juga kita melakukan konfigurasi Map sesuai kebutuhan kita.
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        setMap();
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (!googleApiClient.isConnecting()) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
