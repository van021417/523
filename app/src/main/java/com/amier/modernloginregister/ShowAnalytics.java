package com.amier.modernloginregister;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.HashMap;

public class ShowAnalytics extends AppCompatActivity {
    TextView totalrides;
    TextView fare;
    DatePicker datepicker;
    String dateofride;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_analytics);
        fare=findViewById(R.id.fare);
        totalrides=findViewById(R.id.totalrides);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDialog(999);
    }
    public  void analytics(final String date)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://latestsnewsinfo.com/LoginRegisterApp/analytics.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int totalride=0,totalfare=0;
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Toast.makeText(ShowAnalytics.this, ""+obj, Toast.LENGTH_SHORT).show();
                        if(fare.equals("0"))
                        {

                        }
                        else
                        {
                            String fare=obj.optString("fare");
                            String passenger=obj.optString("noofpassenger");
                            totalride=totalride+Integer.parseInt(passenger);
                            totalfare=totalfare+Integer.parseInt(fare);
                        }

                    }
                    totalrides.setText(totalride+"");
                    fare.setText(totalfare+"");

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
                Toast.makeText(ShowAnalytics.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            public HashMap<String, String> getParams()
            {
                HashMap<String, String> p=new HashMap<>();
                p.put("date",date);

//                p.put("level",1+"");
                return p;
            }
        }
                ;
        RequestQueue requestQueue= Volley.newRequestQueue(ShowAnalytics.this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        analytics(String.valueOf(new StringBuilder().append(year).append("-")
                .append(month).append("/").append(day)));
    }
}