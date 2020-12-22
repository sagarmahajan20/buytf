package sagar.mahajan.dveg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

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
    private List<Modeladdcart> modeladdcart;
    SearchView searchView;
    FirestoreRecyclerAdapter adapter;
    private VegatableAdapter mAdapter;
    private DatabaseReference sDatabaseRef;
    private DatabaseReference mDatabaseRef;
    private List<Modeladdproduct> Modeladdproduct;
    String collection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable);

        modeladdcart = new ArrayList<>();
        searchView = findViewById(R.id.searchView);
        firebaseFirestore  = FirebaseFirestore.getInstance();
        db  = FirebaseFirestore.getInstance();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Modeladdproduct= new ArrayList<>();


        basket = findViewById(R.id.basket);
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Vegetable.this,Basket.class));
            }
        });

        Intent vpd = getIntent();

        collection = vpd.getStringExtra("type");

        Query query = db.collection("vegetables").whereEqualTo("type",collection);

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

        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {

                    search(s);
                    return true;
                }


            });
        }


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



    private void search(String str)
    {
        ArrayList<Modeladdcart> myList = new ArrayList<>();
        for(Modeladdcart object : modeladdcart)
        {
            if(object.getP_name().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }



       //VegatableAdapter searchimage = new VegatableAdapter(options1,Vegetable.this,myList);
        //mRecyclerView.setAdapter(searchimage);






    }

}
