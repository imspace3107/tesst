package com.example.foody.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.foody.MainActivity;
import com.example.foody.View.view_screen_ship.screen_ship;
import com.example.foody.View.View_screen_discover.discover_food;
import com.example.foody.View.View_screen_order.screen_order;
import com.example.foody.adapter.screen_home.CustomGridAdapter;
import com.example.foody.adapter.screen_home.adapter;
import com.example.foody.model.screen_home.Product;
import com.example.foody.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView_listFood;
    CustomGridAdapter customGridAdapterex_;
    List<Product> ProductList;
    adapter proAdapter;
    String urlGetData ="http://192.168.1.5/foody/API.php";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        //onclick
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent=new Intent(getActivity(),screen_detail_food.class);
//                intent.putExtra("name",product_.getProductName());
//                System.out.println(product_.getFlagName());
//                intent.putExtra("name",product_.getFlagName());
//                startActivity(intent);
//            }
//        });


        //slide
        ImageSlider imageSlider=root.findViewById(R.id.home_image_slider);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slide1));
        slideModels.add(new SlideModel(R.drawable.slide2));
        slideModels.add(new SlideModel(R.drawable.slide3));
        imageSlider.setImageList(slideModels,true);

        //active
        root.findViewById(R.id.giaohang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), screen_ship.class);
                startActivity(intent);
            }
        });



        root.findViewById(R.id.order_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), screen_order.class);
                startActivity(intent);
            }
        });


        root.findViewById(R.id.btn_khampha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), discover_food.class);
                startActivity(intent);
            }
        });

        ProductList = new ArrayList<>();
        ProductList.add(new Product("Quán Phở Bình An","https://images.foody.vn/res/g92/914932/prof/s1242x600/foody-upload-api-foody-mobile-rin-191111142628.jpg","163 Hà Huy Tập, Quận Thanh Khê, Đà Nẵng","28.000 đ"));
        ProductList.add(new Product("Dana Buffet","https://images.foody.vn/res/g106/1052041/prof/s1242x600/foody-upload-api-foody-mobile-12-201026151207.jpg","56 Nguyễn Chí Thanh, Quận Hải Châu, Đà Nẵng","369.000 đ"));
        ProductList.add(new Product("My Thái Restaurant - Ẩm Thực Thái","https://images.foody.vn/res/g66/658216/prof/s1242x600/foody-mobile-myth-jpg-641-636332292467972129.jpg","389 Trần Hưng Đạo, Quận Sơn Trà, Đà Nẵng","200,000 đ"));
        ProductList.add(new Product("Son Tra Retreat - Garden Lounge & Eatery","https://images.foody.vn/res/g93/926219/prof/s1242x600/foody-upload-api-foody-mobile-f866665e2c089eff6054-200514095150.jpg","11 Lê Văn Lương, P. Thọ Quang, Quận Sơn Trà","500.000 đ"));
        recyclerView_listFood = root.findViewById(R.id.gridView_home);
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        recyclerView_listFood.setLayoutManager(staggeredGridLayoutManager);
        customGridAdapterex_ = new CustomGridAdapter(getActivity(),ProductList);

        recyclerView_listFood.setAdapter(customGridAdapterex_);
        recyclerView_listFood.setAdapter(customGridAdapterex_);
        return root;
    }
    private void GetData(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(null);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){

                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        ProductList.add(new Product(
                                        object.getString("name"),
                                        object.getString("imageUrl"),
                                        object.getString("price"),
                                        object.getString("address")
                                )
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                customGridAdapterex_.notifyDataSetChanged();

            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }



}