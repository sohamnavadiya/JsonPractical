package com.soham.jsonpractical;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by soham on 31-12-2015.
 */
public class JsonUtils {
    public static String toJson(Person person){
        try {

        //Convert Java Object to JSON
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("name",person.getName());//set the first name/pair
            jsonObj.put("surname",person.getSurname());

            //another object to store the address
            JSONObject jsonAdd=new JSONObject();
            jsonAdd.put("address",person.getAddress().getAddress());
            jsonAdd.put("city",person.getAddress().getCity());
            jsonAdd.put("state",person.getAddress().getState());

            jsonObj.put("address",jsonAdd);//method to store in main JSON object

            //object of jsonArray to store phone number and type (to hold java list)
            JSONArray jsonArr=new JSONArray();

            for(Person.PhoneNumber pn:person.getPhoneList()){

                //Create individual object to store PhoneNumber and Type
                JSONObject pnObj=new JSONObject();
                pnObj.put("num",pn.getNumber());
                pnObj.put("type",pn.getType());

                jsonArr.put(pnObj);
            }

            jsonObj.put("phoneNumber",jsonArr);//method to store in main JSON object

            return jsonObj.toString();


        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
