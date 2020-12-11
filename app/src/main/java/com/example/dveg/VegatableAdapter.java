package com.example.dveg;

import android.app.Activity;
import android.renderscript.Sampler;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VegatableAdapter extends FirestoreRecyclerAdapter<Modeladdproduct,VegatableAdapter.VegatableHolder>
{
    String useremail = Login.getData();
    FirebaseFirestore dbadd;
    FirebaseFirestore ffget;
    String[] amount = { "India", "USA", "China", "Japan", "Other"};
    int quant12 = 0;



    public VegatableAdapter(@NonNull FirestoreRecyclerOptions<Modeladdproduct> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final VegatableAdapter.VegatableHolder holder, int position, @NonNull final Modeladdproduct model) {

        holder.product_name.setText(model.getProduct_name());
        //holder.p_mrp.setText("MRP: "+model.getP_mrp());
        holder.p_rs.setText("Rs: "+model.getP_rs());
        Picasso.get()
                .load(model.getP_urlimage())
                .placeholder(R.drawable.logo1)
                .into(holder.p_urlimage);

        String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
        String target = ".";
        String replacement = "a";
        final String processed = useremail.replace(target, replacement);

        ffget = FirebaseFirestore.getInstance();

        //Query getquant = ffget.collection("vegetables").whereEqualTo("p_id",model.getP_id()).whereEqualTo(processed+".cart","true");

        ffget.collection("vegetables")
                .whereEqualTo("p_id",model.getP_id())
                .whereEqualTo(processed+".cart","true")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String poq = (String) document.get(processed+".quant").toString();
                                holder.current_quant.setText(poq+" Kg");
                            }
                        } else {
                            holder.current_quant.setText("0 Kg");
                        }
                    }
                });

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                //Toast.makeText(v.getContext(),"Added To cart",Toast.LENGTH_SHORT).show();
                dbadd = FirebaseFirestore.getInstance();

                DocumentReference updatecart = dbadd.collection("vegetables").document(model.getP_id());

                String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
                String target = ".";
                String replacement = "a";
                final String processed = useremail.replace(target, replacement);
// Set the "isCapital" field of the city 'DC'
                updatecart
                        .update(processed,"1",
                                processed+".cart","true",
                                processed+".quant", quant12)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(v.getContext(),"Added To cart",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(),"!!Failed to add cart!!",Toast.LENGTH_SHORT).show();
                            }
                        });



                dbadd.collection("vegetables")
                        .whereEqualTo("p_id",model.getP_id())
                        .whereEqualTo(processed+".cart","true")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String poq = (String) document.get(processed+".quant").toString();
                                        holder.current_quant.setText(poq+" Kg");
                                    }
                                } else {
                                    holder.current_quant.setText("0 Kg");
                                }
                            }
                        });


            }
        });





    }

    @NonNull
    @Override
    public VegatableAdapter.VegatableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vegetable_item,
                parent, false);
        return new VegatableHolder(v);
    }

    public class VegatableHolder extends RecyclerView.ViewHolder{

        private TextView p_mrp;
        private TextView p_rs;
        private ImageView p_urlimage;
        private TextView product_name,current_quant;
        Button addtocart;
        View v;
        Spinner spin;

        public VegatableHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name);
            //p_mrp = itemView.findViewById(R.id.p_mrp);
            p_rs = itemView.findViewById(R.id.p_rs);
            p_urlimage = itemView.findViewById(R.id.p_urlimage);
            current_quant = itemView.findViewById(R.id.current_quant);
            spin = (Spinner) itemView.findViewById(R.id.spinner);
            addtocart = itemView.findViewById(R.id.addtocart);
            v = itemView;


            final Integer[] amount = new Integer[] { 1, 2, 3,4,5,6,7,8,9,10};
            ArrayList<Integer> planetList = new ArrayList<Integer>();
            //planetList.addAll( planets );

            //Creating the ArrayAdapter instance having the country list
            ArrayAdapter aa;
            aa = new ArrayAdapter(itemView.getContext(),android.R.layout.simple_spinner_item, amount);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spin.setAdapter(aa);

            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {


                    //Toast.makeText(getApplicationContext(),amount[position] , Toast.LENGTH_LONG).show();
                    quant12 = amount[position];



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });






        }


    }





}
