package com.example.findfood.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by French on 2018/6/30.
 *
 * 实现所有按钮的点击事件
 * 合并订单页面 ，完成下单功能
 *
 */

public class MineFragment extends Fragment {

    private ImageView modifybutton;
    private LinearLayout lyout_Collection,lyout_Record,lyout_modify;
    private Button btn_Recharge, Sign_out;
    private TextView namestextViews;
    private MainActivity mainActivity;
    private TextView name,time,address,money;
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intivew();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.minefragment_ac, container, false);
        return view;
    }
    private void intivew() {
        modifybutton=(ImageView)getActivity().findViewById( R.id.modifybutton );
        lyout_Collection=(LinearLayout)getActivity().findViewById( R.id.lyout_Collection );
        lyout_Record=(LinearLayout)getActivity().findViewById( R.id.lyout_Record);
        lyout_modify=(LinearLayout)getActivity().findViewById( R.id.lyout_modify);
        btn_Recharge=(Button)getActivity().findViewById( R.id.btn_Recharge);
        Sign_out=(Button)getActivity().findViewById( R.id. Sign_out);
        name = (TextView)getActivity().findViewById( R.id.tv_usernames);

        time = (TextView) getActivity().findViewById( R.id.tv_logintimes);

        address = (TextView) getActivity().findViewById( R.id.tv_places);

        money=(TextView)getActivity().findViewById( R.id.tv_moneys);


        if (((MainActivity)getActivity()).getNowUserInfo() !=null) {
            try {
                JSONArray array = new JSONArray(((MainActivity)getActivity()).getNowUserInfo());
                JSONObject object = (JSONObject) array.get(0);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                time.setText(df.format(new Date()));
                if (object.getString("U_name") ==null)
                    name.setText("请填写");
                else {
                    // System.out.println(object.get("U_name"));
                    name.setText(object.getString("U_name"));
                }
                if (object.getString("U_address")==null)
                    address.setText("请填写");
                else {
                    address.setText(object.getString("U_address") + "");
                }
                if (object.getString("U_balance") ==null)
                    address.setText("请填写");
                else {
                    money.setText(object.getString("U_balance") + "");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // 退 出 登 录
        Sign_out.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText( "未登录" );
            }
        } );

        // 充 值
        btn_Recharge.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入充值页面", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),MineRechargeActivity.class);

                startActivity(intent);
            }
        } );

//        // 我的收藏点击事件
//        lyout_Collection.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
//                Toast.makeText(getContext(), "进入我的收藏", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getActivity(),MineCollectionActivity.class);
//                startActivity(intent);
//            }
//        } );

        // 我的消费记录点击事件
        lyout_Record.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入我的消费记录", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),MineRecordActivity.class);
                startActivity(intent);

            }
        } );
        // 修改密码点击事件
        lyout_modify.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入修改密码", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),MineModifyActivity.class);
                System.out.println("密码"+((MainActivity)getActivity()).getPassword()+"  "+((MainActivity)getActivity()).getAccount());
                intent.putExtra("password",((MainActivity)getActivity()).getPassword());
                intent.putExtra("account",((MainActivity)getActivity()).getAccount());
                intent.putExtra("nowUserInfo",((MainActivity)getActivity()).getNowUserInfo());
                startActivity(intent);

            }
        } );



        modifybutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
                Toast.makeText(getContext(), "进入修改个人信息页面", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),MineinfoModifyActivity.class);
                intent.putExtra("nowUserInfo",((MainActivity)getActivity()).getNowUserInfo());
                startActivity(intent);
            }
        } );
    }
    // 提示框信息
    private void showExitDialog() {

        new AlertDialog.Builder( getActivity() )
                .setIcon( R.drawable.logo )
                .setTitle( "提示信息" )
                .setMessage( "是否确认退出系统" )
                .setPositiveButton( "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "退出", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } )
                .setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                        startActivity(intent);
                    }
                } )
                .show();
    }

}
