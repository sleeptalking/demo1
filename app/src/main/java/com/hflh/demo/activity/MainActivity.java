package com.hflh.demo.activity;

import android.Manifest;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hflh.demo.Fragment.HomeFragment;
import com.hflh.demo.Fragment.MineFragment;
import com.hflh.demo.R;
import com.hflh.demo.base.BaseActivity;
import com.hflh.demo.util.PermissionUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private Fragment homeFragment, myFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setDefaultFragment();
        addBottomItem();
        requestPermissions();
    }


    private void requestPermissions(){
        String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.CAMERA};
        PermissionUtils.checkAndRequestMorePermissions(this, permissions, 101, new PermissionUtils.PermissionRequestSuccessCallBack() {
            @Override
            public void onHasPermission() {
                Toast.makeText(MainActivity.this,MainActivity.this.getResources().getString(R.string.perminssion),Toast.LENGTH_LONG).show();
            }
        });

    }



    /**
     * 设置BottomNavigationBar
     */
    private void addBottomItem() {

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.app_images_tabicon_home_page_selected, "首页").setInactiveIconResource(R.mipmap.app_images_tabicon_home_page))
                .addItem(new BottomNavigationItem(R.mipmap.app_images_tabicon_scan_selected, "扫一扫").setInactiveIconResource(R.mipmap.app_images_tabicon_scan))
                .addItem(new BottomNavigationItem(R.mipmap.app_images_tabicon_message_selected, "消息").setInactiveIconResource(R.mipmap.app_images_tabicon_message))
                .addItem(new BottomNavigationItem(R.mipmap.app_images_tabicon_mine_selected, "我的").setInactiveIconResource(R.mipmap.app_images_tabicon_mine))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setInActiveColor("#979EB0")  // 未选中状态颜色
                .setActiveColor("#32C8E3")    // 选中状态颜色
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setFirstSelectedPosition(0)
                .setBarBackgroundColor("#16192A").initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (position) {
                    case 0:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                        }
                        transaction.replace(R.id.fragment_container, homeFragment);
                        break;
                    case 1:
                        if (myFragment == null) {
                            myFragment = new MineFragment();
                        }
                        transaction.replace(R.id.fragment_container, myFragment);
                        break;
                    case 2:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();

                        }
                        transaction.replace(R.id.fragment_container, homeFragment);
                        break;
                    case 3:
                        if (myFragment == null) {
                            myFragment = new MineFragment();
                        }
                        transaction.replace(R.id.fragment_container, myFragment);
                        break;
                    default:
                        break;
                }
                // 事务提交
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * 设置默认选中的fragment
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.replace(R.id.fragment_container, homeFragment);
        transaction.commit();
    }


}
