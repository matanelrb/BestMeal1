package com.example.user.bestmeal1;

import java.util.LinkedList;

/**
 * Created by user on 17/05/2017.
 */
public class Restaurant {

    private String r_name;
    private String r_address;
    private String r_phone_number;



    public Restaurant(String r_name, String r_address, String r_phone_number) {

        this.r_name = r_name;
        this.r_address = r_address;
        this.r_phone_number = r_phone_number;

    }

    public Restaurant(){}

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_address() {
        return r_address;
    }

    public void setR_address(String r_address) {
        this.r_address = r_address;
    }

    public String getR_phone_number() {
        return r_phone_number;
    }

    public void setR_phone_number(String r_phone_number) {
        this.r_phone_number = r_phone_number;
    }


}
