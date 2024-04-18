package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.ListFragment;
import com.example.myapplication.fragment.MineFragment;
import com.example.myapplication.fragment.TimeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FunctionActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private TimeFragment timeFragment;
    private MineFragment mineFragment;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_function);

        //初始化控件
        bottomNavigationView = findViewById(R.id.bottomnavigationView);
        //bottomNavigationView设置点击事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    selectedFragment(0);
                } else if (menuItem.getItemId() == R.id.list) {
                    selectedFragment(1);
                } else if (menuItem.getItemId() == R.id.time) {
                    selectedFragment(2);
                } else {
                    selectedFragment(3);
                }
                return true;
            }
        });

        //默认选中首页
        selectedFragment(0);

    }

    private void selectedFragment(int position) {
        androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);

        if (position == 0) {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.content,homeFragment);
            } else {
                fragmentTransaction.show(homeFragment);
            }
        } else if (position == 1) {
            if (listFragment == null) {
                listFragment = new ListFragment();
                fragmentTransaction.add(R.id.content,listFragment);
            } else {
                fragmentTransaction.show(listFragment);
            }
        } else if (position == 2) {
            if (timeFragment == null) {
                timeFragment = new TimeFragment();
                fragmentTransaction.add(R.id.content,timeFragment);
            } else {
                fragmentTransaction.show(timeFragment);
            }
        } else {
            if (mineFragment == null) {
                mineFragment = new MineFragment();
                fragmentTransaction.add(R.id.content,mineFragment);
            } else {
                fragmentTransaction.show(mineFragment);
            }
        }

        //提交
        fragmentTransaction.commit();

    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {

        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }

        if (listFragment != null) {
            fragmentTransaction.hide(listFragment);
        }

        if (timeFragment != null) {
            fragmentTransaction.hide(timeFragment);
        }

        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }
}