package com.amier.modernloginregister;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class AdminDB extends AppCompatActivity implements View.OnClickListener {

    public CardView cardFare, cardDriver, cardViewDriver,cardSettings,userrider,analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindb);

        cardFare = findViewById(R.id.editFare);
        cardDriver = findViewById(R.id.editDriver);
        analytics=findViewById(R.id.cardAnalytics);
        cardViewDriver = findViewById(R.id.viewDriver);
        userrider=findViewById(R.id.viewusers);
        cardSettings = findViewById(R.id.cardSettings);
        cardFare.setOnClickListener(view -> showToast("Edit Fare"));
        cardDriver.setOnClickListener(view -> showToast("Add Driver"));
        cardViewDriver.setOnClickListener(view -> showToast("View Driver"));
        cardSettings.setOnClickListener(view -> showToast("Settings"));

        cardFare.setOnClickListener(this);

        cardDriver.setOnClickListener(this);
        cardViewDriver.setOnClickListener(this);
        analytics.setOnClickListener(this);
        userrider.setOnClickListener(this);
        cardSettings.setOnClickListener(this);
    }

    private void showToast(String message) {
        Toast.makeText(this, message


                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        Intent i;

        if (id == cardFare.getId()) {
            i = new Intent(this, AdminFare.class);
            startActivity(i);
        } else if (id == cardDriver.getId()) {
            i = new Intent(this, AdminDriver.class);
            startActivity(i);
        } else if (id == cardViewDriver.getId()) {
            i = new Intent(this, AdminViewDriver.class);
            startActivity(i);
        }
        else if (id == userrider.getId()) {
            i = new Intent(this, ViewUsersRides.class);
            startActivity(i);
        }
        else if (id == analytics.getId()) {
            i = new Intent(this, ShowAnalytics.class);
            startActivity(i);
        }
        else if (id == cardSettings.getId()) {
            i = new Intent(this, Settings.class);
            startActivity(i);
        }
    }
}