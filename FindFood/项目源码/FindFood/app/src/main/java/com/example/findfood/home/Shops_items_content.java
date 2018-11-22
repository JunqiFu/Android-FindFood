package com.example.findfood.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by French on 2018/7/3.
 *
 *  详情显示页
 *
 *  最新 页面  加   详情页
 *  最受欢迎页面 加 详情页
 *
 *
 */


public class Shops_items_content extends AppCompatActivity implements View.OnClickListener {

    private ImageView returnimg,sharimg,zanimg,shopTopImg;
    //private Context context;
    private TextView S_name,S_type,S_price,S_introduction,S_address,S_phone;
    private String shopName;
    private String shopData;
    private Button pay_foodsbutton;



//    private Shops_items_content.MyHandler myhandler = new Shops_items_content.MyHandler(this);
//    static class MyHandler extends Handler {
//        private final WeakReference<Shops_items_content> mActivity;
//        public MyHandler(Shops_items_content activity) {
//            mActivity = new WeakReference<Shops_items_content>(activity);
//        }
//        @Override
//        public void handleMessage(Message msg) {
//            System.out.println(msg);
//            if (mActivity.get() == null) {
//                return;
//            }
//            // （得到获取数据库线程发送过来的结果，调用updateUIThread(msg)方法处理结果）
//
//            mActivity.get().updateUIThread(msg);
//        }
//    }
//
//    //配合子线程更新UI线程
//    void updateUIThread(Message msg){
//        Bundle bundle = new Bundle();
//        bundle = msg.getData();
//        String result = bundle.getString("result");
//
////        try {
////            JSONArray array=new JSONArray(result);
////            JSONObject object=(JSONObject)array.get(0);
////            if(object.getString("U_account").equals(namtxt.getText().toString())&&object.getString("U_password").equals(password.getText().toString())) {
////                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                intent.putExtra("nowUserInfo", result);
////                //intent.putExtra("account",namtxt.getText().toString());
////                startActivity(intent);
////            }else {
////                Toast.makeText(LoginActivity.this, "用户或密码错误", Toast.LENGTH_SHORT).show();
////            }
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//        //用于页面跳转，并含有下一个页面需要的数据--data
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dian_pu_xiangqing );
//        //    状态栏透明
        Intent intent=getIntent();

         shopName=intent.getStringExtra("shopName");
         shopData=intent.getStringExtra("shopData");

         System.out.println("datakkss***"+shopName+"    "+shopData);


         S_name=(TextView)findViewById(R.id.S_name);
         S_address=(TextView)findViewById(R.id.S_address);
         S_introduction=(TextView)findViewById(R.id.S_introduction);
         S_phone=(TextView)findViewById(R.id.S_phone);
         S_price=(TextView)findViewById(R.id.S_price);
         S_type=(TextView)findViewById(R.id.S_type);
         pay_foodsbutton=(Button)findViewById( R.id.pay_foodsbutton );
         pay_foodsbutton.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Toast.makeText(getApplicationContext(), "下单", Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(Shops_items_content.this,Foods_items_content.class);
                 startActivity(intent);
             }
         } );

         shopTopImg=(ImageView)findViewById(R.id.shopTopImg);
        try {
            JSONArray array=new JSONArray(shopData);
            for (int i = 0; i <array.length() ; i++) {
                JSONObject object= (JSONObject) array.get(i);
                System.out.println("datakk77ss***"+shopName+"    "+object.get("S_name").toString());
                if(shopName.equals(object.get("S_name").toString())){
//                    int resId = getResources().getIdentifier(object.get("S_headP").toString()+"t", "drawable" , getPackageName());
//                    System.out.println("id*******"+resId);
//                    Drawable drawable = getDrawable(resId);
//                    shopTopImg.setImageDrawable(drawable);
                    System.out.println("datakkss***"+shopName+"    "+object.get("S_name").toString());
                    S_name.setText(object.get("S_name").toString());
                    S_type.setText(object.get("S_type").toString());
                    S_price.setText(object.get("S_price").toString());
                    S_phone.setText(object.get("S_phone").toString());
                    S_introduction.setText(object.get("S_introduction").toString());
                    S_address.setText(object.get("S_address").toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //String type=intent.getStringExtra("aa");

        //System.out.println("id***ddd"+id+"   "+"type"+type);

//        getWindow().setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        intivew();
    }

    private void intivew() {
        returnimg=(ImageView)findViewById( R.id.return_page);
        returnimg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "返回", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Shops_items_content.this,MainActivity.class);
                startActivity(intent);
            }
        } );
        sharimg=(ImageView)findViewById( R.id.shareButton);
        sharimg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "暂时还没有分享功能哦....", Toast.LENGTH_SHORT).show();

            }
        } );
//        zanimg=(ImageView)findViewById( R.id.imageButton );
//        zanimg.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(getApplicationContext(), "点赞成功！", Toast.LENGTH_SHORT).show();
//
//            }
//        } );

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                /*str就是得到的数据*/
//
////                            Gson gson=new Gson();
//////
////                        //将一个对象变成json数据
////                            User user=new User();
////                            user.setU_account(edit0.getText().toString());
////                            user.setU_password(edit3.getText().toString());
////                            user.setU_address(edit1.getText().toString());
////                            user.setU_balance(Float.parseFloat(edit2.getText().toString()));
////                            user.setU_birthday("");
////                            user.setU_headP("");
////                            user.setU_phone("");
////                            user.setU_sex("");
////                            user.setU_name("");
//////
////                            String data1=gson.toJson(user);
////                User user=new User();
//
////                user.setU_account(namtxt.getText().toString());
////                user.setU_password(password.getText().toString());
////                Gson gson=new Gson();
////                String data=gson.toJson(user);
//
////                            try {
////                                JSONArray array=new JSONArray(data);
////                                JSONObject object= (JSONObject) array.get(0);
////                                object.put("U_password" ,password.getText().toString());
////                                object.put("U_account",namtxt.getText().toString());
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
//
//                String str = HttpURLConnection.LoginByPost("SearchShopServlet",shopName);/*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()/*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(), Float.parseFloat(edit6.getText().toString())*/
//
//                /*创建一个容器将获得的数据传给Handler准备更新界面*/
//                Bundle bundle = new Bundle();
//                bundle.putString("result",str);
//                Log.d(LoginActivity.TAG,"str==="+str);
//                Message msg = new Message();
//                msg.setData(bundle);
//                /*发送*/       myhandler.sendMessage(msg);
//            }
//        }).start();
    }

    @Override
    public void onClick(View view) {



    }
}
