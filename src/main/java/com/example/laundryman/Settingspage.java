package com.example.laundryman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class Settingspage extends AppCompatActivity {
    LocationManager locationManager; LocationListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingspage);
     locationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
      listener =new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                getLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
      if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
      }
       else {
                 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
                 Location l=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                 if(l!=null) {
                       getLocation(l);
                 }
      }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1 && grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                 if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
                           locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
                 }

        }
    }

    public void getLocation(Location location) {
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.laundryman",Context.MODE_PRIVATE);
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());
        String address;
        try {
            List<Address> listadd = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if(listadd!=null && listadd.size()>0) {
              address="";
              if(listadd.get(0).getThoroughfare()!=null) {
                     address+=listadd.get(0).getThoroughfare()+ "  ";
                  sharedPreferences.edit().putString("address",address).apply();
              }
              if(listadd.get(0).getLocality()!=null) {
                     address+=listadd.get(0).getLocality()+ "  ";
                  sharedPreferences.edit().putString("Locality",address).apply();
              }

            }
        }catch(Exception e) {
                e.printStackTrace();
        }
        sharedPreferences.edit().putString("Latitude",String.valueOf(location.getLatitude())).apply();
        sharedPreferences.edit().putString("Longitude",String.valueOf(location.getLongitude())).apply();

    }
    public void clickloc(View view) {

        SharedPreferences s=this.getSharedPreferences("com.example.laundryman",Context.MODE_PRIVATE);
        Toast.makeText(Settingspage.this, s.getString("Latitude",""), Toast.LENGTH_SHORT).show();
        Toast.makeText(Settingspage.this, s.getString("Longitude",""), Toast.LENGTH_SHORT).show();
        Toast.makeText(Settingspage.this, s.getString("address",""), Toast.LENGTH_SHORT).show();
        Toast.makeText(Settingspage.this, s.getString("Locality",""), Toast.LENGTH_SHORT).show();
    }

}
