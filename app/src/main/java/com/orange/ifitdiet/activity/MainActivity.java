package com.orange.ifitdiet.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

        View view=getLayoutInflater().inflate(R.layout.nav_header_main,null).findViewById(R.id.iv_navi);
        ImageView iv_navi= (ImageView) view.findViewById(R.id.iv_navi);
        iv_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击了_________________________________________________________");
                if (!isLogin) {
                    startActivity(new Intent().setClass(MainActivity.this, LoginActivity.class));
                }
            }
        });

        ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> fragList = new ArrayList<Fragment>();
        fragList.add(new RecommendFragment());
        fragList.add(new HealthFragment());
        fragList.add(new LocateFragment());
        fragList.add(new GroupFragment());

        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), fragList);
        if (vp != null) {
            vp.setAdapter(fragAdapter);
            vp.setCurrentItem(0);
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
}
