package webService;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

import java.util.ArrayList;

import dataObjects.course;
import dataObjects.groupedCourse;


public class Utitlity {
    /**
     * Null check Method
     * 
     * @param txt
     * @return
     */
    public static boolean isNotNull(String txt) {
     //    System.out.println("Inside isNotNull"+txt);
        return txt != null && txt.trim().length() >= 0 ? true : false;
    }
 
    /**
     * Method to construct JSON
     * 
     * @param tag
     * @param status
     * @return
     */
    public static String constructJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
           

        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        //return obj.toString();
        return obj.toString();
    }
 
    /**
     * Method to construct JSON with Error Msg
     * 
     * @param tag
     * @param status
     * @param err_msg
     * @return
     */
    public static String constructJSON(String tag, boolean status,String err_msg) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", err_msg);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
    
    public static String constructJSONP(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
           

        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
       return obj.toString();
       // return "callback(" + obj.toString() +")";
    }
    
    public static String constructJSONP(String tag, boolean status,String err_msg) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", err_msg);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
        //return "callback(" + obj.toString() +")";
    }
    
    
    public static String constructCourseJSON(ArrayList<course> courseData)
    {
    	Gson gson = new Gson();
    	String json = gson.toJson(courseData); 
		return json;
    	
    }
    
    public static String constructGroupedCourseJSON(ArrayList<groupedCourse> groupedCourseData)
    {
    	Gson gson = new Gson();
    	String json = gson.toJson(groupedCourseData); 
		return json;
    	
    }
 
}