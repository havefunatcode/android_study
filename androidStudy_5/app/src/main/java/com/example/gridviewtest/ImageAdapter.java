package com.example.gridviewtest;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private Integer[] mThumbIds = {
            R.drawable.movie1, R.drawable.movie2,
            R.drawable.movie2, R.drawable.movie4,
            R.drawable.movie3, R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie1, R.drawable.movie2,
            R.drawable.movie2, R.drawable.movie4,
            R.drawable.movie3, R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie1, R.drawable.movie2,
            R.drawable.movie2, R.drawable.movie4,
            R.drawable.movie3, R.drawable.movie6,
            R.drawable.movie7
    };

    public ImageAdapter(Context c)  {
        mContext = c;
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else  {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
