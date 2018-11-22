package com.example.findfood.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.MainActivity;
import com.example.findfood.bean.Cates;
import com.example.findfood.home.Cates_item_content;
import com.example.findfood.home.adpater.Listview_popular_adpter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MostpopularFragment extends Fragment {

    // 图片资源
    private int[] mImages = {R.mipmap.banner, R.mipmap.bannerss, R.mipmap.banner, R.mipmap.bannerss};
    private List<ImageView> mList;
    Handler mHandler = new Handler();

    private List<Cates> catesList;
    private Listview_popular_adpter adpters;


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intivew();

        catesList = new ArrayList<Cates>();
        ListView listView = (ListView)getActivity().findViewById( R.id.mostpopularlistview);
        //初始化商品数据
        initCates();
        adpters = new Listview_popular_adpter(getActivity(), catesList);
        listView.setAdapter(adpters);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入详情页面", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),Cates_item_content.class);
                startActivity(intent);
            }
        } );
    }
     // 准备商品信息
    private void initCates() {

        if(((MainActivity)getActivity()).getAllFoodsData()!=null) {
            try {
                JSONArray array=new JSONArray(((MainActivity)getActivity()).getAllFoodsData());
                for (int i = 1; i < array.length(); i++) {
                    JSONObject object=(JSONObject)array.get(i);
                    //Cates cates = new Cates("火锅", "好事者将“成都火锅”和“重庆火锅”分个高下，实际上， 这两个四川火锅流派，一个棉里藏针，一个剑把弩张.....");
                    Cates cates = new Cates(object.get("F_name").toString(), object.get("F_introduce").toString());

                    catesList.add(cates);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // 数据库添加信息

        }
    }


    private void intivew() {

        final ViewPager viewPager = (ViewPager)getActivity().findViewById( R.id.viewpager1);
        final LinearLayout pointGroup = (LinearLayout)getActivity().findViewById( R.id.pointgroup1);
        // 准备显示的图片集合
        mList = new ArrayList<>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView( getActivity() );
            // 将图片设置到ImageView控件上
            imageView.setImageResource(mImages[i]);

            // 将ImageView控件添加到集合
            mList.add(imageView);

            // 制作底部小圆点
            ImageView pointImage = new ImageView(getActivity());
            pointImage.setImageResource( R.drawable.shape_point_selector);

            // 设置小圆点的布局参数
            int PointSize = getResources().getDimensionPixelSize( R.dimen.point_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PointSize, PointSize);

            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelSize( R.dimen.point_margin);
                pointImage.setSelected(false);
            } else {
                pointImage.setSelected(true);
            }
            pointImage.setLayoutParams(params);
            // 添加到容器里
            pointGroup.addView(pointImage);
        }
        viewPager.setAdapter(new MyAdapter());
        // 对ViewPager设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            int lastPosition;
            @Override
            public void onPageSelected(int position) {
                // 页面被选中

                // 修改position
                position = position % mList.size();

                // 设置当前页面选中
                pointGroup.getChildAt(position).setSelected(true);
                // 设置前一页不选中
                pointGroup.getChildAt(lastPosition).setSelected(false);

                // 替换位置
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewPager.getCurrentItem();

                if(currentPosition == viewPager.getAdapter().getCount() - 1){
                    // 最后一页
                    viewPager.setCurrentItem(0);
                }else{
                    viewPager.setCurrentItem(currentPosition + 1);
                }

                // 一直给自己发消息
                mHandler.postDelayed(this,3000);
            }
        },4000);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.activity_mostpopular, container, false);
        return view;
    }


    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            // 返回整数的最大值
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // return super.instantiateItem(container, position);
            // 修改position
            position = position % mList.size();
            // 将图片控件添加到容器
            container.addView(mList.get(position));
            // 返回
            return mList.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
