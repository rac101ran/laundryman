package com.example.laundryman;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
    }
    public void signupclick(View view) {
        Intent i=new Intent(Welcomepage.this,Signuppage.class);
        startActivity(i);

    }

    public void loginclick(View view) {
        Intent i=new Intent(Welcomepage.this,Loginpage.class);
        startActivity(i);

    }
    public void laundryadmin(View view) {
        Intent i=new Intent(Welcomepage.this,Adminpage.class);
        startActivity(i);
    }
}
