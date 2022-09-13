package com.ppg.digidoc.models;

public class CustomerServices {
    private String orderId;
    private String code;
    private String isConsumed;
    private String Remark;


    public CustomerServices(String orderId, String code, String isConsumed,
                            String Remark) {
        //this.id=id;
        this.orderId = orderId;
        this.code = code;
        this.isConsumed = isConsumed;
        this.Remark = Remark;
    }

    public void setorderID(String orderId) {
        this.orderId = orderId;
    }

    public void setcode(String code) {
        this.code = code;
    }

    public void setisConsumed(String isConsumed) {
        this.isConsumed = isConsumed;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }


    public String getorderId() {
        return orderId;
    }

    public String getcode() {
        return code;
    }

    public String getisConsumed() {
        return isConsumed;
    }

    public String getRemark() {
        return Remark;
    }
}
