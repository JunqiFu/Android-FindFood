package com.example.findfood.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.LoginActivity;
import com.example.findfood.app.MainActivity;
import com.example.findfood.bean.Orders;
import com.example.findfood.bean.User;
import com.example.findfood.data.HttpURLConnection;
import com.example.findfood.order.adpter.Listview_orders_adpter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by French on 2018/6/30.
 *
 * 合成订单页面
 *
 */

public class OrderFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener {

    // Listview
    private List<Orders> orderssList;
    private String account;
    private Listview_orders_adpter adpters;
    private OrderFragment.MyHandler myhandler = new OrderFragment.MyHandler(this);
    private String olderData="";

    static class MyHandler extends Handler {
        private final WeakReference<OrderFragment> mActivity;
        public MyHandler(OrderFragment activity) {
            mActivity = new WeakReference<OrderFragment>(activity);
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



            System.out.println("7897564****---" + result);




            orderssList = new ArrayList<Orders>();
            ListView listView = (ListView) getActivity().findViewById(R.id.orderinfolistview);
            //初始化商品数据
            initOrders(result);
            adpters = new Listview_orders_adpter(getActivity(), orderssList);
            listView.setAdapter(adpters);



        //用于页面跳转，并含有下一个页面需要的数据--data

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.orderfragment_ac, container, false );
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Bundle bundle=getArguments();
//        account=bundle.get("account").toString();
        intivew();

        ListView listView = (ListView)getActivity().findViewById(R.id.orderinfolistview);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入订单详情页面", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),Orders_items_content.class);
                startActivity(intent);
            }
        } );

    }

    private void initOrders(String olderData) {

        if(olderData!="") {
            // 数据库添加信息
            try {
                JSONArray array=new JSONArray(olderData);
                System.out.println("数据85255"+array.length()+"   "+olderData);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object= (JSONObject) array.get(i);
                    Orders orders = new Orders(object.get("O_number").toString(), object.get("S_name").toString(), object.get("F_name").toString(), object.get("O_total").toString(), object.get("O_state").toString());
//                    Orders orders = new Orders("2017.07.01.160352", "一面之缘", "456", "15.00", "O_state");
                    orderssList.add(orders);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void intivew(){
        account=((MainActivity)getActivity()).getAccount();
        System.out.println("账号"+account);
        if(account!=null) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String str = HttpURLConnection.LoginByPost("AllOlderServlet", account);/*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()/*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(), Float.parseFloat(edit6.getText().toString())*/

                    /*创建一个容器将获得的数据传给Handler准备更新界面*/
                    Bundle bundle = new Bundle();
                    bundle.putString("result", str);
                    Log.d(LoginActivity.TAG, "str===" + str);
                    Message msg = new Message();
                    msg.setData(bundle);
                    /*发送*/
                    myhandler.sendMessage(msg);
                }
            }).start();
        }else {
            Toast.makeText(getContext(), "kongde空地", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}