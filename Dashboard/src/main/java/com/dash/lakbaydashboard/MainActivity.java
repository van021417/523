
package com.dash.lakbaydashboard;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView cardProf, cardSettings, cardEwallet, cardTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardProf = findViewById(R.id.cardProf);
        cardSettings = findViewById(R.id.cardSettings);
        cardEwallet = findViewById(R.id.cardEwallet);
        cardTransaction = findViewById(R.id.cardTransaction);


        cardProf.setOnClickListener(view -> showToast("Profile"));
        cardSettings.setOnClickListener(view -> showToast("Settings"));
        cardEwallet.setOnClickListener(view -> showToast("E-Wallet"));
        cardTransaction.setOnClickListener(view -> showToast("Transaction History"));
        cardProf.setOnClickListener(this);
        cardSettings.setOnClickListener(this);
        cardEwallet.setOnClickListener(this);
        cardTransaction.setOnClickListener(this);

    }
    private void showToast(String message){
        Toast.makeText(this, message


                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        Intent i;

        if (id == cardProf.getId()){
            i= new Intent(this,Profile.class);
            startActivity(i);
        } else if (id == cardEwallet.getId()) {
            i= new Intent(this,EWallet.class);
            startActivity(i);
        } else if (id == cardTransaction.getId()) {
            i= new Intent(this,TransactionHistory.class);
            startActivity(i);
        } else if (id == cardSettings.getId()) {
            i= new Intent(this,Settings.class);
            startActivity(i);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.aboutUs){
            Intent myIntent = new Intent(MainActivity.this,
                    AboutUs.class);
            startActivity(myIntent);
            Toast.makeText(this, "About us selected", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.logout){
            Toast.makeText(this, "Log-out", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}

