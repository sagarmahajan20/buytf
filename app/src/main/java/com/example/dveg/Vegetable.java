package com.example.dveg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class Vegetable extends AppCompatActivity
{

    ImageView basket;
    FirebaseFirestore db;
    FirebaseFirestore firebaseFirestore;
    private RecyclerView mRecyclerView;
    private ImageView log;
    FirestoreRecyclerAdapter adapter;
    private VegatableAdapter mAdapter;
    private DatabaseReference sDatabaseRef;
    private DatabaseReference mDatabaseRef;
    private List<Modeladdproduct> Modeladdproduct;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable);


        firebaseFirestore  = FirebaseFirestore.getInstance();
        db  = FirebaseFirestore.getInstance();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Modeladdproduct= new ArrayList<>();


        basket = findViewById(R.id.basketo);
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Vegetable.this,Basket.class));
            }
        });

        Query query = db.collection("vegetables");

        FirestoreRecyclerOptions<Modeladdproduct> options = new FirestoreRecyclerOptions.Builder<Modeladdproduct>()
                .setQuery(query,Modeladdproduct.class)
                .build();

        adapter  = new VegatableAdapter(options);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);




    }




    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public void onBackPressed()
    {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this, "left-to-right");
    }


}
