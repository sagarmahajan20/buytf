package com.example.dveg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Placeorder extends AppCompatActivity {

    EditText pincode,houseno, roadname,city,state,landmark,username,usermobile,phonenumber;
    TextView totalcost;
    Button confirmorder;
    FirebaseFirestore co;
    final String useremail = Login.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder);


        pincode = findViewById(R.id.pincode);
        houseno = findViewById(R.id.houseno);
        roadname = findViewById(R.id.roadname);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        landmark = findViewById(R.id.landmark);
        username = findViewById(R.id.username);
        usermobile = findViewById(R.id.usermobile);
        phonenumber = findViewById(R.id.phonenumber);


        totalcost = findViewById(R.id.totalcost);

        confirmorder = findViewById(R.id.confirmorder);
        co = FirebaseFirestore.getInstance();

        String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
        String target = ".";
        String replacement = "a";
        final String processed = useremail.replace(target, replacement);



        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String pincode1 = pincode.getText().toString().trim();
                String houseno1 = houseno.getText().toString().trim();
                String roadname1 = roadname.getText().toString().trim();
                String city1 = city.getText().toString().trim();
                String state1 = state.getText().toString().trim();
                String landmark1 = landmark.getText().toString().trim();
                String username1 = username.getText().toString().trim();
                String usermobile1 = usermobile.getText().toString().trim();
                String phonenumber1 = phonenumber.getText().toString().trim();

                if(TextUtils.isEmpty(pincode1)){
                    pincode.setError("Pincode is Required.");
                    return;
                }

                if(TextUtils.isEmpty(houseno1)){
                    houseno.setError("House Number is Required.");
                    return;
                }

                if(TextUtils.isEmpty(roadname1)){
                    roadname.setError("Pincode is Required.");
                    return;
                }

                if(TextUtils.isEmpty(city1)){
                    city.setError("City is Required.");
                    return;
                }

                if(TextUtils.isEmpty(state1)){
                    state.setError("State is Required.");
                    return;
                }

                if(TextUtils.isEmpty(landmark1)){
                    landmark.setError("landmark is Required.");
                    return;
                }

                if(TextUtils.isEmpty(username1)){
                    username.setError("User Name is Required.");
                    return;
                }

                if(TextUtils.isEmpty(usermobile1)){
                    usermobile.setError("Pincode is Required.");
                    return;
                }

                //Query query1 = co.collection("vegetables").whereEqualTo(processed+".cart","true");
                Query updatecart = co.collection("vegetables").whereEqualTo(processed+".cart","true");

                String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
                String target = ".";
                String replacement = "a";
                final String processed = useremail.replace(target, replacement);

                final String id = co.collection("orders").document().getId();

                final String idd = "1";
                final Map<String, Object> city = new HashMap<>();
                city.put("pincode", pincode1);
                city.put("houseno", houseno1);
                city.put("roadname", roadname1);
                city.put("state", state1);
                city.put("landmark", landmark1);
                city.put("username", username1);
                city.put("usermobile", usermobile1);
                city.put("email", useremail);
                city.put("phonenumber", phonenumber1);
                city.put("date", new Timestamp(new Date()));
                city.put("total", "101");
                city.put("deleviredstatus", "delevired");

                //city.put("country", "USA");


                co.collection("orders").document(id)
                        .set(city)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Log.w(TAG, "Error writing document", e);
                            }
                        });



                updatecart.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        int total = 0;
                        int total1 = 0;
                        for (QueryDocumentSnapshot document : task.getResult())
                        {
                            //String str = document.getString("p_rs");
                            String pid = document.getString("p_id");
                            //String mrp = document.getString(processed+".quant");
                            //int itemCost = Integer.parseInt(str);
                            //int realmrp = Integer.parseInt(mrp);
                            ///total =total+ itemCost*realmrp;

                            String update = document.getString(processed+".cart");

                            DocumentReference updatecart1 = co.collection("vegetables").document(pid);

                            updatecart1
                                    .update(processed,"1",
                                            processed+".cart","false"
                                           )
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(),"Order placed Successfully",Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(),"!!Order placed Failed!!",Toast.LENGTH_SHORT).show();
                                        }
                                    });


                             Map<String, Object> city1 = new HashMap<>();
                            city1.put("product", FieldValue.arrayUnion(document.getString("product_name")));
                            city1.put("product_quant", FieldValue.arrayUnion(document.getString("p_rs")));
                            co.collection("orders").document(id)
                                    .set(city1, SetOptions.merge());





                        }
                        Log.d("TAG", String.valueOf(total));
                        totalcost.setText(String.valueOf("MRP "+total1));
                    }

                    Intent sa = new Intent(Placeorder.this,Myorders.class);
                    startActivity(sa);

                }
            });


// Set the "isCapital" field of the city 'DC'
               /* updatecart
                        .set(processed,"1",
                                processed+".cart","false",
                                processed+".confirmorder", "true")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Order placed Successfully",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"order placed failed",Toast.LENGTH_SHORT).show();
                            }
                        });*/








            }
        });

    }
}
