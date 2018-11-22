package com.example.findfood.bean;

/**
 * Created by French on 2018/7/2.
 */

public class Shops {

    private String s_imageId; //图片的名称
    private String s_name; //菜名
    private String s_price; //价格
    private String s_type; //菜品类别
    private String b_name; //商家名称
    private String b_palce; //商家地址
    public Shops( String s_imageId,String s_name, String b_palce,String s_price, String s_type ){
        this.s_name=s_name;
        this.s_type=s_type;
        this.b_palce=b_palce;
        this.s_price=s_price;
        this.s_imageId=s_imageId;
    }
    @Override
    public String toString() {
        return  b_palce + "," + s_name + ", " + s_price+","+s_type;
    }

    public String getS_imageId() {
        return s_imageId;
    }

    public void setS_imageId(String s_imageId) {
        this.s_imageId = s_imageId;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_price() {
        return s_price;
    }

    public void setS_price(String s_price) {
        this.s_price = s_price;
    }

    public String getS_type() {
        return s_type;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_palce() {
        return b_palce;
    }

    public void setB_palce(String b_palce) {
        this.b_palce = b_palce;
    }
}
