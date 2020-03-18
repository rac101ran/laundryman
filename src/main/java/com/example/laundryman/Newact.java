package com.example.laundryman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Newact extends AppCompatActivity {
    int images[]={R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,R.drawable.download,
            R.drawable.download,R.drawable.download};
  String Titlemain[];
   String Descmain[];
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newact);
        recyclerView=(RecyclerView)findViewById(R.id.rec);
        Titlemain=getResources().getStringArray(R.array.Title);
        Descmain=getResources().getStringArray(R.array.desc);
        MyAdapter adapter=new MyAdapter(this,images,Titlemain,Descmain);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
