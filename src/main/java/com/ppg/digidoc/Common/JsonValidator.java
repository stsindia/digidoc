package com.ppg.digidoc.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class JsonValidator {
	 public boolean isValidObject(String json) {
	        try {
	            new JSONObject(json);
	        } catch (JSONException e) {
	            return false;
	        }
	        return true;
	    }

	    public boolean isValidJson(String json) {
	        try {
	            new JSONObject(json);
	        } catch (JSONException e) {
	            try {
	                new JSONArray(json);
	            } catch (JSONException ne) {
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    public JSONObject rtnJSONObject(String json) {
	    	JSONObject jo;
	    	
	        try {
	            jo=new JSONObject(json);
	        } catch (JSONException e) {
	            
	                return null;
	            
	        }
	        return jo;
	    }

	    public JSONArray rtnJSONArray(String json) {
	    	JSONArray jo;
	    	
	        try {
	            jo=new JSONArray(json);
	        } catch (JSONException e) {
	            
	                return null;
	            
	        }
	        return jo;
	    }
}
