package com.example.findfood.mine.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findfood.R;
import com.example.findfood.bean.Foods;

import java.util.List;

public class listview_celecter_adapter extends BaseAdapter {
    private List<Foods> celectlist;
    private Context context;
    @Override
    public int getCount() {
        return celectlist.size();
    }

    @Override
    public Foods getItem(int position) {
        return celectlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = null;
        if (convertView == null) {
            holder = new ViewHolder();
//            convertView = View.inflate(getApplicationContext(), R.layout.mine_collection_listview, null);

             holder.iv_icon=(ImageView)view.findViewById( R.id.iv_icon);
             holder.tv_fenshu=(TextView)view.findViewById( R.id.tv_fenshu);
             holder.tv_price=(TextView) view.findViewById( R.id.tv_price);
             holder.tv_dianpu=(TextView)view.findViewById( R.id.tv_dianpu);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Foods item= (Foods) getItem(position);
         holder.tv_dianpu.setText(item.getB_name());
//         holder.tv_fenshu.setText(item.fenshu);
         holder.tv_price.setText(item.getF_price());
         holder.iv_icon.setImageDrawable( Drawable.createFromPath( item.getF_imageId() ) );



        return convertView;

    }




    private static class ViewHolder {
            TextView tv_fenshu;
            TextView tv_price;
            ImageView iv_icon;
            TextView tv_dianpu;

        }
    }

