//package com.example.findfood.home.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.findfood.R;
//import com.example.findfood.bean.Shops;
//import com.example.findfood.home.Shops_items_content;
//import com.example.findfood.home.adpater.Listview_shops_adpter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class NewestFragment extends Fragment  {
//
//    private List<Shops> shopsList;
//    private Listview_shops_adpter adpter;
//
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        intivew();
//        shopsList = new ArrayList<Shops>();
//        ListView listView = (ListView)getActivity().findViewById(R.id.newest_listview);
//        //初始化商品数据
//        initShops();
//        adpter = new Listview_shops_adpter(getActivity(), shopsList);
//        listView.setAdapter(adpter);
//        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.e("整体item----->",  i +"");
//                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
//                Toast.makeText(getContext(), "进入详情页面", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getActivity(),Shops_items_content.class);
//                startActivity(intent);
//
//            }
//        } );
//    }
//    //初始化商品数据
//    private void initShops() {
//
//      // 数据库添加信息
//        for (int i=1;i<6;i++){
//            Shops shops = new Shops( "Mr.龙虾先生(新南店)","益州大道北段33号","人均99元","小龙虾 湘菜","" );
//            shopsList.add(shops);
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate( R.layout.activity_newest, container, false);
//        return view;
//    }
//    // 初始化控件
//    private void intivew() {
//
//    }
//
//
//        //item 监听事件
//
//}


package com.example.findfood.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.LoginActivity;
import com.example.findfood.bean.Shops;
import com.example.findfood.data.HttpURLConnection;
import com.example.findfood.home.Shops_items_content;
import com.example.findfood.home.adpater.Listview_shops_adpter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class NewestFragment extends Fragment {

    private List<Shops> shopsList;
    private Listview_shops_adpter adpter;
    private NewestFragment.MyHandler myhandler = new NewestFragment.MyHandler(this);
    private String shopData="";

    //Spring
    private List<String> list = new ArrayList<String>();//创建一个String类型的数组列表。
    private TextView myTextView;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;//创建一个数组适配器

    static class MyHandler extends Handler {
        private final WeakReference<NewestFragment> mActivity;
        public MyHandler(NewestFragment activity) {
            mActivity = new WeakReference<NewestFragment>(activity);
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

        System.out.println("*********-----**"+result);
        if(result!=null)shopData=result;

        shopsList = new ArrayList<Shops>();
        ListView listView = (ListView)getActivity().findViewById(R.id.newest_listview);
        initShops(shopData);
        adpter = new Listview_shops_adpter(getActivity(), shopsList);
        listView.setAdapter(adpter);

//        try {
////            JSONArray array=new JSONArray(result);
////            JSONObject object=(JSONObject)array.get(0);
////            if(object.getString("U_account").equals(namtxt.getText().toString())&&object.getString("U_password").equals(password.getText().toString())) {
////                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                intent.putExtra("nowUserInfo", result);
////                //intent.putExtra("account",namtxt.getText().toString());
////                startActivity(intent);
////            }else {
////                Toast.makeText(NewestFragment.this, "用户或密码错误", Toast.LENGTH_SHORT).show();
////            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //用于页面跳转，并含有下一个页面需要的数据--data

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intivew();
        Spring();

        //super.onActivityCreated(savedInstanceState);
//        intivew();
//        shopsList = new ArrayList<Shops>();
        ListView listView = (ListView)getActivity().findViewById(R.id.newest_listview);
//        //初始化商品数据
//        initShops();
//        adpter = new Listview_shops_adpter(getActivity(), shopsList);
//        listView.setAdapter(adpter);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("整体item----->",  i +"");
                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入详情页面", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),Shops_items_content.class);
                TextView textView=view.findViewById(R.id.txt_s_name);
                intent.putExtra("shopName",textView.getText().toString());
                intent.putExtra("shopData",shopData);

//
                startActivity(intent);

            }
        } );



    }

    private void Spring() {
        // 添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项，即数据源
        list.add("菜系");
        list.add("川菜");
        list.add("鲁菜");
        list.add("粤菜");
        list.add("其他");
        mySpinner = (Spinner)getActivity().findViewById(R.id.newest_top_spinner);
        //1.为下拉列表定义一个数组适配器，这个数组适配器就用到里前面定义的list。装的都是list所添加的内容
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, list);
        //2.为适配器设置下拉菜单样式。adapter.setDropDownViewResource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //3.以上声明完毕后，建立适配器,有关于sipnner这个控件的建立。用到myspinner
        mySpinner.setAdapter(adapter);
        //4.为下拉列表设置各种点击事件，以响应菜单中的文本item被选中了，用setOnItemSelectedListener
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText( getContext(),""+adapter.getItem(arg2),Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                //myTextView.setText("Nothing");
                Toast.makeText( getContext(),"Nothing:",Toast.LENGTH_SHORT).show();
            }
        });


    }

    //初始化商品数据
    private void initShops(String shopData1) {

        // 数据库添加信息

        System.out.println("987789*********"+shopData1);
        if(shopData1!="") {
            try {
                JSONArray array=new JSONArray(shopData1);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object= (JSONObject) array.get(i);
                    Shops shops = new Shops(object.get("S_headP").toString(),object.get("S_name").toString(), object.get("S_address").toString(), object.get("S_price").toString(), object.get("S_type").toString());
                    shopsList.add(shops);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.activity_newest, container, false);
        return view;
    }
    // 初始化控件
    private void intivew() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = HttpURLConnection.LoginByPost("AllShopServlet","");
                /*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()/*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(), Float.parseFloat(edit6.getText().toString())*/
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

    public String send(){
        return shopData;
    }
}
