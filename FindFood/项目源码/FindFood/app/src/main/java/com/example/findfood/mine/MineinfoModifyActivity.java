package com.example.findfood.mine;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
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

import java.util.Calendar;
import java.util.jar.JarEntry;


/*
*  20180705 时间日期选择器实现
*
*  我的 页面点击事件响应显示页面展示
*
*  订单展示页面的显示
*  订单详情页的展示
*
* */

public class MineinfoModifyActivity extends AppCompatActivity {
    private EditText days,name,address;
    private RadioGroup sex;
    private ImageView mine_modifyinfo_return;
    private String userInfo;

    private Button saveBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mineinfo_modify );

        Intent intent=getIntent();
        userInfo=intent.getStringExtra("nowUserInfo");


        days=(EditText)findViewById( R.id.days);
        name=(EditText)findViewById(R.id.edt_modify_name);
        sex=(RadioGroup)findViewById(R.id.sex_rg);
        address=(EditText)findViewById(R.id.edt_modify_places);
        saveBt=(Button)findViewById(R.id.baocun);

        try {
            JSONArray array=new JSONArray(userInfo);
            JSONObject object=(JSONObject)array.get(0);
            days.setText(object.get("U_birthday").toString());
            name.setText(object.get("U_name").toString());
            if(object.get("U_sex").toString().equals("m")){
                sex.check(R.id.male_rb);
            }else if(object.get("U_sex").toString().equals("w")){
                sex.check(R.id.famale_rb);
            }
            address.setText(object.get("U_address").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        days.setInputType( InputType.TYPE_NULL); // 不显示系统输入键盘
        days.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(hasFocus){
                    Log.e( "获取输入框的焦点","///////////////////////////////////// -------------" );
                    showDatePickerDialog();
                }
            }
        });
        days.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e( "时间选择器","///////////////////////////////////// -------------选择日期" );
                showDatePickerDialog();

            }
        } );
//        months=(EditText)findViewById( R.id.months);
//        years=(EditText)findViewById( R.id.years);

        mine_modifyinfo_return=(ImageView)findViewById( R.id.mine_modifyinfo_return);
        mine_modifyinfo_return.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
                Log.e( "返回","///////////////////////////////////// -------------点击返回按钮" );

            }
        } );

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setU_birthday(days.getText().toString());
                System.out.println("输入"+days.getText().toString());
                user.setU_address(address.getText().toString());
                user.setU_name(name.getText().toString());
                if(sex.getCheckedRadioButtonId()==R.id.male_rb){
                    user.setU_sex("m");
                }else {
                    user.setU_sex("w");
                }
                init(user);
            }
        });
    }

    private void init(final User user ) {

        try {
            JSONArray array=new JSONArray(userInfo);
            final JSONObject object=(JSONObject)array.get(0);




            new Thread(new Runnable() {
                @Override
                public void run() {
                    /*str就是得到的数据*/
                    try {
                        user.setU_account(object.get("U_account").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Gson gson=new Gson();
                    String data=gson.toJson(user);
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
//

//                            try {
//                                JSONArray array=new JSONArray(data);
//                                JSONObject object= (JSONObject) array.get(0);
//                                object.put("U_password" ,password.getText().toString());
//                                object.put("U_account",namtxt.getText().toString());
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                    String str = HttpURLConnection.LoginByPost("EditUserServlet",data);/*edit0.getText().toString(),edit1.getText().toString(),edit2.getText().toString()/*,edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString(), Float.parseFloat(edit6.getText().toString())*/

                    /*创建一个容器将获得的数据传给Handler准备更新界面*/
//                    Bundle bundle = new Bundle();
//                    bundle.putString("result",str);
//                    Log.d(LoginActivity.TAG,"str==="+str);
//                    Message msg = new Message();
//                    msg.setData(bundle);
//                    /*发送*/       myhandler.sendMessage(msg);
                }
            }).start();


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"修改成功,请重新登录",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,LoginActivity.class);
       // intent.putExtra("nowUserInfo",userInfo);
        startActivity(intent);


    }


    /**
     * 展示日期选择对话框
     */
    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(MineinfoModifyActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                days.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }

    // 提示框信息
    private void showExitDialog() {
        new AlertDialog.Builder( this )
                .setIcon( R.drawable.logo)
                .setTitle( "提示框" )
                .setMessage( "是否放弃编辑信息！" )
                .setPositiveButton( "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "退出", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MineinfoModifyActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                } )
                .setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(MineinfoModifyActivity.this,MainActivity.class);
//                        startActivity(intent);
                    }
                } )
                .show();
    }

}
