package sagar.mahajan.dveg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Myorders extends AppCompatActivity {

    RecyclerView morecyclerview;
    FirebaseFirestore myorder;
    FirestoreRecyclerAdapter adapter;
    final String useremail = Login.getData();
    Spinner payment;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);




        String string = useremail.replaceAll("([^.@\\s]+)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", "");
        String target = ".";
        String replacement = "a";
        final String processed = useremail.replace(target, replacement);

        morecyclerview = findViewById(R.id.myordersrecycler);

        myorder = FirebaseFirestore.getInstance();
        Query query = myorder.collection("orders").whereEqualTo("email",useremail);


        FirestoreRecyclerOptions<MyorderModel> options = new FirestoreRecyclerOptions.Builder<MyorderModel>()
                .setQuery(query,MyorderModel.class)
                .build();

        adapter  = new MyorderAdapter(options);

        morecyclerview.setHasFixedSize(true);
        morecyclerview.setLayoutManager(new LinearLayoutManager(this));
        morecyclerview.setAdapter(adapter);



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



}
