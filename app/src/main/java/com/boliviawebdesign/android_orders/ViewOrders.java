package com.boliviawebdesign.android_orders;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ViewOrders extends AppCompatActivity {

    CheckBox checkBox;
    TextView tv;
    ImageButton addBtn;
    ImageButton minusBtn;
    TextView qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);





        init();
    }


    public void init(){
        TableLayout ll = (TableLayout) findViewById(R.id.displayLinear);


        for (int i = 0; i <20; i++) {

            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            checkBox = new CheckBox(this);
            tv = new TextView(this);
            addBtn = new ImageButton(this);
            addBtn.setImageResource(R.mipmap.add);
            minusBtn = new ImageButton(this);
            minusBtn.setImageResource(R.mipmap.minus);
            qty = new TextView(this);
            checkBox.setText("hello");
            qty.setText((i+1)+"");
            row.addView(checkBox);
            row.addView(minusBtn);
            row.addView(qty);
            row.addView(addBtn);
            ll.addView(row,i);
        }
    }












    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params){

            String stringStream = "";
            BufferedReader reader=null;


            try
            {

                // Defined URL  where to send data
                URL url = new URL("http://192.168.134.204/orders/index.php/orders/view_orders_service");

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
                    stringStream += stringStream;
                }


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

            return stringStream;
        }

        protected void onPostExecute(String stringStream){

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
            if(stringStream !=null){
                try{
                    // Get the full HTTP Data as JSONObject

                    JSONObject reader= new JSONObject(stringStream);


                    // process other data as this way..............

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end
}
