package com.bawanj.fresconews.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bawanj.fresconews.R;
import com.bawanj.fresconews.activity.GalleryPagerActivity;
import com.bawanj.fresconews.model.GalleryItem;
import com.bawanj.fresconews.utils.VolleyHelper;

import java.util.ArrayList;

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.GalleryHolder> {

    private final String TAG = "GalleryListAdapter";

    private ImageLoader mImageLoader;
    private ArrayList<GalleryItem> mGalleryItems;
    private Context mContext;

    // try
    //private GalleryListFragment.Callbacks mCallback;

    public GalleryListAdapter(ArrayList<GalleryItem> galleryItems){ //, GalleryListFragment.Callbacks callback ){
    // constructor
        mGalleryItems = galleryItems;
        mImageLoader= VolleyHelper.getInstance().getImageLoader();
        //mCallback= callback;
    }

    public final class GalleryHolder extends RecyclerView.ViewHolder{
        // 1. reference 2. set input information
        private CardView mCardView;
        private NetworkImageView mImageView;
        private TextView mTextView;
        private GalleryItem mGalleryItem;

        public GalleryHolder( View itemView ) {
            super(itemView);
            mCardView= (CardView)itemView.findViewById(R.id.cardView);
            mTextView= (TextView) itemView.findViewById(R.id.item_name);
            mImageView= (NetworkImageView)itemView.findViewById(R.id.item_avatar );
        }

        public void bindGalleryItem(GalleryItem galleryItem ){
            mGalleryItem= galleryItem;

            String userName= mGalleryItem.getFirstName()+" "+mGalleryItem.getLastName();
            mTextView.setText( userName );
            mImageView.setImageUrl( mGalleryItem.getAvatarUrl(), mImageLoader);
        }
    }

    @Override
    public int getItemCount() {
        return mGalleryItems.size();
    }


    @Override
    public GalleryHolder onCreateViewHolder( ViewGroup viewGroup, int viewType ) {

            mContext= viewGroup.getContext();

            View itemView = LayoutInflater.from( mContext )
                    .inflate(R.layout.item_recycler_view_card, viewGroup, false);

            return new GalleryHolder( itemView );
    }

    @Override
    public void onBindViewHolder( GalleryHolder galleryHolder, final int position ) {

        final GalleryItem item= mGalleryItems.get(position);
        galleryHolder.bindGalleryItem(item);

        galleryHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //case 1: easy -> use Intent pass GalleryItem to GalleryPagerActivity,
                // then GalleryFragment can retrieve the object
                Intent intent= GalleryPagerActivity.newIntent(mContext, position , mGalleryItems );

                mContext.startActivity(intent);
                //case 2: a call back
                //mCallback.onItemSelected( item );
            }
        });

    }




}
