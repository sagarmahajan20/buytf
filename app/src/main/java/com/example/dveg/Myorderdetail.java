package com.example.dveg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Myorderdetail extends AppCompatActivity {

    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorderdetail);

        name = findViewById(R.id.nameofdata);
        String s;

        Intent passdata = getIntent();
        s =passdata.getStringExtra("product");

        name.setText(s.toString());


    }
}
