package com.example.findfood.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.bean.User;
import com.example.findfood.data.HttpURLConnection;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView registtxt,forget;
    private EditText namtxt,password;
    private RadioButton radioButton ;
    private Button loginbutton;
    private MyHandler myhandler = new MyHandler(this);
    public static final String TAG="MainActivity";

    static class MyHandler extends Handler {
        private final WeakReference<LoginActivity> mActivity;
        public MyHandler(LoginActivity activity) {
            mActivity = new WeakReference<LoginActivity>(activity);
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

        try {
            System.out.println("7897564****---"+result);
            JSONArray array=new JSONArray(result);
            JSONObject object=(JSONObject)array.get(0);
            if(object.getString("U_account").equals(namtxt.getText().toString())&&object.getString("U_password").equals(password.getText().toString())) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("nowUserInfo", result);
                //intent.putExtra("account",namtxt.getText().toString());
                startActivity(intent);
            }else {
                Toast.makeText(LoginActivity.this, "用户或密码错误", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //用于页面跳转，并含有下一个页面需要的数据--data

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
////            状态栏透明

        Intent intent=getIntent();
        String account=intent.getStringExtra("R_account");
        String passworddata=intent.getStringExtra("R_password");



        getWindow().setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView( R.layout.activity_login );
        intiview();

        if(account!=null&&passworddata!=null){
            namtxt.setText(account);
            password.setText(passworddata);
        }
    }

    private void intiview() {

        namtxt = (EditText) findViewById( R.id.tv_username );

        namtxt.addTextChangedListener( new EditChangedListener() );


        password = (EditText) findViewById( R.id.tv_userpassword );


        registtxt = (TextView) findViewById( R.id.userregist );

        registtxt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e( "跳转啦", " ---------------------------------------------------- 跳转信息---" );
                Intent intent = new Intent( LoginActivity.this, RegistActivity.class );
                startActivity( intent );

            }
        } );

        forget = (TextView) findViewById( R.id.userforget_pwd );



        loginbutton = (Button) findViewById( R.id.btn_userlogin );
        loginbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = namtxt.length();
                int len1 = password.length();
                if (len == 0 ) {
                    Toast.makeText( getApplicationContext(), "用户名输入为空", Toast.LENGTH_SHORT ).show();
                } else if (len1 == 0 ) {
                    Toast.makeText( getApplicationContext(), "密码输入为空或者密码长度小于六位", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( getApplicationContext(), "验证登陆信息中...", Toast.LENGTH_SHORT ).show();
//
//                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);

                    //开启访问数据库线程获取数据
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            /*str就是得到的数据*/

//                            Gson gson=new Gson();
////
//                        //将一个对象变成json数据
//                            User user=new User();
//                            user.setU_account(edit0.getText().toString());
//                            user.setU_password(edit3.getText().toString());
//                            user.setU_address(edit1.getText().toString());
//                            user.setU_balance(Float.parseFloat(edit2.getText().toString()));
//                            user.setU_birthday("");
//                            user.setU_headP("");
//                            user.setU_phone("");
//                            user.setU_sex("");
//                            user.setU_name("");
////
//                            String data1=gson.toJson(user);
                            User user=new User();
                            user.setU_account(namtxt.getText().toString());
                            user.setU_password(password.getText().toString());
                            Gson gson=new Gson();
                            String data=gson.toJson(user);

//                            try {
//                                JSONArray array=new JSONArray(data);
//                                JSONObject object= (JSONObject) array.get(0);
//                                object.put("U_password" ,password.getText().toString());
//                                object.put("U_account",namtxt.getText().toString());
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                            String str = HttpURLConnection.LoginByPost("LoginServlet",data);/*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()/*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(), Float.parseFloat(edit6.getText().toString())*/

                            /*创建一个容器将获得的数据传给Handler准备更新界面*/
                            Bundle bundle = new Bundle();
                            bundle.putString("result",str);
                            Log.d(LoginActivity.TAG,"str==="+str);
                            Message msg = new Message();
                            msg.setData(bundle);
                            /*发送*/       myhandler.sendMessage(msg);
                        }
                    }).start();
                }

            }
        } );

        radioButton = (RadioButton) findViewById( R.id.rb_userpassword );
    }

    @Override
    public void onClick(View view) {



    }

}
