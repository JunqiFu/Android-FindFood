package com.example.findfood.bean;

/**
 * Created by French on 2018/7/3.
 *
 *  菜品属性
 */

public class Foods {

    private String f_imageId; //图片的名称
    private String f_name; //菜名
    private String f_price; //价格
    private String f_type; //菜品类别
    private String b_name; //商家名称
    private String b_palce; //商家地址

    public Foods( String f_name,String f_price, String b_palce ){
        this.f_name=f_name;
        this.f_price=f_price;
        this.b_palce=b_palce;

    }

    @Override
    public String toString() {
        return  b_palce + "," + f_name + ", " + f_price;
    }

    public String getF_imageId() {
        return f_imageId;
    }

    public void setF_imageId(String f_imageId) {
        this.f_imageId = f_imageId;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_price() {
        return f_price;
    }

    public void setF_price(String f_price) {
        this.f_price = f_price;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
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

