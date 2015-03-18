package com.codepath.petbnbcodepath.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codepath.petbnbcodepath.R;


/**
 * Created by gangwal on 3/9/15.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private int[] mImages = new int[] {
            R.drawable.pet_sitter1,
            R.drawable.pet_sitter2,
            R.drawable.pet_sitter3,
            R.drawable.pet_sitter4,
            R.drawable.pett_sitter5,
    };

    public ImagePagerAdapter(Context context){
        this.context = context;

    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        Context context = getActivity();
        ImageView imageView = new ImageView(context);
//            int padding = context.getResources().getDimensionPixelSize(
//                    R.dimen.padding_medium);
//            imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setAdjustViewBounds(true);
        imageView.setImageResource(mImages[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
