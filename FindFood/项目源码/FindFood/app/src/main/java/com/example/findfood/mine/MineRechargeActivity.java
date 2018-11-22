package com.example.findfood.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.MainActivity;


// 个人充值
public class MineRechargeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView rb_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mine_recharge );
        rb_back=(ImageView)findViewById( R.id.rsadaass);
        rb_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   showExitDialog();
                   Log.e( "返回","///////////////////////////////////// -------------点击返回按钮" );
            }
        } );

    }

    @Override
    public void onClick(View view) {

    }

    // 提示框信息
    private void showExitDialog() {
        new AlertDialog.Builder( this )
                .setIcon( R.drawable.logo)
                .setTitle( "提示框" )
                .setMessage( "是否充值！" )
                .setPositiveButton( "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "请输入充值金额！", Toast.LENGTH_SHORT).show();
                    }
                } )
                .setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MineRechargeActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                } )
                .show();
    }
}
