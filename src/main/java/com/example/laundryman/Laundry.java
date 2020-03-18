package com.example.laundryman;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Laundry extends AppCompatActivity {


    DatabaseReference reference,ref2;
    FirebaseUser currentuser;
    FirebaseAuth auth;
  static   SharedPreferences preferences;

    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;

    static int totalclothes = 0;
    static int totalclothespants = 0;
    static int getTotalclothesothers=0;

    Button laundryb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);

        laundryb=(Button)findViewById(R.id.laundrysendbutton);

        auth= FirebaseAuth.getInstance();
        currentuser=FirebaseAuth.getInstance().getCurrentUser();


        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Shirts, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Pants, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Others, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 1:
                        totalclothes += position * 10;
                        //Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

                    case 2:
                        totalclothes += position * 10;
                        //  Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

                    case 3:
                        totalclothes += position * 10;
                        //  Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

                    case 4:
                        totalclothes+=position*10;

                    case 5:
                        totalclothes+=position*10;

                    case 6:
                        totalclothes+=position*10;

                    case 7:
                        totalclothes+=position*10;

                    case 8:
                        totalclothes+=position*10;

                    case 9:
                        totalclothes+=position*10;


                }

               //    map.put("Total shirts",String.valueOf(totalclothes));
                Toast.makeText(Laundry.this, String.valueOf(totalclothes) +" Rupees", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 1:
                        totalclothespants += position * 15;
                        //  Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

                    case 2:
                        totalclothespants += position * 15;
                        // Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

                    case 3:
                        totalclothespants += position * 15;
                        // Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

                    case 4:
                        totalclothespants+=position*10;

                    case 5:
                        totalclothespants+=position*10;

                    case 6:
                        totalclothespants+=position*10;

                    case 7:
                        totalclothespants+=position*10;

                    case 8:
                        totalclothespants+=position*10;

                    case 9:
                        totalclothespants+=position*10;

                }
                Toast.makeText(Laundry.this, String.valueOf(totalclothespants)+" Rupees", Toast.LENGTH_SHORT).show();
              //  map.put("Total shirts",String.valueOf(totalclothespants)+" Rupees");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //  Toast.makeText(Laundry.this,String.valueOf(totalclothes+totalclothespants),Toast.LENGTH_SHORT).show();

       spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
        switch (position) {

            case 1:
                getTotalclothesothers += position * 10;
                //Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

            case 2:
                getTotalclothesothers += position * 10;
                //  Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();

            case 3:
               getTotalclothesothers+= position * 10;
                //  Toast.makeText(Laundry.this, String.valueOf(totalclothes), Toast.LENGTH_SHORT).show();
            case 4:
                getTotalclothesothers+=position*10;

            case 5:
                getTotalclothesothers+=position*10;

            case 6:
                getTotalclothesothers+=position*10;

            case 7:
                getTotalclothesothers+=position*10;

            case 8:
                getTotalclothesothers+=position*10;

            case 9:
                getTotalclothesothers+=position*10;
        }
        Toast.makeText(Laundry.this, String.valueOf(getTotalclothesothers)+" Rupees", Toast.LENGTH_SHORT).show();
       //     map.put("Total shirts",String.valueOf(totalclothespants));

    }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){

    }
    });

laundryb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Map<String,String> map=new HashMap<>();
        String cost=String.valueOf((totalclothes+getTotalclothesothers+totalclothespants));
        reference= FirebaseDatabase.getInstance().getReference("Users").child("Laundry Users").child(currentuser.getUid());
        map.put("Cost",cost);
        reference.setValue(map);
        Toast.makeText(Laundry.this,cost+" Rupees", Toast.LENGTH_SHORT).show();

         Intent i=new Intent(Laundry.this,Owners.class);
         startActivity(i);

    }
});


}




}
