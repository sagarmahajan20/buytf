package com.example.dveg;

public class Modeladdproduct
{

    private String p_id;
    private String product_name;
    private String p_mrp;
    private String p_rs;
    private String p_urlimage;

    public Modeladdproduct()
    {

    }

    public Modeladdproduct(String p_id, String product_name, String p_mrp, String p_rs, String p_urlimage) {
        this.p_id = p_id;
        this.product_name = product_name;
        this.p_mrp = p_mrp;
        this.p_rs = p_rs;
        this.p_urlimage = p_urlimage;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getP_mrp() {
        return p_mrp;
    }

    public void setP_mrp(String p_mrp) {
        this.p_mrp = p_mrp;
    }

    public String getP_rs() {
        return p_rs;
    }

    public void setP_rs(String p_rs) {
        this.p_rs = p_rs;
    }

    public String getP_urlimage() {
        return p_urlimage;
    }

    public void setP_urlimage(String p_urlimage) {
        this.p_urlimage = p_urlimage;
    }
}
