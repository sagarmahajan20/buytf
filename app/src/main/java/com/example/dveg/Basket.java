package com.example.dveg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    final String useremail = Login.getData();
    FirebaseFirestore db;
    FirebaseFirestore dbadd;
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter adapter;
    Button placeorder;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView totalrs;
    TextView totalmrp;
    Double total;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


        //totalmrp = findViewById(R.id.totalmrp);
        totalrs = findViewById(R.id.totalrs);

        //nav start
        //nav start
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
//        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        //nav end


        //nav end



        firebaseFirestore  = FirebaseFirestore.getInstance();
        db  = FirebaseFirestore.getInstance();
        RecyclerView mFirestoreList = findViewById(R.id.recycler_view_basket);

        String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
        String target = ".";
        String replacement = "a";
        final String processed = useremail.replace(target, replacement);



        Query query = db.collection("vegetables").whereEqualTo(processed+".cart","true");

        FirestoreRecyclerOptions<BasketModel> options = new FirestoreRecyclerOptions.Builder<BasketModel>()
                .setQuery(query,BasketModel.class)
                .build();

        adapter  = new BasketAdapter(options);

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);


        dbadd  = FirebaseFirestore.getInstance();
        Query query1 = dbadd.collection("vegetables").whereEqualTo(processed+".cart","true");
        query1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    total = 0.0;
                    Double total1 = 0.0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String str = document.getString("p_rs");
                        Double mrp = document.getDouble(processed+".quant");
                        Double itemCost = Double.parseDouble(str);
                        //int realmrp = Integer.parseInt(mrp);
                        total =total+ itemCost*mrp;


                        String str1 = document.getString("p_mrp");
                        Double mrp1 = document.getDouble(processed+".quant");
                        Double itemCost1 = Double.parseDouble(str);
                        total1 =total1+ itemCost1*mrp1;


                    }
                    Log.d("TAG", String.valueOf(total));
                    totalrs.setText(String.valueOf("Rs. "+total));
                }
            }
        });



        placeorder = findViewById(R.id.placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //firestore user add to order document

                Intent gotocheck = new Intent(Basket.this,Placeorder.class);
                startActivity(gotocheck);



            }
        });






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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId())
        {
            case R.id.nav_home :
                break;


            case R.id.nav_cart :
                break;

            case R.id.nav_share :
                Toast.makeText(getApplicationContext(), "Setting panel ",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(), "Sign out Successfully ",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Basket.this,Login.class);
                startActivity(i);
                break;
        }


        return true;
    }
}
