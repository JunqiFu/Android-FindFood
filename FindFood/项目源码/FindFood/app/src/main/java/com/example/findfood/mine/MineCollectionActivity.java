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

//我的收藏
public class MineCollectionActivity extends AppCompatActivity {
    private ImageView rb_backssgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mine_collection );

        rb_backssgg=(ImageView)findViewById( R.id.rb_backssgg);
        rb_backssgg.setOnClickListener( new View.OnClickListener() {
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
                .setMessage( "您还没有收藏信息，请先选择自己喜欢的物品吧！" )
                .setPositiveButton( "我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "进入物品页面", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MineCollectionActivity.this,MainActivity.class);
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
