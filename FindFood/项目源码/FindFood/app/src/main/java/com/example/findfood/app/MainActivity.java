package com.example.findfood.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.base.MyFragmentPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*+
-++
*    1. 修复 底部导航栏不能点击的BUG（完成）
*    2. 实现主页面的界面 嵌套 viewpage+fagment (完成)
*    3. 实现订单页面的展示（未完成）
*    4. 实现后台登陆注册 （完成）
 *   5. 实现菜品下单功能（未完成）
* */

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener,View.OnClickListener,AdapterView.OnItemClickListener {

    private Handler handler = new Handler();

    //UI Objects
    private TextView txt_topbar,tv_username;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_home;
    private RadioButton rb_order;
    private RadioButton rb_mine;
    private ViewPager vpager;

    private ImageButton LoginButton;
    private MyFragmentPagerAdapter mAdapter;

    //
    private LinearLayout layout_login,layout_Collection,layout_Record,layout_modify;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    private String nowUserInfo="";
    public String getNowUserInfo(){
        return nowUserInfo;
    }
    public void setNowUserInfo(String nowUserInfo){
        this.nowUserInfo=nowUserInfo;
    }
    private String account="";
    public String getAccount(){
        return account;
    }
    public void setAccount(String account){
        this.account=account;
    }
    private String password="";

    public String getPassword(){
        return  password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    private TextView name,time,address;

    boolean  firstOpen=true;

    private String allFoodsData;

    public String getAllFoodsData(){
        return allFoodsData;
    }
    public void setAllFoodsData(String allFoodsData){
        this.allFoodsData=allFoodsData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Intent intent=getIntent();
        nowUserInfo = intent.getStringExtra("nowUserInfo");
        if(nowUserInfo!=null) {
            try {
                JSONArray array=new JSONArray(nowUserInfo);
                JSONObject object = (JSONObject) array.get(0);

                System.out.println("************************************************************"+nowUserInfo);
                account = object.getString("U_account");
                password = object.getString("U_password");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //password=intent.getStringExtra("password");

//        //    状态栏透明
//        getWindow().setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView( R.layout.activity_main );

        mAdapter = new MyFragmentPagerAdapter( getSupportFragmentManager() );
        bindViews();
        //******设置 主页 为 默认页面
        rb_home.setChecked( true );
    }
    private void bindViews() {

        name=(TextView)findViewById(R.id.tv_username);
        time=(TextView)findViewById(R.id.tv_logintimes);
        address=(TextView)findViewById(R.id.tv_places);

        rg_tab_bar = (RadioGroup) findViewById( R.id.rg_tab_bar );
        rb_home = (RadioButton) findViewById( R.id.rb_home );
        rb_home.setOnClickListener( this );
        rb_order = (RadioButton) findViewById( R.id.rb_order );
        rb_order.setOnClickListener( this );
        rb_mine = (RadioButton) findViewById( R.id.rb_mine );
        rb_mine.setOnClickListener( this );
        tv_username=(TextView)findViewById( R.id.tv_username );
        layout_login=(LinearLayout)findViewById( R.id.lyout_login);
        layout_Collection=(LinearLayout)findViewById( R.id.lyout_Collection );
        layout_Record=(LinearLayout)findViewById( R.id.lyout_Record );
        layout_modify=(LinearLayout)findViewById( R.id.lyout_modify );

        rg_tab_bar.setOnCheckedChangeListener( this );
        vpager = (ViewPager) findViewById( R.id.vpager );
        vpager.setAdapter( mAdapter );
        vpager.setCurrentItem( 0 );
        vpager.addOnPageChangeListener(this);

    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state ==2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_home.setChecked( true );
                    break;
                case PAGE_TWO:
                    rb_order.setChecked( true );
                    break;
                case PAGE_THREE:
                    rb_mine.setChecked( true );
                    if(account=="" && password=="")showExitDialog();
//                    else {
//                        try {
//                            JSONArray array=new JSONArray(nowUserInfo);
//                            JSONObject object = (JSONObject) array.get(0);
//                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//                            time.setText(df.format(new Date()));
//                            if(object.getString("U_name")=="")
//                            name.setText("请填写");
//                            else {
//                                name.setText(object.getString("U_name"));
//                            }
//                            if(object.getString("U_address")=="")
//                                address.setText("请填写");
//                            else {
//                                address.setText(object.getString("U_address")+"");
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    break;


            }
        }

    }
    // 当检查发生改变
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
    // 提示框信息
    private void showExitDialog() {

        firstOpen=false;
        new AlertDialog.Builder( this )
                .setIcon( R.drawable.logo )
                .setTitle( "登陆提示框" )
                .setMessage( "您还没有登陆，请先登录！！" )
                .setPositiveButton( "登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "跳转至登陆页", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                } )

                .setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "取消登录", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                        startActivity(intent);
                    }
                } )
                .show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_home:
                //点击"我的"时切换到第一页
                vpager.setCurrentItem(0);
//                Intent intent=new Intent(MainActivity.this, OrderFragment.class);
//                intent.putExtra("account",account);
//                startActivity(intent);
                break;
            case R.id.rb_order:
                //点击"分类"时切换的第二页
                Bundle bundle=new Bundle();
                bundle.putString("account",account);
//                FragmentTransaction transaction=getFragmentManager().beginTransaction();
//
//                OrderFragment orderFragment=new OrderFragment();
//                orderFragment.setArguments(bundle);
//                //fragment的Arguments通过get and set在fragment中传递参数
//              transaction.replace
//              transaction.commit();
                vpager.setCurrentItem(1);

                break;
            case R.id.rb_mine:
                //点击"分类"时切换的第二页
                vpager.setCurrentItem(2);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//        Log.e("整体item----->",  i +"");
//        Log.e("","////////////////////  *****   侦听事件   **** ///////////////////////////");
//        Toast.makeText(getApplicationContext(), "跳转至登陆页", Toast.LENGTH_SHORT).show();
//        Intent intent=new Intent(getActivity(),MainActivity.class);
//        startActivity(intent);
//
    }
}

