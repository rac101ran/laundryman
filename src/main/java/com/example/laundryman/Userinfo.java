package com.example.laundryman;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Userinfo extends AppCompatActivity {

    FloatingActionButton button;
    Spinner s;
    TextView t;
    EditText clgname;
    DatabaseReference ref;
    FirebaseAuth auth;
    FirebaseUser currentuser;

    EditText addresstext;
    EditText postalcode;
    EditText phonetext;
    TextView tmane;

    Map<String,String> map=new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        auth=FirebaseAuth.getInstance();
        currentuser=FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users").child("Laundry Users").child(currentuser.getUid());

        button=(FloatingActionButton)findViewById(R.id.floatbutton);
        t=(TextView)findViewById(R.id.resultid);
        tmane=(TextView)findViewById(R.id.nameid);
        s=(Spinner)findViewById(R.id.spinnercollege);
        addresstext=(EditText)findViewById(R.id.Address);
        phonetext=(EditText)findViewById(R.id.phoneid);
        postalcode=(EditText)findViewById(R.id.postalid);
        clgname=(EditText)findViewById(R.id.collegenameresult);
        clgname.setVisibility(View.INVISIBLE);

        ArrayAdapter<CharSequence> arrayadapter =ArrayAdapter.createFromResource(Userinfo.this,R.array.academicinfo,android.R.layout.simple_spinner_dropdown_item);
        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(arrayadapter);

        SharedPreferences pref=Userinfo.this.getSharedPreferences("com.example.laundryman",Context.MODE_PRIVATE);
        tmane.setText(pref.getString("Name",""));

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0) {
                       clgname.setVisibility(View.INVISIBLE);
                       t.setText("  ");
                }
                if(position==1) {
                   t.setText("        Student");
                   clgname.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Userinfo.this).setIcon(R.drawable.ic_check_box_black_24dp).setTitle("Apply Changes ").setMessage("Are you sure ? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (!TextUtils.isEmpty(addresstext.getText().toString()) && !TextUtils.isEmpty(postalcode.getText().toString()) && !TextUtils.isEmpty(phonetext.getText().toString())) {

                                    SharedPreferences pref=Userinfo.this.getSharedPreferences("com.example.laundryman",Context.MODE_PRIVATE);
                                    map.put("Name",pref.getString("Name",""));
                                    map.put("Address", addresstext.getText().toString());
                                    map.put("Postal Code", postalcode.getText().toString());
                                    map.put("Phone Number", phonetext.getText().toString());
                                    map.put("College",clgname.getText().toString());
                                    ref.setValue(map);

                                    Toast.makeText(Userinfo.this, "Saving Data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No",null).show();
                Toast.makeText(Userinfo.this,"Yes",Toast.LENGTH_SHORT).show();
            }
        });

        InputMethodManager inputesc=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
         inputesc.hideSoftInputFromWindow(phonetext.getWindowToken(),0);
    }
   public void photoselect(View view) {

        Intent i=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i,3);
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3 && resultCode==-1) {
                try {

                    ImageView i=findViewById(R.id.photoid);
                    i.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData()));

                }catch(Exception e) {
                        e.printStackTrace();
                }

        }
    }
}

