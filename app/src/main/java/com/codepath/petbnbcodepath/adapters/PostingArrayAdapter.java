package com.codepath.petbnbcodepath.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.petbnbcodepath.R;
import com.codepath.petbnbcodepath.activities.DetailsPageActivity;
import com.codepath.petbnbcodepath.models.Listing;
import com.codepath.petbnbcodepath.viewpagers.WrapContentHeightViewPager;
import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by gangwal on 3/8/15.
 */
public class PostingArrayAdapter extends ArrayAdapter<Listing> {

    private class ViewHolder{
        ImageView ivSitterImage;
        ImageView ivWishlist;
        ImageView ivUserImage;
        TextView tvPostTitle;
        TextView tvPostSubTitle;
    }

    private FragmentManager mFragmentManger;
    Activity mActivity;
    Listing listing;
    WrapContentHeightViewPager viewPager;
    //TODO Need to check if making viewholder final effect the performance
    ImageView ivWishlist;


    public PostingArrayAdapter(FragmentActivity activity, List<Listing> listings) {
        super(activity, R.layout.item_post, listings);
        mActivity = activity;
        mFragmentManger = activity.getSupportFragmentManager();    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Listing currentListing = getItem(position);
        listing = getItem(position);
        final ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
            convertView.setTag(viewHolder);

            viewPager = (WrapContentHeightViewPager) convertView.findViewById(R.id.view_pager);
            viewHolder.ivWishlist = (ImageView)convertView.findViewById(R.id.ivWishlist);
            viewHolder.ivSitterImage = (ImageView)convertView.findViewById(R.id.ivSitterImage);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivSitterImage.setImageResource(0);
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(mActivity.getResources().getColor(R.color.white))
                .borderWidthDp(1)
                .cornerRadiusDp(25)
                .oval(false)
                .build();

        Picasso.with(getContext())
                .load(R.drawable.sample_profile)
                .fit()
                .transform(transformation)
                .into(viewHolder.ivSitterImage);

        final Animation anim = AnimationUtils.loadAnimation(mActivity, R.anim.scale);
        ivWishlist = viewHolder.ivWishlist;
        viewHolder.ivWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.ivWishlist.startAnimation(anim);
                viewHolder.ivWishlist.setImageResource(R.drawable.wishlist_heart_selected);
            }
        });
        viewHolder.ivSitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailsPageActivity.class);
                getContext().startActivity(intent);
            }
        });
        ImagePagerAdapter adapter = new ImagePagerAdapter(mActivity);
        viewPager.setAdapter(adapter);
        return convertView;
    }
}
