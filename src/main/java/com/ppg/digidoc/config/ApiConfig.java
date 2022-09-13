package com.ppg.digidoc.config;

import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Log
@Component
@ConfigurationProperties(prefix = "qr.api")
public class ApiConfig {

    private String base;
    private String version;

    private String org;
    private String token;
    
    private String logo_size;
    private String logo_excavated;
    private String logo_url;
    private String error_correction;
    
    

    public String getTextQrCodeUrl() {
        log.info("Value of base = " + this.base);
        return (this.base + version + "/qrcode/text");
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void setlogo_size(String logo_size) {
        this.logo_size = logo_size;
    }


    public void setlogo_excavated(String logo_excavated) {
        this.logo_excavated = logo_excavated;
    }


    public void setlogo_url(String logo_url) {
        this.logo_url = logo_url;
    }


    public void seterror_correction(String error_correction) {
        this.error_correction = error_correction;
    }

    
    public String getBase() {
        return base;
    }

    public String getVersion() {
        return version;
    }

    public String getOrg() {
        return org;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "ApiConfig{" +
                "base='" + base + '\'' +
                ", version='" + version + '\'' +
                ", org='" + org + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getlogo_url() {
        return logo_url;
    }


    public String getlogo_size() {
        return logo_size;
    }


    public String getlogo_excavated() {
        return logo_excavated;
    }


    public String geterror_correction() {
        return error_correction;
    }

}
