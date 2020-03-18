package com.example.laundryman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.laundryman.ui.NewAdapter;

public class Owners extends AppCompatActivity {
    RecyclerView recyclerView;
int images[]={R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,
        R.drawable.download,R.drawable.download};
String title[];
String description[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners);
        recyclerView=findViewById(R.id.recycleid);
        title=getResources().getStringArray(R.array.Title);
        description=getResources().getStringArray(R.array.desc);
        NewAdapter newAdapter=new NewAdapter(this,title,description,images);
        recyclerView.setAdapter(newAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
