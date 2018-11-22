package com.example.findfood.home.adpater;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.findfood.R;
import com.example.findfood.app.MainActivity;
import com.example.findfood.bean.Foods;
import com.example.findfood.home.fragment.NewestFragment;

import java.util.List;

/**
 * Created by French on 2018/7/3.
 *
 *   最热页  Listview Adpater
 *
 */

public class Listview_foods_adpter extends BaseAdapter implements AbsListView.OnScrollListener ,View.OnClickListener {

    //private TextView F_name,F_price,S_name;
    private Context context;
    private List<Foods> foodList;
    private InnerItemOnclickListener mListener;

    private NewestFragment newestFragment;
    //private String ShopData=newestFragment.send();
    public Listview_foods_adpter(Context context, List<Foods> list) {

        this.context = context;
        this.foodList = list;
    }
    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
        Log.e("1","******************** // 点击事件  //****************");
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
    // listView 的 返回值  数量  item  id  等
    @Override
    public int getCount(){
        return foodList.size();
    }

    @Override
    public Object getItem(int i){
        return foodList.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.hotteslistview_item_foods, null);
            //实例化对象
            holder.foodsname = (TextView) view.findViewById( R.id.txt_foodss);// 食物名称
            holder.foodprice = (TextView) view.findViewById(R.id.txt_pricess);// 食物价格
            holder.bpalce=(TextView)view.findViewById( R.id.txt_placess); // 食物地址

            view.setTag(holder);
        }else {

            holder = (ViewHolder) view.getTag();
        }
//        if(ShopData!=null){
//
//        }
        if (!TextUtils.isEmpty(foodList.get(i).getF_name())){
            holder.foodsname.setText(foodList.get(i).getF_name().toString());
        }
        if (!TextUtils.isEmpty(foodList.get(i).getF_price())){
            holder.foodprice.setText(foodList.get(i).getF_price().toString());
        }
        if (!TextUtils.isEmpty(foodList.get(i).getB_palce())){
            holder.bpalce.setText(foodList.get(i).getB_palce().toString());
        }

        return view;
    }

    // item 监听事件
    interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(Listview_foods_adpter.InnerItemOnclickListener listener){
        this.mListener=listener;
    }
    public  class ViewHolder{
        TextView foodsname;// 食名
        TextView foodprice;//价格
        TextView bpalce;//点名

    }

}
