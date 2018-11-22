package com.example.findfood.home.adpater;

import android.app.Application;
import android.content.Context;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findfood.R;
import com.example.findfood.bean.Shops;

import java.util.List;

/**
 * Created by French on 2018/7/2.
 */

public class Listview_shops_adpter extends BaseAdapter {
    private Context context;
    private List<Shops> shopsList;

    public Listview_shops_adpter(Context context, List<Shops> list) {
        this.context = context;
        this.shopsList = list;
    }


    @Override
    public int getCount() {
        return shopsList.size();
    }

    @Override
    public Object getItem(int i) {
        return shopsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //引入布局
            convertView = View.inflate(context, R.layout.newestlistview_item_shops, null);
            //实例化对象
            holder.shopstype = (TextView) convertView.findViewById( R.id.txt_s_type);//
            holder.shopsname = (TextView) convertView.findViewById(R.id.txt_s_name);//
            holder.shopsprice=(TextView)convertView.findViewById( R.id.txt_s_price); //
            holder.shopsplace=(TextView) convertView.findViewById( R.id.txt_s_place); //
            holder.shopImg=(ImageView)convertView.findViewById(R.id.images_shop);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(shopsList.get(i).getS_imageId())) {

//            ApplicationInfo appInfo = getApplicationInfo();
//            int resID = getResources().getIdentifier(name, "drawable", appInfo.packageName);
//            int resId = context.getResources().getIdentifier(shopsList.get(i).getS_imageId(), "drawable" , context.getPackageName());
//            System.out.println("id*******"+resId);
//            Drawable drawable = context.getDrawable(resId);
            int resId = context.getResources().getIdentifier(shopsList.get(i).getS_imageId(), "drawable" , context.getPackageName());
            System.out.println("id*******"+resId);
                       Drawable drawable = context.getDrawable(resId);
            holder.shopImg.setImageDrawable(drawable);
        }
        if (!TextUtils.isEmpty(shopsList.get(i).getS_type())) {
            holder.shopstype.setText(shopsList.get(i).getS_type().toString());
        }
        if (!TextUtils.isEmpty(shopsList.get(i).getS_name())) {
            holder.shopsname.setText(shopsList.get(i).getS_name().toString());
        }
        if (!TextUtils.isEmpty(shopsList.get(i).getS_price())) {
            holder.shopsprice.setText(shopsList.get(i).getS_price().toString());
        }
        if (!TextUtils.isEmpty(shopsList.get(i).getB_palce())) {
            holder.shopsplace.setText(shopsList.get(i).getB_palce().toString());
        }

        return convertView;
    }

    public  class ViewHolder{
        TextView shopsname;//
        TextView shopsprice;//
        TextView shopsplace;//
        TextView shopstype;
        TextView position;//序号
        ImageView shopImg;
    }
}
