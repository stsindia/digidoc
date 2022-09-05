package com.ppg.digidoc.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class ApplicationName {
	 	@Indexed(unique = true)
		private String ApplicationCode;
	 	 @Indexed(unique = true)
		private String APIKey;
		private String Auth_Sign;
		private String Active;
	    

public ApplicationName( String ApplicationCode,String APIKey,String Auth_Sign,String  Active) {
	//this.id=id;
	this.ApplicationCode=ApplicationCode;
	this.APIKey=APIKey;
	this.Auth_Sign=Auth_Sign;
	this.Active=Active;
}
	    
public void setApplicationName(String ApplicationCode) {
	this.ApplicationCode=ApplicationCode;
}
 

public String getApplicationCode() {
	return ApplicationCode;
}
public void setAPIKey(String APIKey) {
	this.APIKey=APIKey;
}
 

public String getAPIKey() {
	return APIKey;
}
public void setAuth_Sign(String Auth_Sign) {
	this.Auth_Sign=Auth_Sign;
}
 

public String getAuth_Sign() {
	return Auth_Sign;
}
public void setActive(String Active) {
	this.Active=Active;
}
 

public String getActive() {
	return Active;
}
}
