package com.example.findfood.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.LoginActivity;
import com.example.findfood.app.MainActivity;
import com.example.findfood.bean.User;
import com.example.findfood.data.HttpURLConnection;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

//修改密码

public class MineModifyActivity extends AppCompatActivity {
    private ImageView fanhui2;
    private EditText edt_input,oldPwd,newPwd;
    private TextView yanzhengma ,txtyanzhengtxt;
    private String passwordData;
    private String accountData;
    private String userInfo;
    private Button saveBt;
    private String userInfo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mine_modify );
        Intent intent=getIntent();
        passwordData=intent.getStringExtra("password");
        accountData=intent.getStringExtra("account");
        userInfo=intent.getStringExtra("nowUserInfo");
        fanhui2=(ImageView)findViewById( R.id.fanhui2);
        saveBt=(Button)findViewById(R.id.save);
        oldPwd=(EditText)findViewById(R.id.oldPwd);
        newPwd=(EditText)findViewById(R.id.newPwd);
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init(passwordData,oldPwd.getText().toString(),newPwd.getText().toString());
            }
        });
        fanhui2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
                Log.e( "返回","///////////////////////////////////// -------------点击返回按钮" );
            }
        } );

    }

    private void init(String passwordData, String s, final String s1) {

        if(s!=""&&s1!=""){
            if(passwordData.equals(s)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        User user=new User();
                        user.setU_password(s1);
                        user.setU_account(accountData);

                        Gson gson=new Gson();
                        String data=gson.toJson(user);

                        String str = HttpURLConnection.LoginByPost("UpdatePasswordServlet",data);/*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()/*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(), Float.parseFloat(edit6.getText().toString())*/

                        /*创建一个容器将获得的数据传给Handler准备更新界面*/
//                        Bundle bundle = new Bundle();
//                        bundle.putString("result",str);
//                        Log.d(LoginActivity.TAG,"str==="+str);
//                        Message msg = new Message();
//                        msg.setData(bundle);
                        ///*发送*/       myhandler.sendMessage(msg);
                    }
                }).start();
                Toast.makeText(this,"修改成功,请重新登录",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent=new Intent(this,LoginActivity.class);
//                array = null;
//                try {
//                    JSONArray array = new JSONArray(userInfo);
//                    JSONObject object = (JSONObject) array.get(0);
//                    object.put("U_password",s1);
//                    System.out.println("新密码"+s1);
//                    userInfo1=userInfo;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                intent.putExtra("nowUserInfo",userInfo1);
                startActivity(intent);

            }else {
                Toast.makeText(this,"你的密码错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"请输入完整",Toast.LENGTH_SHORT).show();
        }

    }

    // 提示框信息
    private void showExitDialog() {

        new AlertDialog.Builder( this )
                .setIcon( R.drawable.logo )
                .setTitle( "提示信息" )
                .setMessage( "确定要修改密码吗？下次要记得密码哦！" )
                .setPositiveButton( "我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "进入修改密码页面", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MineModifyActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                } )
                .setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                        startActivity(intent);
                    }
                } )
                .show();
    }

}
