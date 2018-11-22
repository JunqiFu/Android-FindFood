package com.example.findfood.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.bean.User;
import com.example.findfood.data.HttpURLConnection;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.Random;
/**
 *  文本框输入判断
 *  返回按钮设置
 *
 *
 * */
public class RegistActivity extends AppCompatActivity {
    private EditText account,password,password_ag,yanz;
    private Button yzBt,rgBt;
    private RegistActivity.MyHandler myhandler = new RegistActivity.MyHandler(this);

    static class MyHandler extends Handler {
        private final WeakReference<RegistActivity> mActivity;
        public MyHandler(RegistActivity activity) {
            mActivity = new WeakReference<RegistActivity>(activity);
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

        //用于页面跳转，并含有下一个页面需要的数据--data
        if(result.equals("success")) {
            Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
            intent.putExtra("R_password", password.getText().toString());
            intent.putExtra("R_account",account.getText().toString());
            startActivity(intent);
        }else {
            Toast.makeText(RegistActivity.this, "注册失败！！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_regist );

        account=(EditText)findViewById(R.id.name_txt);
        password=(EditText)findViewById(R.id.password_txt);
        password_ag=(EditText)findViewById(R.id.password_txt_ag);

        yanz=(EditText)findViewById(R.id.Veri_Code_txt);

        yzBt=(Button)findViewById(R.id.Veri_Code_btn);
        yzBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random=new Random();

                double num=(1+random.nextDouble())*Math.pow(10,4);

                yzBt.setText(String.valueOf(num).substring(1,5));
            }
        });
        rgBt=(Button)findViewById(R.id.regist_btn);
        rgBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                init();
            }
        });
    }

    private void init() {
        if(password.getText().toString().equals(password_ag.getText().toString())){
            if(yzBt.getText().toString().equals(yanz.getText().toString())){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User user=new User();
                        user.setU_password(password.getText().toString());
                        user.setU_account(account.getText().toString());
                        Gson gson=new Gson();
                        String data=gson.toJson(user);
                        String str = HttpURLConnection.LoginByPost("RegisterServlet",data);
                    /*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()
                    /*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(),
                     Float.parseFloat(edit6.getText().toString())*/

                        /*创建一个容器将获得的数据传给Handler准备更新界面*/
                        Bundle bundle = new Bundle();
                        bundle.putString("result",str);
                        Log.d(LoginActivity.TAG,"str==="+str);
                        Message msg = new Message();
                        msg.setData(bundle);
                        /*发送*/       myhandler.sendMessage(msg);
                    }
                }).start();
            }else {
                Toast.makeText(RegistActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(RegistActivity.this, "两次密码不一致请重新输入", Toast.LENGTH_SHORT).show();
        }
    }
}
