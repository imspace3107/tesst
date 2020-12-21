package com.example.foody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foody.adapter.screen_home.CustomGridAdapter;
import com.example.foody.model.screen_home.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private ViewPager nViewPager;
    String urlGetData ="http://192.168.1.5/foody/API.php";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);
        nViewPager = findViewById(R.id.ViewPager_container);
        GetData(urlGetData);

        setUpviewpager();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        nViewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_note:
                        nViewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_list:
                        nViewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_notifications:
                        nViewPager.setCurrentItem(3);
                        break;
                    case R.id.navigation_profile:
                        nViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }
    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                );
        requestQueue.add(jsonArrayRequest);
    }
    private void setUpviewpager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        nViewPager.setAdapter(viewPagerAdapter);
        nViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNav.getMenu().findItem(R.id.navigation_home).setChecked(true);
                        break;
                    case 1:
                        bottomNav.getMenu().findItem(R.id.navigation_note).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.navigation_list).setChecked(true);
                        break;
                    case 3:
                        bottomNav.getMenu().findItem(R.id.navigation_notifications).setChecked(true);
                        break;
                    case 4:
                        bottomNav.getMenu().findItem(R.id.navigation_profile).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}