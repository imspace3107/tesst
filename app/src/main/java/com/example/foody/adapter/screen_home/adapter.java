package com.example.foody.adapter.screen_home;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foody.R;
import com.example.foody.model.screen_home.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<Product> foodList;
    Product food;

    public adapter(Context context, int layout, List<Product> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView name,price,address;
        ImageView imageUrl;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.name  = (TextView)view.findViewById(R.id.nameProduct);
            holder.price  = (TextView)view.findViewById(R.id.price);
            holder.imageUrl = (ImageView)view.findViewById(R.id.img);
            holder.address=(TextView) view.findViewById(R.id.address);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }

        food = foodList.get(position);
        holder.name.setText(food.getName());
        Picasso.get().load(food.getImageUrl())

                .into(holder.imageUrl);


        return view;
    }
}