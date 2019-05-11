package com.example.carel.mapex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AcercaDeNosotros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de_nosotros);
    }

    public void MapsActivityLaunch(View view){
        Intent maps_activity = new Intent(this, MapsActivity.class);
        startActivity(maps_activity);
    }
}
