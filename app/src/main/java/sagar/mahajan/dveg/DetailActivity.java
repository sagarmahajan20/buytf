package sagar.mahajan.dveg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;



public class DetailActivity extends AppCompatActivity {

    FirebaseFirestore db;
    TextView product_name;
    ImageView product_image;
    //private static String pquantity;
    TextView product_mrp;
    Button add_to_cart;
    Button basket;
    final String useremail = Login.getData();
    String[] stringArray2 = {"all","Cultivator","Weeder","Sprayer","Driller","Gardner"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        db = FirebaseFirestore.getInstance();
        product_name = findViewById(R.id.product_name);
        product_image = findViewById(R.id.product_image);
        product_mrp = findViewById(R.id.product_mrp);
        add_to_cart = findViewById(R.id.add_to_cart);
        basket = findViewById(R.id.basket);


        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent basket = new Intent(DetailActivity.this, Basket.class);
                startActivity(basket);

            }
        });


        



        //Intent intent = getIntent();
        //final String name = intent.getStringExtra(vegetable_name);
        //final String url = intent.getStringExtra(imageurl);
        //final String mrp1 = intent.getStringExtra(mrp);
        //final String rs1 = intent.getStringExtra(rs);
        //product_name.setText(name);
        //product_mrp.setText("MRP : "+mrp1);
        //Picasso.get().load(url).into(product_image);

        //final String quant = product_quantity.getText().toString().trim();



    }

    @Override
    public void onBackPressed() {
        Intent ia = new Intent(DetailActivity.this, Vegetable.class);
        startActivity(ia);

    }
}
