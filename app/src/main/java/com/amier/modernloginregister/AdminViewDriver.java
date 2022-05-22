package com.amier.modernloginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminViewDriver extends AppCompatActivity {
    ArrayList<String> arr;
    ArrayAdapter<String> adapter;
    String url ="";
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdriver);
        listView=findViewById(R.id.listview);
        arr=new ArrayList<>();


        url="https://latestsnewsinfo.com/LoginRegisterApp/getdriverdata.php";
        showdriverdata();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(AdminViewDriver.this,EditDriverInof.class);
                intent.putExtra("name",arr.get(i).toString());
                startActivity(intent);
            }
        });
    }
    public  void showdriverdata()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AdminViewDriver.this, ""+response, Toast.LENGTH_SHORT).show();
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                       arr.add(obj.getString("name"));

//                        name.setText(obj.getString("name"));
//                        email.setText(obj.getString("email"));
//                        address.setText(obj.getString("address"));
//                        mobile.setText(obj.getString("mobile"));
//                        user.setText(obj.getString("user"));
                    }
                    adapter=new ArrayAdapter<>(AdminViewDriver.this, android.R.layout.simple_list_item_1,arr);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(AdminViewDriver.this, ""+response, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Qrgenerator.this, LoginActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminViewDriver.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(AdminViewDriver.this);
        requestQueue.add(stringRequest);
    }
}
