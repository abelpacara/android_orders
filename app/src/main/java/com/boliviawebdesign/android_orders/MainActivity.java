package com.boliviawebdesign.android_orders;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnSave = (Button) this.findViewById(R.id.btnSave);



        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click



                urlString = "http://192.168.134.204/orders/index.php/orders/show_orders2";
                new ProcessJSON().execute(urlString);

            }
        });


    }








    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];









            try{
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Check the connection status
                if(urlConnection.getResponseCode() == 200)
                {
                    // if response code = 200 ok
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    // Read the BufferedInputStream
                    BufferedReader r = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        sb.append(line);
                    }
                    stream = sb.toString();
                    // End reading...............

                    // Disconnect the HttpURLConnection
                    urlConnection.disconnect();
                }
                else
                {
                    // Do something
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }finally {

            }
            // Return the data from specified url





            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream){
            TextView tv = (TextView) findViewById(R.id.resultView);
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


                    tv.setText(stream);


                    // process other data as this way..............

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end



}
