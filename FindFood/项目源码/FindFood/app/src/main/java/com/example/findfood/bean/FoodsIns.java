package com.example.findfood.bean;

/**
 * Created by French on 2018/7/5.
 */

public class FoodsIns {
    private String fs_name; //菜名
    private String fs_price; //价格

    public FoodsIns( String fs_name,String fs_price){
        this.fs_name=fs_name;
        this.fs_price=fs_price;

    }

    public String getFs_name() {
        return fs_name;
    }

    public void setFs_name(String fs_name) {
        this.fs_name = fs_name;
    }

    public String getFs_price() {
        return fs_price;
    }

    public void setFs_price(String fs_price) {
        this.fs_price = fs_price;
    }
}
