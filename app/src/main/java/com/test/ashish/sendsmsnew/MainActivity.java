package com.test.ashish.sendsmsnew;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new MessageFragment());

        BottomNavigationView navigation = findViewById(R.id.activity_sales_bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Bundle bundle = new Bundle();

                switch (item.getItemId()) {
                    case R.id.nav_visits:
                        fragment = new ContactFragment();
                        break;

                    case R.id.nav_followup:
                        fragment = new MessageFragment();
                        break;

                    default:
                        break;
                }

                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_sales_frame_layout, fragment)
                    .commit();

            return true;
        }
        return false;
    }
}