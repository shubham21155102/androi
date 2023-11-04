package com.example.payfmcw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log; // Import Log
import android.widget.TextView; // Import TextView
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TextView textView = findViewById(R.id.data);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://my-json-server.typicode.com/shubham21155102/demo/datas";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parse the JSON array
                            JSONArray jsonArray = new JSONArray(response);

                            // Create a StringBuilder to build the concatenated string
                            StringBuilder itemsText = new StringBuilder();

                            // Loop through the JSON array
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                // Extract data from each JSON object
                                String userId = jsonObject.getString("name");
                                String Id = jsonObject.getString("email");
                                String title = jsonObject.getString("phone");
                                String address=jsonObject.getString("address");
                                String streetAddress=jsonObject.getString("streetAddress");
                                String city=jsonObject.getString("city");
                                String state=jsonObject.getString("state");
                                String pinCode=jsonObject.getString("pinCode");
                                String gender=jsonObject.getString("gender");

                                // Append the extracted data to the StringBuilder
                                itemsText.append("Name: ").append(userId).append("\n");
                                itemsText.append("Email: ").append(Id).append("\n");
                                itemsText.append("Phone: ").append(title).append("\n");
                                itemsText.append("Address: ").append(address).append("\n");
                                itemsText.append("Street Address: ").append(streetAddress).append("\n");
                                itemsText.append("City: ").append(city).append("\n");
                                itemsText.append("Pin Code: ").append(pinCode).append("\n");
                                itemsText.append("Gender: ").append(gender).append("\n");
                                itemsText.append("State: ").append(state).append("\n\n");

                            }

                            // Set the concatenated string in the TextView
                            textView.setText(itemsText.toString());
                        } catch (JSONException e) {
                            // Handle JSON parsing errors, if any
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't worked!");
            }
        });

        queue.add(stringRequest);
    }
}
