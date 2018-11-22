package com.example.findfood.home.adpater;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.findfood.R;
import com.example.findfood.bean.FoodsIns;

import java.util.List;

/**
 * Created by French on 2018/7/5.
 */

  //下单详情页
public class Listview_foodsitem_adpter extends BaseAdapter implements AbsListView.OnScrollListener , View.OnClickListener {

    private Context context;
    private List<FoodsIns> foodinsList;
    private InnerItemOnclickListener mListener;

    private Button xiadan;

    public Listview_foodsitem_adpter(Context context, List<FoodsIns> list) {
        this.context = context;
        this.foodinsList = list;
    }

    // listView 的 返回值  数量  item  id  等
    @Override
    public int getCount(){
        return foodinsList.size();
    }

    @Override
    public Object getItem(int i){
        return foodinsList.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder holder = null;
//        xiadan=view.findViewById(R.id.btn_foods_orders_mew);
//        xiadan.setOnClickListener(this);

        if (view == null) {
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.foodsitem_listviews_item, null);
            //实例化对象
            holder.foodissname = (TextView) view.findViewById( R.id.txst_foodss);// 食物名称
            holder.foodisprice = (TextView) view.findViewById(R.id.txzt_pricess);// 食物价格
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (!TextUtils.isEmpty(foodinsList.get(i).getFs_name())){
            holder.foodissname.setText(foodinsList.get(i).getFs_name().toString());
        }
        if (!TextUtils.isEmpty(foodinsList.get(i).getFs_price())){
            holder.foodisprice.setText(foodinsList.get(i).getFs_price().toString());
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        mListener.itemClick(view);
//        if(view.getId()==R.id.btn_foods_orders_mew){
//            System.out.println("hciusdcbousancoiasssssssssssss***");
//        }
        Log.e("1","******************** // 点击事件  //****************");

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }


    public  class ViewHolder{
        TextView foodissname;// 食名
        TextView foodisprice;//价格
      }


    // item 监听事件
    interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }
}
