package com.hextogen.daurm;

// for room object

public class room {

    String r_name, r_state;


    public room() {

    }
    public room(String r_name, String r_state) {
        this.r_name = r_name;
        this.r_state = r_state;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public void setR_state(String r_state) {
        this.r_state = r_state;
    }

    public String getR_name() {
        return r_name;
    }

    public String getR_state() {
        return r_state;
    }
}
