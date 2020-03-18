package com.example.laundryman.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryman.M;
import com.example.laundryman.N;
import com.example.laundryman.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.InnerAdapter> {
    FirebaseAuth auth;
    DatabaseReference ref,refself;
    FirebaseUser user;
    Map<String,String> map;
    Context c;
    String Titlemain[];
    String Descmain[];
    int[] images;

    public NewAdapter(Context context, String Title[], String Desc[], int[] image) {
        c = context;
        Titlemain = Title;
        Descmain = Desc;
        images = image;

    }

    @NonNull
    @Override
    public InnerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View v = layoutInflater.inflate(R.layout.row, parent, false);
        return new InnerAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerAdapter holder, final int position) {


        holder.title.setText(Titlemain[position]);
        holder.img.setImageResource(images[position]);
        holder.DESC.setText(Descmain[position]);
        holder.ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0) {
                    map=new HashMap<>();
                    auth= FirebaseAuth.getInstance();
                    user=FirebaseAuth.getInstance().getCurrentUser();
                    ref= FirebaseDatabase.getInstance().getReference("Users").child("Laundry Owners").child(Titlemain[position]);

                    refself=FirebaseDatabase.getInstance().getReference("Users").child("Laundry Users").child(user.getUid());
                    refself.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            map.put(user.getUid(),dataSnapshot.child("Cost").getValue().toString());
                            ref.setValue(map);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Intent i = new Intent(c, N.class);
                    c.startActivity(i);
                }
                if(position==1) {
                    map=new HashMap<>();
                    auth= FirebaseAuth.getInstance();
                    user=FirebaseAuth.getInstance().getCurrentUser();
                    ref= FirebaseDatabase.getInstance().getReference("Users").child("Laundry Owners").child(Titlemain[position]);

                    refself=FirebaseDatabase.getInstance().getReference("Users").child("Laundry Users").child(user.getUid());
                    refself.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            map.put(user.getUid(),dataSnapshot.child("Cost").getValue().toString());
                            ref.setValue(map);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                      Intent i2=new Intent(c, M.class);
                      c.startActivity(i2);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class InnerAdapter extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        TextView DESC;
       ConstraintLayout  ct;
        public InnerAdapter(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleid2);
            DESC = itemView.findViewById(R.id.descid2);
            img = itemView.findViewById(R.id.imageView9);
            ct=itemView.findViewById(R.id.rowid);
        }
    }
}
