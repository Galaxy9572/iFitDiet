package com.orange.ifitdiet.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.common.FragAdapter;
import com.orange.ifitdiet.common.ListPool;
import com.orange.ifitdiet.common.StepService;
import com.orange.ifitdiet.common.UtilPool;
import com.orange.ifitdiet.fragment.GroupFragment;
import com.orange.ifitdiet.fragment.HealthFragment;
import com.orange.ifitdiet.fragment.LocateFragment;
import com.orange.ifitdiet.fragment.RecommendFragment;
import com.orange.ifitdiet.util.DBUtil;
import com.orange.ifitdiet.util.DisplayUtil;
import com.orange.ifitdiet.util.LocateUtil;
import com.orange.ifitdiet.util.NetUtil;
import com.orange.ifitdiet.util.QRCodeUtil;
import com.orange.ifitdiet.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static Boolean isExit = false;
    //Tab显示内容TextView
    private TextView tv_tab_recommend, tv_tab_health, tv_tab_locate, tv_tab_group;
    private ViewPager vp;
    private static BeanPool beanPool = new BeanPool();//存放JavaBean的池
    private static UtilPool utilPool = new UtilPool();//存放工具类的池
    private static ListPool listPool = new ListPool();
    private LocateUtil locateUtil;//定位工具类
    private DBUtil dbUtil;//数据库工具类
    private DisplayUtil displayUtil;//显示设置工具类
    private NetUtil netUtil;//网络通信工具类
    private TimeUtil timeUtil;//时间工具类
    private QRCodeUtil qrCodeUtil;//二维码生成工具类


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!NetUtil.checkNetworkAvailable(this)){
            //网络不可用
            this.setNetworkMethod(this);
        }
        //判断手机是否支持蓝牙4.0
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "你的手机不支持低功耗蓝牙，功能受到限制", Toast.LENGTH_SHORT).show();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);//添加Toolbar
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle("iFitDiet");//在toolbar里设置标题
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initComponents();//初始化一些按钮、TextView等组件
        initUtilsAndPools();//初始化工具类实例
        locateUtil.locate(getApplicationContext());
        initFragments();//初始化fragments
        initDatabase();//初始化数据库
        Intent intent = new Intent(this, StepService.class);//启动计步器服务
        startService(intent);

    }

    /**
     * 初始化工具类和BeanPool并添加到Pool中
     */
    private void initUtilsAndPools() {
        locateUtil = new LocateUtil(this);
        dbUtil = new DBUtil(getApplicationContext());
        displayUtil = new DisplayUtil(getApplicationContext());
        netUtil = new NetUtil(getApplicationContext());
        timeUtil = new TimeUtil();
        qrCodeUtil = new QRCodeUtil();
        utilPool.getUtilMap().put("locateUtil", locateUtil);
        utilPool.getUtilMap().put("dbUtil", dbUtil);
        utilPool.getUtilMap().put("displayUtil", displayUtil);
        utilPool.getUtilMap().put("netUtil", netUtil);
        utilPool.getUtilMap().put("timeUtil", timeUtil);
        utilPool.getUtilMap().put("qrCodeUtil", qrCodeUtil);
    }


    /**
     * 初始化数据库
     */
    private void initDatabase() {
        dbUtil.createNewDB("health_data.db");
    }

    /**
     * 初始化MainActivity的组件
     */
    private void initComponents() {
        View v_1 = getLayoutInflater().inflate(R.layout.activity_main_top_tab, null);
        tv_tab_recommend = (TextView) v_1.findViewById(R.id.tv_tab_recommend);//MainActivity顶部的推荐tab
        tv_tab_health = (TextView) v_1.findViewById(R.id.tv_tab_health);//MainActivity顶部的健康tab
        tv_tab_locate = (TextView) v_1.findViewById(R.id.tv_tab_locate);//MainActivity顶部的附近tab
        tv_tab_group = (TextView) v_1.findViewById(R.id.tv_tab_group);//MainActivity顶部的群组tab
        tv_tab_recommend.setOnClickListener(new TabOnClickListener(0));
        tv_tab_health.setOnClickListener(new TabOnClickListener(1));
        tv_tab_locate.setOnClickListener(new TabOnClickListener(2));
        tv_tab_group.setOnClickListener(new TabOnClickListener(3));

        View v_2 = getLayoutInflater().inflate(R.layout.nav_header_main, null);
/*       Button bt_register = (Button) v_2.findViewById(R.id.bt_register);//MainActivity左滑导航栏中的注册按钮
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(MainActivity.this, RegisterActivity.class));
            }
        });
        Button bt_login = (Button) v_2.findViewById(R.id.bt_login);//MainActivity左滑导航栏中的登录按钮
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(MainActivity.this, LoginActivity.class));
            }
        });*/


    }


    /**
     * 初始化推荐、健康、附近、群组四个Fragment
     */
    private void initFragments() {
        vp = (ViewPager) findViewById(R.id.viewpager);//找到ViewPager
        List<Fragment> fragList = new ArrayList<>();
        //将四个fragment添加到界面
        fragList.add(new RecommendFragment());
        fragList.add(new HealthFragment());
        fragList.add(new LocateFragment());
        fragList.add(new GroupFragment());
        vp.setCurrentItem(0);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), fragList);
        vp.setAdapter(fragAdapter);//给ViewPager设置Adapter

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        tv_tab_recommend.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        tv_tab_health.setTextColor(Color.WHITE);
                        setTheme(R.style.HealthThem);
                        break;
                    case 2:
                        tv_tab_locate.setTextColor(Color.WHITE);
                        break;
                    case 3:
                        tv_tab_group.setTextColor(Color.WHITE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main_activity, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 重新设置tab上的四个标签的字体颜色
     */
    private void resetTextView() {
        tv_tab_recommend.setTextColor(Color.WHITE);
        tv_tab_locate.setTextColor(Color.WHITE);
        tv_tab_health.setTextColor(Color.WHITE);
        tv_tab_group.setTextColor(Color.WHITE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        startActivity(new Intent().setClass(MainActivity.this, ShareActivity.class));
        return super.onOptionsItemSelected(item);
    }
/*
    *//**
     * 注册一个新的用户
     *
     * @param v
     *//*
    public void iv_register(View v) {
        startActivity(new Intent().setClass(MainActivity.this, RegisterActivity.class));
    }

    *//**
     * 用户登录
     *
     * @param
     *//*
    public void iv_login(View v) {
        startActivity(new Intent().setClass(MainActivity.this, LoginActivity.class));
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mymsg) {//我的消息按钮事件
            startActivity(new Intent().setClass(MainActivity.this, MyMsgActivity.class));
        } else if (id == R.id.nav_personal_info) {//个人信息按钮事件
            if (beanPool.getBeanMap().get("user") == null) {
                startActivity(new Intent().setClass(MainActivity.this, LoginActivity.class));
                Toast.makeText(this, "请先登录哟", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent().setClass(MainActivity.this, PersonalInfoActivity.class));
            }
        } else if (id == R.id.nav_share) {//分享按钮事件
            startActivity(new Intent().setClass(MainActivity.this, ShareActivity.class));
        } else if (id == R.id.nav_comm) {
            startActivity(new Intent().setClass(this, CommActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent().setClass(this, AboutActivity.class));
        } else if (id == R.id.nav_exit) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("退出程序").setMessage("是否退出程序")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    }).setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            }).create(); // 创建对话框
            alertDialog.show(); // 显示对话框
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class TabOnClickListener implements View.OnClickListener {
        private int index;

        public TabOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            vp.setCurrentItem(index, true);//选择某一页
        }

    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    public static BeanPool getBeanPool() {
        return beanPool;
    }

    public static UtilPool getUtilPool() {
        return utilPool;
    }

    public static ListPool getListPool() {
        return listPool;
    }

    public void iv_breakfast(View v) {
        startActivity(new Intent().setClass(MainActivity.this, BreakfastActivity.class));
    }

    public void iv_lunch(View v) {
        startActivity(new Intent().setClass(MainActivity.this, LunchActivity.class));
    }

    public void iv_supper(View v) {
        startActivity(new Intent().setClass(MainActivity.this, SupperActivity.class));
    }

    public void iv_fruit(View v) {
        startActivity(new Intent().setClass(MainActivity.this, FruitActivity.class));
    }

    public void iv_week(View view) {
        startActivity(new Intent().setClass(MainActivity.this, WeekActivity.class));
    }
    public static void setNetworkMethod(final Context context){
        //提示对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if(android.os.Build.VERSION.SDK_INT>10){
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
