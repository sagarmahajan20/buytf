package com.example.dveg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import maes.tech.intentanim.CustomIntent;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    ImageView basket;
    TextView textView;
    CarouselView carouselView;
    ImageView vegetable,fruit,herbs, flower,viewall,seasonal;
    LinearLayout username;
    TextView user_name;

    int[] sampleImages = {R.drawable.ca, R.drawable.cb, R.drawable.cc, R.drawable.cd, R.drawable.ca};

    ImageView i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //bottomnav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        return true;

                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), chatbot.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        //bottomend


        //
        vegetable = findViewById(R.id.vegetable);

        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vi = new Intent(MainActivity.this,Vegetable.class);
                vi.putExtra("type","vegetable");
                startActivity(vi);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

        herbs = findViewById(R.id.herbs);
        herbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(MainActivity.this,Vegetable.class);
                f.putExtra("type","herbs");
                startActivity(f);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

        flower = findViewById(R.id.flower);
        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(MainActivity.this,Vegetable.class);
                f.putExtra("type","flower");
                startActivity(f);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

        viewall = findViewById(R.id.viewall);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(MainActivity.this,Vegetable.class);
                f.putExtra("type","viewall");
                startActivity(f);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });


        seasonal = findViewById(R.id.seasonal);
        seasonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(MainActivity.this,Vegetable.class);
                f.putExtra("type","seasonal");
                startActivity(f);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

        fruit = findViewById(R.id.fruit);
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(MainActivity.this,Vegetable.class);
                f.putExtra("type","fruit");
                startActivity(f);
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

        basket = findViewById(R.id.basket);
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Basket.class));
                CustomIntent.customType(MainActivity.this, "left-to-right");
            }
        });

        //username set

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        user_name = findViewById(R.id.user_name);
        //user_name.setText(email);
        //userame set ed
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


        //carousel

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        //carousel end
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch(menuItem.getItemId())
        {
            case R.id.nav_home :
                Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent1);
                CustomIntent.customType(MainActivity.this, "left-to-right");
                break;


            case R.id.nav_cart :
                Intent intent2 = new Intent(MainActivity.this,Basket.class);
                startActivity(intent2);
                CustomIntent.customType(MainActivity.this, "left-to-right");
                break;

            case R.id.nav_myorders :
                Intent intent3 = new Intent(MainActivity.this,Myorders.class);
                startActivity(intent3);
                CustomIntent.customType(MainActivity.this, "left-to-right");
                break;

            case R.id.nav_profile :
                Intent intent = new Intent(MainActivity.this,UserProfile.class);
                startActivity(intent);
                CustomIntent.customType(MainActivity.this, "left-to-right");
                break;

            case R.id.nav_share :
                Toast.makeText(getApplicationContext(), "Sorry this function is not working ",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(), "Sign out Successfully ",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
                CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                break;

            case R.id.nav_rate :
                Toast.makeText(getApplicationContext(), "!! Sorry this function is not working !!",Toast.LENGTH_LONG).show();
                break;

        }


        return true;
    }





    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this, "left-to-right");
    }


}
