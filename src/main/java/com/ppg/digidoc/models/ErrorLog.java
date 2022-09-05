package com.ppg.digidoc.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document("ErrorLog")
// this will save any error log into database 
public class ErrorLog {
	  
	    private String id;

	    private String application;
	    private String key;
	    private String errorDetails;
	    private Date CreatedON;
}
