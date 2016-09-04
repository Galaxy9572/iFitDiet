package com.orange.ifitdiet.activity;

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
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.fragment.FragAdapter;
import com.orange.ifitdiet.fragment.GroupFragment;
import com.orange.ifitdiet.fragment.HealthFragment;
import com.orange.ifitdiet.fragment.LocateFragment;
import com.orange.ifitdiet.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment_recommend, fragment_health, fragment_locate, fragment_group;
    private boolean isRegister, isLogin;
    //Tab显示内容TextView
    private TextView tv_tab_recommend, tv_tab_health, tv_tab_locate, tv_tab_group;
    //Tab的那个引导线
    private ImageView iv_tabline;
    //屏幕的宽度
    private int screenWidth;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "你的手机不支持低功耗蓝牙，功能受到限制", Toast.LENGTH_SHORT).show();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle("iFitDiet");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v = getLayoutInflater().inflate(R.layout.activity_main_top_tab, null);
        iv_tabline = (ImageView) v.findViewById(R.id.id_tab_line_iv);
        tv_tab_health = (TextView) v.findViewById(R.id.id_locate_tv);
        tv_tab_recommend = (TextView) v.findViewById(R.id.id_recommend_tv);
        tv_tab_locate = (TextView) v.findViewById(R.id.id_health_tv);
        tv_tab_group = (TextView) v.findViewById(R.id.id_group_tv);

        tv_tab_recommend.setOnClickListener(new TabOnClickListener(0));
        tv_tab_health.setOnClickListener(new TabOnClickListener(1));
        tv_tab_locate.setOnClickListener(new TabOnClickListener(2));
        tv_tab_group.setOnClickListener(new TabOnClickListener(3));
        regOrLogin();
        initFragments();
        initTabLineWidth();
    }

    private void regOrLogin() {
        View view = getLayoutInflater().inflate(R.layout.nav_header_main, null).findViewById(R.id.iv_navi);
        ImageView iv_navi = (ImageView) view.findViewById(R.id.iv_navi);
        iv_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击了_________________________________________________________");
                if (!isLogin) {
                    startActivity(new Intent().setClass(MainActivity.this, LoginActivity.class));
                }
            }
        });
    }


    private void initFragments() {
        vp = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> fragList = new ArrayList<Fragment>();
        fragList.add(new RecommendFragment());
        fragList.add(new HealthFragment());
        fragList.add(new LocateFragment());
        fragList.add(new GroupFragment());
        vp.setCurrentItem(0);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), fragList);
        if (vp != null) {
            vp.setAdapter(fragAdapter);
            vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_tabline
                            .getLayoutParams();
                    //返回组件距离左侧组件的距离
                    lp.leftMargin = (int) ((positionOffset + position) * screenWidth / 4);
                    iv_tabline.setLayoutParams(lp);
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

    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_tabline.getLayoutParams();
        lp.width = screenWidth / 4;
        iv_tabline.setLayoutParams(lp);
    }

    private void resetTextView() {
        tv_tab_recommend.setTextColor(Color.BLACK);
        tv_tab_locate.setTextColor(Color.BLACK);
        tv_tab_health.setTextColor(Color.BLACK);
        tv_tab_group.setTextColor(Color.BLACK);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    protected void register(View v) {
        startActivity(new Intent().setClass(this, RegisterActivity.class));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mymsg) {
            startActivity(new Intent().setClass(MainActivity.this, RegisterActivity.class));
        } else if (id == R.id.nav_myinfo) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_social) {

        } else if (id == R.id.nav_about) {
            startActivity(new Intent().setClass(this, AboutActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent().setClass(this, SettingActivity.class));
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
        private int index = 0;

        public TabOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            vp.setCurrentItem(index);//选择某一页
        }

    }
}