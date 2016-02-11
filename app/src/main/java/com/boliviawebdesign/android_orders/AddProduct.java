package com.boliviawebdesign.android_orders;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class AddProduct extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Button btnAddProduct = (Button) this.findViewById(R.id.btnAddProduct);

        final EditText editTextCode = (EditText) findViewById(R.id.editTextCode);
        final EditText editTextName = (EditText) findViewById(R.id.editTextName);
        final EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String params[] = {editTextCode.getText().toString(), editTextName.getText().toString(), editTextDescription.getText().toString()};

                //String params[] = {"33DFG", "HELLO NAME", "LOREM IPSUM ANDROID"};


                new ProcessJSON().execute(params);
            }
        });
        findViewById(R.id.btBackFromAddProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddProduct.this, MainActivity.class));
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }


    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params){

            String text = "";
            BufferedReader reader=null;


            try
            {

                // Defined URL  where to send data
                URL url = new URL("http://192.168.134.204/orders/index.php/orders/add_product");

                // Send POST data request


                // Create data variable for sent values to server

                String data = URLEncoder.encode("product_code", "UTF-8")
                        + "=" + URLEncoder.encode(params[0], "UTF-8");

                data += "&" + URLEncoder.encode("product_name", "UTF-8") + "="
                        + URLEncoder.encode(params[1], "UTF-8");

                data += "&" + URLEncoder.encode("product_description", "UTF-8")
                        + "=" + URLEncoder.encode(params[2], "UTF-8");



                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "\n");
                }


                text = sb.toString();
            }
            catch(Exception ex)
            {

            }
            finally
            {
                try
                {

                    reader.close();
                }

                catch(Exception ex) {}
            }

            return "";
        }

        protected void onPostExecute(String stream){

            //tv.setText(stream);

            /*
                Important in JSON DATA
                -------------------------
                * Square bracket ([) represents a JSON array
                * Curly bracket ({) represents a JSON object
                * JSON object contains key/value pairs
                * Each key is a String and value may be different data types
             */

            //..........Process JSON DATA................
            if(stream !=null){
                try{
                    // Get the full HTTP Data as JSONObject

                    //#######JSONObject reader= new JSONObject(stream);


                    // process other data as this way..............

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end

    private String getQuery(List<Pair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Pair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.first + "", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.second + "" , "UTF-8"));
        }

        return result.toString();
    }

}
