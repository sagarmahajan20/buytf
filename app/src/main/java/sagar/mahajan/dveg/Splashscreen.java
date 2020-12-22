package sagar.mahajan.dveg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class Splashscreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent gi = new Intent(Splashscreen.this,Login.class);
                startActivity(gi);
                finish();
            }
        },SPLASH_TIME_OUT);


    }
}
