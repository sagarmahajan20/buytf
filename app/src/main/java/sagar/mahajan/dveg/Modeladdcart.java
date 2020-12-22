package sagar.mahajan.dveg;

public class Modeladdcart {


    String p_id;
    String p_name;
    String p_rs;
    String p_quant;
    String p_email;
    String p_urlimage;


    public Modeladdcart(){}

    public Modeladdcart(String p_id, String p_name, String p_rs, String p_quant, String p_email, String p_urlimage) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_rs = p_rs;
        this.p_quant = p_quant;
        this.p_email = p_email;
        this.p_urlimage = p_urlimage;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_rs() {
        return p_rs;
    }

    public void setP_rs(String p_rs) {
        this.p_rs = p_rs;
    }

    public String getP_quant() {
        return p_quant;
    }

    public void setP_quant(String p_quant) {
        this.p_quant = p_quant;
    }

    public String getP_email() {
        return p_email;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

    public String getP_urlimage() {
        return p_urlimage;
    }

    public void setP_urlimage(String p_urlimage) {
        this.p_urlimage = p_urlimage;
    }
}
