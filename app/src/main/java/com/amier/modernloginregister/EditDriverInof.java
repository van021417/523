package com.amier.modernloginregister;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
public class EditDriverInof extends AppCompatActivity {
    String Heroes;
    String value;
    String result;
    HttpURLConnection httpURLConnection;
    private String sharepref="modernloginregister";
    TextView  email, user;
    EditText name,address,mobile;
    SharedPreferences sharedPreferences;
    Bundle extras;
    Button btnupdate;
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnupdate=findViewById(R.id.btnupdate);
        btnupdate.setVisibility(View.VISIBLE);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateprofile();
            }
        });
       sharedPreferences=getSharedPreferences(sharepref,MODE_PRIVATE);
       value=getIntent().getStringExtra("name");
        Log.d("useremail",value
        );
        getJSON("https://latestsnewsinfo.com/LoginRegisterApp/get.php?useremail="
        +value);

        name =  findViewById(R.id.name);
        address =  findViewById(R.id.address);
        email =  findViewById(R.id.email);
        mobile =  findViewById(R.id.mobile);
        address.setEnabled(true);
        name.setEnabled(true);
        mobile.setEnabled(true);
        user = (TextView) findViewById(R.id.user);
    getprofile();
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void getJSON(final String urlWebservice) {

        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
              //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//                try {
//                    loadIntoListView(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebservice);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json).append("\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void loadIntoListView (String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            name.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            address.setVisibility(View.VISIBLE);
            mobile.setVisibility(View.VISIBLE);
            user.setVisibility(View.VISIBLE);

            name.setText(obj.getString("name"));
            email.setText(obj.getString("email"));
            address.setText(obj.getString("address"));
            mobile.setText(obj.getString("mobile"));
            user.setText(obj.getString("user"));
        }
    }
    public void getprofile()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://latestsnewsinfo.com/LoginRegisterApp/get.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        name.setVisibility(View.VISIBLE);
                        email.setVisibility(View.VISIBLE);
                        address.setVisibility(View.VISIBLE);
                        mobile.setVisibility(View.VISIBLE);
                        user.setVisibility(View.VISIBLE);
                        name.setText(obj.getString("name"));
                        email.setText(obj.getString("email"));
                        address.setText(obj.getString("address"));
                        mobile.setText(obj.getString("mobile"));
                        user.setText(obj.getString("user"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Intent i = new Intent(Qrgenerator.this, LoginActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditDriverInof.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            public HashMap<String, String> getParams()
            {
                HashMap<String, String> p=new HashMap<>();
               p.put("useremail",value);

//                p.put("level",1+"");
                return p;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(EditDriverInof.this);
        requestQueue.add(stringRequest);
    }
    public void updateprofile()
    {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://latestsnewsinfo.com/LoginRegisterApp/updateinfo.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject jsonObject= null;
                    try {
                        jsonObject = new JSONObject(response);
                        int success=jsonObject.getInt("success");
                        if(success==1)
                        {
                            Toast.makeText(EditDriverInof.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(EditDriverInof.this, "There is an issue", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
              //      Toast.makeText(EditDriverInof.this, ""+response, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Qrgenerator.this, LoginActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(EditDriverInof.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            })
            {
                @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                public HashMap<String, String> getParams()
                {
                    HashMap<String, String> p=new HashMap<>();
                    p.put("address",address.getText().toString());
                    p.put("mobile",mobile.getText().toString());
                    p.put("email",email.getText().toString());
                    p.put("name",name.getText().toString());

//                p.put("level",1+"");
                    return p;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(EditDriverInof.this);
            requestQueue.add(stringRequest);
    }
}


