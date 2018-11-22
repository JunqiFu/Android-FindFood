package com.example.findfood.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.R;
import com.example.findfood.home.fragment.HottestFragment;
import com.example.findfood.home.fragment.MostpopularFragment;
import com.example.findfood.home.fragment.NewestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by French on 2018/6/30.
 */

public class HomeFragment  extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    //定义组建
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private android.support.v4.app.FragmentManager manager;
    private HottestFragment hottestFragment;
    private MostpopularFragment mostpopularFragment;
    private NewestFragment newestFragment;
    private FrameLayout frameLayout;
    //点击按钮控件
    private RadioButton newest,hottest,mospopular;

    //Spring
    private List<String> list = new ArrayList<String>();//创建一个String类型的数组列表。
    private TextView myTextView;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;//创建一个数组适配器

    private ImageView starts;
    private SearchView home_search_starsss;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intivew();
        initFragment();
        Spring();
    }
    private void Spring() {
        // 添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项，即数据源
        list.add( "成都" );
        list.add( "上海" );
        list.add( "广州" );
        list.add( "深圳" );
        list.add( "其他" );
        mySpinner = (Spinner) getActivity().findViewById( R.id.city_spinnersh );
        //1.为下拉列表定义一个数组适配器，这个数组适配器就用到里前面定义的list。装的都是list所添加的内容
        adapter = new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, list );
        //2.为适配器设置下拉菜单样式。adapter.setDropDownViewResource
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        //3.以上声明完毕后，建立适配器,有关于sipnner这个控件的建立。用到myspinner
        mySpinner.setAdapter( adapter );
        //4.为下拉列表设置各种点击事件，以响应菜单中的文本item被选中了，用setOnItemSelectedListener
        mySpinner.setOnItemSelectedListener( new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                if (adapter.getItem( arg2 ).equals( "其他" )) {
                    Toast.makeText( getContext(), "为您打开城市选项界面", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent( getActivity(), CitycalssActivity.class ); //跳转到城市选择页面（玩未完成！！！）
                    startActivity( intent );

                }
                Toast.makeText( getContext(), "已为您定位到:" + adapter.getItem( arg2 ), Toast.LENGTH_SHORT ).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                //myTextView.setText("Nothing");
                Toast.makeText( getContext(), "Nothing:", Toast.LENGTH_SHORT ).show();
            }
        } );

        starts = (ImageView) getActivity().findViewById( R.id.images_stars );
        starts.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText( getContext(), "小星星很可爱哦！！", Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( getActivity(), CitycalssActivity.class ); //跳转到特别展示页面
                startActivity( intent );
            }
        } );
        // 搜索框事件
        home_search_starsss = (SearchView) getActivity().findViewById( R.id.home_search_starsss );

        home_search_starsss.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            //输入完成后，提交时触发的方法，一般情况是点击输入法中的搜索按钮才会触发，表示现在正式提交了
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty( query )) {
                    Toast.makeText( getContext(), "请输入查找内容！", Toast.LENGTH_SHORT ).show();
                }
                return true;
            }//在输入时触发的方法，当字符真正显示到searchView中才触发，像是拼音，在输入法组词的时候不会触发

            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty( newText )) {
                    Toast.makeText( getContext(), "请输入查找内容！", Toast.LENGTH_SHORT ).show();
                }
                return true;
            }
        } );
    }
    // 获 取 控 件
    private void intivew() {
        frameLayout = (FrameLayout)getActivity().findViewById(R.id.frame_layout);
        radioGroup = (RadioGroup)getActivity().findViewById(R.id.rg_menu_bar);
        radioGroup.setOnCheckedChangeListener( this );
        newest=(RadioButton)getActivity().findViewById( R.id.rb_newest);
        newest.setOnClickListener( this );
        hottest=(RadioButton)getActivity().findViewById( R.id.rb_hottest);
        hottest.setOnClickListener( this );
        mospopular=(RadioButton)getActivity().findViewById( R.id.rb_mospopular);
        mospopular.setOnClickListener( this );
        //******************************默认第一个选中Fragment
        hottest = (RadioButton) radioGroup.getChildAt(0);
        hottest.setChecked(true);

        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    private void setSupportActionBar(Toolbar toolbar) {



    }

    //初始化第一个页面
    private void initFragment() {
        //获取管理器
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        newestFragment =new NewestFragment();
        transaction.add(R.id.frame_layout,newestFragment,null);
        transaction.commit();
    }

    //点击rb 按钮 改变页面
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i){
        switch (i){
            case R.id.rb_newest:
                FragmentTransaction ft1=manager.beginTransaction();
                hideAll(ft1);
                if(newestFragment==null){
                    newestFragment = new NewestFragment();
                    ft1.add(R.id.frame_layout,newestFragment);
                }else{
                    ft1.show(newestFragment);
                }
                ft1.commit();
                break;
            case R.id.rb_hottest:
                FragmentTransaction ft2=manager.beginTransaction();
                hideAll(ft2);
                if(hottestFragment==null){
                    hottestFragment = new HottestFragment();
                    ft2.add(R.id.frame_layout,hottestFragment);
                }else{
                    ft2.show(hottestFragment);
                }
                ft2.commit();
                break;
            case  R.id.rb_mospopular:
               FragmentTransaction ft3=manager.beginTransaction();
                hideAll(ft3);
                if(mostpopularFragment==null){
                    mostpopularFragment = new MostpopularFragment();
                    ft3.add(R.id.frame_layout,mostpopularFragment);
                }else{
                    ft3.show(mostpopularFragment);
                }
                ft3.commit();
                break;
        }
    }
    private void hideAll(FragmentTransaction ft) {
        if (ft == null) {
            return;
        }
        if (newestFragment != null) {
            ft.hide(newestFragment);
        }
        if (hottestFragment != null) {
            ft.hide(hottestFragment);
        }
        if (mostpopularFragment != null) {
            ft.hide(mostpopularFragment);
        }
    }
    // 主页 界面 完成
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment_ac, container, false);
        return view;
    }
    @Override
    public void onClick(View view) {

    }
}

