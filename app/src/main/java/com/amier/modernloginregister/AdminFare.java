package com.amier.modernloginregister;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
public class AdminFare extends AppCompatActivity {
Button btnupdate;
EditText edtfare,edtperkmfare;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminfare);
btnupdate=findViewById(R.id.btnupdate);
edtfare=findViewById(R.id.fare);
edtperkmfare=findViewById(R.id.perkmfare);
btnupdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(!edtfare.getText().toString().trim().equals("")&&!edtperkmfare.getText().toString().trim().equals(""))
        updatenow();
        else
        {
            Toast.makeText(AdminFare.this, "Fare or Per KM fare is null", Toast.LENGTH_SHORT).show();
        }
    }
});
}
public void updatenow()
{
    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://latestsnewsinfo.com/LoginRegisterApp/updatefare.php", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(AdminFare.this, ""+response, Toast.LENGTH_SHORT).show();
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(response);
                int success=jsonObject.optInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(AdminFare.this, ""+response, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Qrgenerator.this, LoginActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AdminFare.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    })
    {
        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public HashMap<String, String> getParams()
        {
            HashMap<String, String> p=new HashMap<>();
            p.put("perkmfare",edtperkmfare.getText().toString());
            p.put("fare",edtfare.getText().toString()+"");
            p.put("id",1+"");
            return p;
        }
    };
    RequestQueue requestQueue= Volley.newRequestQueue(AdminFare.this);
    requestQueue.add(stringRequest);
}
}