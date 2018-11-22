package com.example.findfood.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.LoginActivity;
import com.example.findfood.app.MainActivity;
import com.example.findfood.bean.Foods;
import com.example.findfood.data.HttpURLConnection;
import com.example.findfood.home.Foods_items_content;
import com.example.findfood.home.adpater.Listview_foods_adpter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/*
*  1. item 点击事件 进入店家详情页
*  2. 实现 首页的每一个页面
*  3. 优化 item 页面
*  4. 将登陆注册功能合并到本项目
*  5.
* */
public class HottestFragment extends Fragment {
    // 图片资源
    private int[] mImages = {R.mipmap.bannerss, R.mipmap.banner, R.mipmap.bannerss, R.mipmap.banner};
    private List<ImageView> mList;
    private String FoodData;
    private NewestFragment newestFragment;
    //private String shopData=newestFragment.getShopData();



    Handler mHandler = new Handler();

    // Listview
    private List<Foods> foodsList;
    private Listview_foods_adpter adpters;
    private HottestFragment.MyHandler myhandler = new HottestFragment.MyHandler(this);

    static class MyHandler extends Handler {
        private final WeakReference<HottestFragment> mActivity;
        public MyHandler(HottestFragment activity) {
            mActivity = new WeakReference<HottestFragment>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg);
            if (mActivity.get() == null) {
                return;
            }
            // （得到获取数据库线程发送过来的结果，调用updateUIThread(msg)方法处理结果）
            mActivity.get().updateUIThread(msg);
        }
    }

    //配合子线程更新UI线程
    void updateUIThread(Message msg){
        Bundle bundle = new Bundle();
        bundle = msg.getData();
        String result = bundle.getString("result");

        ((MainActivity)getActivity()).setAllFoodsData(result);
        if(result!=null)FoodData=result;
        foodsList = new ArrayList<Foods>();
        ListView listView = (ListView)getActivity().findViewById(R.id.hottest_listview);
        //初始化商品数据
        initFoods(FoodData);
        adpters = new Listview_foods_adpter(getActivity(), foodsList);
        listView.setAdapter(adpters);
//        try {
//            JSONArray array=new JSONArray(result);
//            JSONObject object=(JSONObject)array.get(0);
//            if(object.getString("U_account").equals(namtxt.getText().toString())&&object.getString("U_password").equals(password.getText().toString())) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("nowUserInfo", result);
//                //intent.putExtra("account",namtxt.getText().toString());
//                startActivity(intent);
//            }else {
//                Toast.makeText(LoginActivity.this, "用户或密码错误", Toast.LENGTH_SHORT).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //用于页面跳转，并含有下一个页面需要的数据--data

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intivew();

        ListView listView = (ListView)getActivity().findViewById(R.id.hottest_listview);


//        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
//                Toast.makeText(getContext(), "进入详情页面", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getActivity(),Foods_items_content.class);
//                startActivity(intent);
//            }
//        } );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("整体item----->",  i +"");
                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入详情页面", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),Foods_items_content.class);
                TextView textView=view.findViewById(R.id.txt_s_name);
                intent.putExtra("FoodName",textView.getText().toString());
                intent.putExtra("FoodData",FoodData);

//
                startActivity(intent);

            }
        } );

    }
    //初始化商品数据
    private void initFoods(String FoodData) {
        // 数据信息导入页
        if (FoodData!= "") {
            try {
//                JSONArray sArray=new JSONArray()
                JSONArray fArray = new JSONArray(FoodData);
                for (int i = 0; i < fArray.length(); i++) {
                    JSONObject object = (JSONObject) fArray.get(i);
                   // for()
                    Foods foods = new Foods(object.get("F_name").toString(), object.get("F_price").toString(), object.get("S_name").toString());
                    foodsList.add(foods);
                   // shopsList.add(shops);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.activity_hottest, container, false);
        return view;
    }

   // 获取 控件
    private void intivew() {

        final ViewPager viewPager = (ViewPager)getActivity().findViewById(R.id.viewpager);
        final LinearLayout pointGroup = (LinearLayout)getActivity().findViewById(R.id.pointgroup);
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
            pointImage.setImageResource(R.drawable.shape_point_selector);

            // 设置小圆点的布局参数
            int PointSize = getResources().getDimensionPixelSize(R.dimen.point_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PointSize, PointSize);

            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.point_margin);
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

        new Thread(new Runnable() {
            @Override
            public void run() {


                String str = HttpURLConnection.LoginByPost("AllFoodServlet","");/*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()/*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(), Float.parseFloat(edit6.getText().toString())*/

                /*创建一个容器将获得的数据传给Handler准备更新界面*/
                Bundle bundle = new Bundle();
                bundle.putString("result",str);
                Log.d(LoginActivity.TAG,"str=456=="+str);
                Message msg = new Message();
                msg.setData(bundle);
                /*发送*/       myhandler.sendMessage(msg);
            }
        }).start();
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
