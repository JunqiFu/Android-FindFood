package com.example.findfood.home.adpater;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.findfood.R;
import com.example.findfood.bean.Cates;

import java.util.List;

/**
 * Created by French on 2018/7/3.
 */

public class Listview_popular_adpter extends BaseAdapter implements AbsListView.OnScrollListener ,View.OnClickListener{

        private Context context;
        private List<Cates> catesList;
        private InnerItemOnclickListener mListener;

    public Listview_popular_adpter(Context context, List <Cates> list ) {

        this.context = context;
        this.catesList = list;
    }

    @Override
    public void onClick(View view) {
        mListener.itemClick(view);
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
        return catesList.size();
    }

    @Override
    public Object getItem(int i){
        return catesList.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.mostpopular_item_cates, null);
            //实例化对象
            holder.c_names = (TextView) view.findViewById( R.id.txt_caters_items_names);// 名字
            holder.c_content=(TextView)view.findViewById( R.id.txt_caters_contents); // 价格

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (!TextUtils.isEmpty(catesList.get(i).getC_name())){
            holder.c_names.setText(catesList.get(i).getC_name().toString());
        }
        if (!TextUtils.isEmpty(catesList.get(i).getC_contents())){
            holder.c_content.setText(catesList.get(i).getC_contents().toString());
        }
        return view;
    }


    // item 监听事件
    interface InnerItemOnclickListener {
        void itemClick(View view);
    }
    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }
    public  class ViewHolder{
        TextView c_names;// 名字
        TextView c_content;//内容

    }
}
