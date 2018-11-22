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

//我的消费记录
public class MineRecordActivity extends AppCompatActivity {
    private ImageView rb_backew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mine_record );
        rb_backew=(ImageView)findViewById( R.id.rb_backew);
        rb_backew.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
                Log.e( "返回","///////////////////////////////////// -------------点击返回按钮" );
            }
        } );


    }
    // 提示框信息
    private void showExitDialog() {

        new AlertDialog.Builder( this )
                .setIcon( R.drawable.logo )
                .setTitle( "提示信息" )
                .setMessage( "还没有消费记录呢，请阁下消费之后再来查看吧!" )
                .setPositiveButton( "我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "进入商品页面", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MineRecordActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                } )
                .setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
                    }
                } )
                .show();
    }

}
