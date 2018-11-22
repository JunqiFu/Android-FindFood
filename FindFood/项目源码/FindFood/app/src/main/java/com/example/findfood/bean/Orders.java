package com.example.findfood.bean;

/**
 * Created by French on 2018/7/4.
 *
 *  订单实体类
 */

public class Orders {

    private String o_id; //订单编号
    private String c_image; //图片的名称
    private String b_name; //店名
    private String f_name;//食物名
    private String f_prices; //价格
    private String o_state;//状态


    public Orders( String o_id,String b_name,String f_name,String f_prices,String o_state ){
        this.o_id=o_id;
        this.f_name=f_name;
        this.b_name=b_name;
        this.f_prices=f_prices;
        this.o_state=o_state;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getC_image() {
        return c_image;
    }

    public void setC_image(String c_image) {
        this.c_image = c_image;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_prices() {
        return f_prices;
    }

    public void setF_prices(String f_prices) {
        this.f_prices = f_prices;
    }

    public String getO_state() {
        return o_state;
    }

    public void setO_state(String o_state) {
        this.o_state = o_state;
    }
}
