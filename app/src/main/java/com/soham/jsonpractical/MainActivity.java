package com.soham.jsonpractical;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView jsonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        jsonText=(TextView)findViewById(R.id.jsonView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Parse JSON Data : Serialization
        //Object to store person record
        Person person=new Person();

        //Object to store address record
        Person.Address address=person.new Address();
        address.setAddress("Dharmajivan");
        address.setCity("Ahmedabad");
        address.setState("Gujrat");

        //object1 to store phone number and type
        Person.PhoneNumber ph=person.new PhoneNumber();
        ph.setNumber("123");
        ph.setType("Landline");

        //object2 to store phone number and type
        Person.PhoneNumber ph1=person.new PhoneNumber();
        ph1.setNumber("456");
        ph1.setType("Mobile Number");

        //object of list to store phone record in ArrayList
        List<Person.PhoneNumber> phone = new ArrayList<>();

        phone.add(ph);
        phone.add(ph1);

        person.setName("Soham");
        person.setSurname("Navadiya");
        person.setAddress(address);
        person.setPhoneList(phone);

        jsonText.setText(JsonUtils.toJson(person));

        //Parse JSON Data : Deserialization
        try{
            JSONObject jObj=new JSONObject(JsonUtils.toJson(person).toString());
            String surname=jObj.getString("surname");
            Log.e("SURNAME",surname);


            JSONObject subObj=jObj.getJSONObject("address");
            String city=subObj.getString("city");
            Log.e("CITY",city);

            JSONArray jArr=jObj.getJSONArray("phoneNumber");
            for(int i=0;i<jArr.length();i++){
                JSONObject obj=jArr.getJSONObject(i);
                String number=obj.getString("num");
                Log.e("NUMBER",number);
                String type=obj.getString("type");
                Log.e("TYPE",type);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class PhoneNumber {
    }
}
