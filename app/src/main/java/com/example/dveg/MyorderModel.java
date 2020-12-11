package com.example.dveg;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.List;

public class MyorderModel
{

    @ServerTimestamp
    Date date;
    String email;
    String houseno;
    String landmark;
    String phonenumber;
    String pincode;
    String roadname;
    String state;
    String usermobile;
    String username;
    List<String> product;
    List<String> product_quant;
    String deleviredstatus;
    String total;


    public MyorderModel(){}

    public MyorderModel(Date date, String email, String houseno, String landmark, String phonenumber, String pincode, String roadname, String state, String usermobile, String username, List<String> product, List<String> product_quant, String deleviredstatus, String total) {
        this.date = date;
        this.email = email;
        this.houseno = houseno;
        this.landmark = landmark;
        this.phonenumber = phonenumber;
        this.pincode = pincode;
        this.roadname = roadname;
        this.state = state;
        this.usermobile = usermobile;
        this.username = username;
        this.product = product;
        this.product_quant = product_quant;
        this.deleviredstatus = deleviredstatus;
        this.total = total;
    }

    public String getDeleviredstatus() {
        return deleviredstatus;
    }

    public void setDeleviredstatus(String deleviredstatus) {
        this.deleviredstatus = deleviredstatus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getRoadname() {
        return roadname;
    }

    public void setRoadname(String roadname) {
        this.roadname = roadname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsermobile() {
        return usermobile;
    }

    public void setUsermobile(String usermobile) {
        this.usermobile = usermobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getProduct() {
        return product;
    }

    public void setProduct(List<String> product) {
        this.product = product;
    }

    public List<String> getProduct_quant() {
        return product_quant;
    }

    public void setProduct_quant(List<String> product_quant) {
        this.product_quant = product_quant;
    }
}
