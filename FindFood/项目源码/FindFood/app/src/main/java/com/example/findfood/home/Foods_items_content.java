package com.example.findfood.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.app.MainActivity;
import com.example.findfood.bean.FoodsIns;
import com.example.findfood.home.adpater.Listview_foodsitem_adpter;
import com.example.findfood.mine.MineRechargeActivity;

import java.util.ArrayList;
import java.util.List;


// 下单 页面
public class Foods_items_content extends AppCompatActivity implements AdapterView.OnItemClickListener{

    List<FoodsIns> foodsInss = new ArrayList<>();
    private Listview_foodsitem_adpter adpter;
    private ListView listView;
    private ImageView  return_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_foods_items_content );
        intnview();
        //初始化商品数据
        initfoodsitems();
        adpter = new Listview_foodsitem_adpter(this, foodsInss);
        listView = (ListView)findViewById(R.id.foods_item_list);
//        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Log.e("","////////////////////onItemClick///////////////////////////");
//                Toast.makeText(Foods_items_content.this, "进入详情页面", Toast.LENGTH_SHORT).show();
//
//            }
//        } );

        listView.setOnItemClickListener(this);

        listView.setAdapter(adpter);

        return_page=(ImageView)findViewById(R.id.return_page);
        return_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Foods_items_content.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void intnview() {


    }
    //添加商品信息
    private void initfoodsitems() {

        // 数据库添加信息
        for(int i=0;i<8;i++){
            FoodsIns foodsis = new FoodsIns( "香辣小龙虾","48");
            foodsInss.add( foodsis );
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        view.findViewById(R.id.)
    }
}
