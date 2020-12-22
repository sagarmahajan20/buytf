package sagar.mahajan.dveg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;

public class Myorderdetail extends AppCompatActivity {

    TextView name,mobile,address,totalcostitem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorderdetail);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        totalcostitem = findViewById(R.id.totalcostitem);

        String date;
        String deleviredstatus;
        String email;
        String houseno;
        String landmark;
        String phonenumber;
        String pincode;
        String roadname;
        String state;
        String total;
        String usermobile;
        String username;

        Intent passdata = getIntent();
        date = passdata.getStringExtra("date");
        deleviredstatus = passdata.getStringExtra("deleviredstatus");
        email = passdata.getStringExtra("email");
        houseno = passdata.getStringExtra("houseno");
        landmark =passdata.getStringExtra("landmark");
        phonenumber = passdata.getStringExtra("phonenumber");
        pincode = passdata.getStringExtra("pincode");
        roadname = passdata.getStringExtra("roadname");
        state = passdata.getStringExtra("state");
        total = passdata.getStringExtra("total");
        usermobile = passdata.getStringExtra("usermobile");
        username = passdata.getStringExtra("username");


        name.setText("Name: "+username);
        mobile.setText("Mobile: "+usermobile);
        address.setText("Address: "+landmark +" "+houseno+" "+ state);
        totalcostitem.setText("Total: "+total);

        ArrayList<String> product = getIntent().getStringArrayListExtra("product");
        ArrayAdapter modadapter = new ArrayAdapter<String>(this,R.layout.myorderdetail_item,product);
        ListView listview  = findViewById(R.id.listview);
        listview.setAdapter(modadapter);


        ArrayList<String> product_quant = getIntent().getStringArrayListExtra("product_quant");
        ArrayAdapter modadapter1 = new ArrayAdapter<String>(this,R.layout.myorderdetail_item,product_quant);
        ListView listview1  = findViewById(R.id.listview1);
        listview1.setAdapter(modadapter1);


    }
}
