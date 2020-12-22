package sagar.mahajan.dveg;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

public class MyorderAdapter extends FirestoreRecyclerAdapter<MyorderModel,MyorderAdapter.MyorderHolder>
{

    String useremail = Login.getData();

    public MyorderAdapter(@NonNull FirestoreRecyclerOptions<MyorderModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyorderAdapter.MyorderHolder holder, int position, @NonNull final MyorderModel model)
    {


        holder.date.setText(model.getDate().toString()
        );
        holder.total.setText("Total: "+model.total);
        holder.deleviredstatus.setText("Delevired Status: "+model.getDeleviredstatus());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passdata = new Intent(v.getContext(),Myorderdetail.class);
                passdata.putExtra("date",model.getDate());
                passdata.putExtra("deleviredstatus",model.getDeleviredstatus());
                passdata.putExtra("email",model.getEmail());
                passdata.putExtra("houseno",model.getHouseno());
                passdata.putExtra("landmark",model.getLandmark());
                passdata.putExtra("phonenumber",model.getPhonenumber());
                passdata.putExtra("pincode",model.getPincode());
                passdata.putExtra("roadname",model.getRoadname());
                passdata.putExtra("state",model.getState());
                passdata.putExtra("total",model.getTotal());
                passdata.putExtra("usermobile",model.getUsermobile());
                passdata.putExtra("username",model.getUsername());
                passdata.putStringArrayListExtra("product", (ArrayList<String>) model.getProduct());
                passdata.putStringArrayListExtra("product_quant", (ArrayList<String>) model.getProduct_quant());
                v.getContext().startActivity(passdata);
            }
        });



    }

    @NonNull
    @Override
    public MyorderAdapter.MyorderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder_item,
                parent, false);
        return new MyorderAdapter.MyorderHolder(v);



    }


    public class MyorderHolder extends RecyclerView.ViewHolder
    {

        private TextView date;
        private TextView total;
        private TextView deleviredstatus;
        View v;

        public MyorderHolder(@NonNull View itemView)
        {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            total = itemView.findViewById(R.id.total);
            deleviredstatus = itemView.findViewById(R.id.deleviredstatus);
            v = itemView;




        }



    }



}
