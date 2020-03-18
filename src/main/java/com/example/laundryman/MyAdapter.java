package com.example.laundryman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    int []images;
   String Title[];
  String Desc[];
    MyAdapter(Context c,int img[],String title[],String[] desc) {
        context=c;
        images=img;
        Title=title;
        Desc=desc;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=  layoutInflater.inflate(R.layout.newlay,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                 holder.texttitle.setText(Title[position]);
                 holder.textdesc.setText(Desc[position]);
                 holder.imagOwer.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView texttitle;
        TextView textdesc;
        ImageView imagOwer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textdesc=itemView.findViewById(R.id.descid2);
            texttitle=itemView.findViewById(R.id.titleid2);
            imagOwer=itemView.findViewById(R.id.imageid);

        }
    }



}
