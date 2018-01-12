package com.example.malavshah.materialcollapsingtoolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView bikecard, boatcard, buscard, carcard, flightcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bikecard = (CardView) findViewById(R.id.bike_card);
        boatcard = (CardView) findViewById(R.id.boats_card);
        buscard = (CardView) findViewById(R.id.bus_card);
        carcard = (CardView) findViewById(R.id.cars_card);
        flightcard = (CardView) findViewById(R.id.flights_card);

        bikecard.setOnClickListener(this);
        boatcard.setOnClickListener(this);
        buscard.setOnClickListener(this);
        carcard.setOnClickListener(this);
        flightcard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.bike_card : i = new Intent(this, BikeActivity.class); startActivity(i); break;
            case R.id.boats_card : i = new Intent(this, BoatActivity.class); startActivity(i);break;
            case R.id.bus_card : i = new Intent(this, BusActivity.class); startActivity(i);break;
            case R.id.cars_card : i = new Intent(this, CarActivity.class);startActivity(i); break;
            case R.id.flights_card : i = new Intent(this, FlightActivity.class); startActivity(i);break;

        }

    }
}
