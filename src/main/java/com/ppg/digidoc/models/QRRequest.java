package com.ppg.digidoc.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Data
@Document("QRRequest")
public class QRRequest {
    @Id
    @Indexed(unique = true)
    private String id;
    @Indexed(unique = true)
    private String orderId;
    private String application;
    private String key;
    private String centerLogo;
    private String urlContentView;
    private String contentType;
    private String content;
   
    private String eyeColor;
    private String backgroundColor;
    private String dataGradientColor;
    private String auth_sign;
    
    
    private List<CustomerServices> CustomerServices;
   // private Instant CreatedON;

    private Date CreatedON;

public QRRequest( String orderId, String application, String key,
                        String centerLogo, String urlContentView, String contentType,
                        String content, String eyeColor, String  backgroundColor, String dataGradientColor, String auth_sign  ) {
	//this.id=id;
	this.orderId=orderId;
	this.application=application;
	this.key=key;
	this.centerLogo=centerLogo;
	this.urlContentView=urlContentView;
	this.contentType=contentType;
	this.content=content;
	Calendar calendar = Calendar.getInstance();
	this.CreatedON=calendar.getTime();
	
	this.eyeColor=eyeColor;
	this.backgroundColor=backgroundColor;
	this.dataGradientColor=dataGradientColor;
	this.auth_sign=auth_sign;
}

/*
 * public void setId(String id) { this.id=id; }
 */
public void setorderID(String orderId) {
	this.orderId=orderId;
}


public void setApplication(String application) {
	this.application=application;
}

public void setKey(String key) {
	this.key=key;
}

public void setCenterLogo(String centerLogo) {
	this.centerLogo=centerLogo;
}

public void setUrlContentView(String urlContentView) {
	this.urlContentView=urlContentView;
}
public void seteyeColor(String eyeColor) {
	this.eyeColor=eyeColor;
}

public void setbackgroundColor(String backgroundColor) {
	this.backgroundColor=backgroundColor;
}

public void setdataGradientColor(String dataGradientColor) {
	this.dataGradientColor=dataGradientColor;
}
public void setcontentType(String contentType) {
	this.contentType=contentType;
}

public void setContent(String content) {
	this.content=content;
}

public void setCreatedON(Date CreatedON) {
	this.CreatedON=CreatedON;
}
public void setauth_sign(String auth_sign) {
	this.auth_sign=auth_sign;
}

public String getauth_sign() {
	return auth_sign;
}


public String getId() {
	return id;
}
public String getorderId() {
	return orderId;
}

public String getAapplication() {
	return application;
}
public String getkey() {
	return key;
}
public String getcenterLogo() {
	return centerLogo;
}
public String geturlContentView() {
	return urlContentView;
}
public String getcontent() {
	return content;
}
public Date getCreatedON() {
	return CreatedON;
}
 


}
