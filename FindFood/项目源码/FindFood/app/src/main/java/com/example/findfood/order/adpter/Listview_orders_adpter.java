package com.example.findfood.order.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.findfood.R;
import com.example.findfood.bean.Orders;

import java.util.List;

/**
 * Created by French on 2018/7/4.
 */

public class Listview_orders_adpter extends BaseAdapter implements AbsListView.OnScrollListener ,View.OnClickListener {

    private Context context;
    private List<Orders> ordersList;
    private InnerItemOnclickListener smListener;

    public Listview_orders_adpter(Context context, List<Orders> list) {

        this.context = context;
        this.ordersList = list;
    }

    @Override
    public void onClick(View view) {

        smListener.itemClick(view);
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
        return ordersList.size();
    }

    @Override
    public Object getItem(int i){
        return ordersList.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    // ************************
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.orderslistview_item_orders, null);
            //实例化对象
            holder.o_id = (TextView) view.findViewById( R.id.txt_order_idinfo);// 订单编号
            holder.b_name = (TextView) view.findViewById(R.id.txt_order_dnames);// 店名
            holder.f_name=(TextView)view.findViewById( R.id.txt_order_fnames); // 食物名
            holder.f_prices=(TextView)view.findViewById( R.id.txt_orderfprices); // 价格
            holder.o_state=(TextView)view.findViewById( R.id.txt_orders_state); // 状态
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (!TextUtils.isEmpty(ordersList.get(i).getO_id())){
            holder.o_id.setText(ordersList.get(i).getO_id().toString());
        }else {
            holder.o_id.setText("sssss");
        }
        if (!TextUtils.isEmpty(ordersList.get(i).getB_name())){
            holder.b_name.setText(ordersList.get(i).getB_name().toString());
        }else {
            holder.b_name.setText("sssss");
        }
        if (!TextUtils.isEmpty(ordersList.get(i).getF_name())){
            holder.f_name.setText(ordersList.get(i).getF_name().toString());
        }else {
            holder.f_name.setText("sssss");
        }
        if (!TextUtils.isEmpty(ordersList.get(i).getF_prices())){
            holder.f_prices.setText(ordersList.get(i).getF_prices().toString());
        }else {
            holder.f_prices.setText("sssss");
        }
        if (!TextUtils.isEmpty(ordersList.get(i).getO_state())){
            holder.o_state.setText(ordersList.get(i).getO_state().toString());
        }else {
            holder.o_state.setText("sssss");
        }
        return view;
    }


    // item 监听事件
    interface InnerItemOnclickListener {
        void itemClick(View view);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.smListener=listener;
    }
    public  class ViewHolder{
        TextView o_id;// 订单编号
        TextView b_name;//店名
        TextView f_name;//食物名
        TextView f_prices;//价格
        TextView o_state;//状态
    }
}
