package com.example.namxuan.thirdexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {


    private Context context;
    //PROCESS
    private ArrayList<JSONObject> imageList;
    public ImageAdapter(Context c, ArrayList<JSONObject> list){
        context = c;
        imageList = list;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(context);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        //imageView.setImageResource(imageList.get(position));
//        //PROCESS
//        //position urlList
//        Picasso.get().load(imageList.get(position)).into(imageView);
//        Picasso.get().
//        return imageView;

   // }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            // tao view moi
            view = new View(context);
            view = inflater.inflate(R.layout.gridlayout, null);
            TextView textViewAndroid = (TextView) view.findViewById(R.id.gridview_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.gridview_image);
            try {
                textViewAndroid.setText(imageList.get(position).getString("author"));
                Picasso.get().load(imageList.get(position).getString("photo")).into(imageView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //---------------------------------




        } else {
            view =  convertView;
        }

        //imageView.setImageResource(imageList.get(position));
        //PROCESS
        //position urlList
        return view;

   }
}
