package com.example.laundryman;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
              if(destination.getId()==R.id.navigation_dashboard) {
                     Intent i=new Intent(MainActivity.this,Laundry.class);
                     startActivity(i);
              }
              if(destination.getId()==R.id.navigation_notifications) {
                 Intent i=new Intent(MainActivity.this,Newact.class);
                 startActivity(i);
              }
            }
        });
        new AlertDialog.Builder(MainActivity.this).setIcon(R.drawable.ic_announcement_black_24dp).setTitle("Before Experiencing LaundryMan").setMessage("Would you like to fill rest of your necessary details?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      Intent i=new Intent(MainActivity.this,Userinfo.class);
                      startActivity(i);
                    }
                }).setNegativeButton("No",null).show();

              }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          switch(item.getItemId()) {

              case R.id.logout:
                  auth.signOut();
                  Intent i=new Intent(MainActivity.this,Welcomepage.class);
                  startActivity(i);
                  return true;

              case R.id.LaundryMen:
                  Intent i2=new Intent(MainActivity.this,Owners.class);
                  startActivity(i2);
                  return true;

              case R.id.settingid:
                  Intent i3=new Intent(MainActivity.this,Settingspage.class);
                  startActivity(i3);

          }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser==null) {
               Intent i=new Intent(MainActivity.this,Welcomepage.class);
               startActivity(i);
               finish();
        }
    }

}
