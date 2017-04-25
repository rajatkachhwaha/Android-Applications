package com.example.rjt.faster;

/**
 * Created by Rjt on 4/24/2017.
 */

public class ClickAccount {


    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    private  String nameid;

    public int getClickCounts() {
        return clickCounts;
    }

    private int clickCounts;

    public void updateCount(){
      clickCounts= clickCounts+1;

    };


    public ClickAccount(){

    }


}
