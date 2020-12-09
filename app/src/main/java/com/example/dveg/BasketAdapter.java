package com.example.dveg;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasketAdapter extends FirestoreRecyclerAdapter<BasketModel,BasketAdapter.BasketHolder>
{

    int quant12 = 0;
    String useremail = Login.getData();
    FirebaseFirestore dbadd1;
    FirebaseFirestore dbadd2;

    public BasketAdapter(@NonNull FirestoreRecyclerOptions<BasketModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BasketHolder holder, int position, @NonNull final BasketModel model) {
        holder.p_rs.setText("Rs: "+model.getP_rs());
        holder.p_mrp.setText("MRP: "+model.getP_mrp());
        holder.product_name.setText(model.getProduct_name());

        Picasso.get()
                .load(model.getP_urlimage())
                .into(holder.p_urlimage);


        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                //Toast.makeText(v.getContext(),"Added To cart",Toast.LENGTH_SHORT).show();
                dbadd1 = FirebaseFirestore.getInstance();

                DocumentReference updatecart = dbadd1.collection("vegetables").document(model.getP_id());

                String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
                String target = ".";
                String replacement = "a";
                String processed = useremail.replace(target, replacement);
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
                                Toast.makeText(v.getContext(),"Added To cart",Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });


        holder.deletebasketitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {

                dbadd2 = FirebaseFirestore.getInstance();


                String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
                String target = ".";
                String replacement = "a";
                final String processed = useremail.replace(target, replacement);

                String iaaa = model.getP_id();

                DocumentReference updatecart = dbadd2.collection("vegetables").document(iaaa);

                updatecart
                        .update(processed,"1",
                                processed+".cart","false"
                        )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(v.getContext(),"Item Deleted",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(),"!!Item deletion Failed!!",Toast.LENGTH_SHORT).show();
                            }
                        });







            }
        });



    }



    @NonNull
    @Override
    public BasketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item,
                parent, false);
        return new BasketHolder(v);
    }


    public class BasketHolder extends RecyclerView.ViewHolder {


        private TextView p_mrp;
        private TextView p_rs;
        private TextView product_name;
        private ImageView p_urlimage;
        Button updatecart;
        Button deletebasketitem;
        Spinner spin;
        View v;

        public BasketHolder(@NonNull View itemView) {
            super(itemView);

            p_mrp = itemView.findViewById(R.id.p_mrp);
            p_rs = itemView.findViewById(R.id.p_rs);
            spin = (Spinner) itemView.findViewById(R.id.spinner1);
            product_name = itemView.findViewById(R.id.product_name);
            p_urlimage = itemView.findViewById(R.id.p_urlimage);
            updatecart = itemView.findViewById(R.id.updatecart);
            deletebasketitem = itemView.findViewById(R.id.deletebasketitem);
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
