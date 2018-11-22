package com.example.findfood.bean;

/**
 * Created by French on 2018/7/3.
 *
 *  最受欢迎界面的展示
 */



public class Cates {

    private String c_image; //图片的名称
    private String c_name; //美食
    private String c_contents; //介绍

    public Cates(String c_name, String c_contents ){
        this.c_name=c_name;
        this.c_contents=c_contents;

    }

    @Override
    public String toString() {
        return  c_name + ", " + c_contents;
    }

    public String getC_image() {
        return c_image;
    }

    public void setC_image(String c_image) {
        this.c_image = c_image;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_contents() {
        return c_contents;
    }

    public void setC_contents(String c_contents) {
        this.c_contents = c_contents;
    }
}
