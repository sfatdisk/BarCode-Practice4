package com.bawanj.fresconews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bawanj.fresconews.R;
import com.bawanj.fresconews.activity.GalleryPagerActivity;
import com.bawanj.fresconews.model.GalleryItem;
import com.bawanj.fresconews.utils.VolleyHelper;
import com.bawanj.fresconews.view.CircularNetworkImageView;

public class GalleryPagerFragment extends Fragment {

    private static final String TAG= "GalleryPagerFragment";

    public static final String ARG_GALLERY_ITEM= "gallery_item";

    // UI
    private NetworkImageView mHeader ;
    private CircularNetworkImageView  mAvatar;

    private TextView mUserName, mAddress, mTime, mCaption;
    private ImageView mIconAddress, mIconTime;

    // fetch data
    private ImageLoader mImageLoader;
    private GalleryItem mGalleryItem;

    public static GalleryPagerFragment newInstance( GalleryItem item ){
        Bundle args= new Bundle();
        args.putSerializable(ARG_GALLERY_ITEM, item );

        GalleryPagerFragment fragment= new GalleryPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle  savedInstanceState ){
        super.onCreate(savedInstanceState);

        ( (GalleryPagerActivity)getActivity() )
                .getSupportActionBar()
                .hide();

        // loader
        mImageLoader= VolleyHelper.getInstance().getImageLoader();
        // save variables
        setRetainInstance(true);
        // retrieve data
        mGalleryItem= (GalleryItem) getArguments().getSerializable(ARG_GALLERY_ITEM);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){

        // Define the xml for the fragment
        View rootView= inflater.inflate(R.layout.fragment_gallery_detail, container, false);

        mHeader = (NetworkImageView) rootView.findViewById(R.id.fragment_detail_header);
//        mAvatar = (NetworkImageView) rootView.findViewById(R.id.fragment_detail_ic_avatar);
        mAvatar = (CircularNetworkImageView) rootView.findViewById(R.id.fragment_detail_ic_avatar);

        // NetworkImageView
        mHeader.setImageUrl( mGalleryItem.getHeaderUrl(), mImageLoader);
        mAvatar.setImageUrl( mGalleryItem.getAvatarUrl() , mImageLoader );

        // TextView
        mUserName = (TextView) rootView.findViewById(R.id.fragment_detail_username);
        String userName= mGalleryItem.getFirstName()+" "+mGalleryItem.getLastName();
        mUserName.setText( userName );

        mAddress  = (TextView) rootView.findViewById(R.id.fragment_detail_address);
        mAddress.setText( mGalleryItem.getAddress() );

        mTime     = (TextView) rootView.findViewById(R.id.fragment_detail_time);
        mTime.setText( mGalleryItem.getTime() );

        mCaption  = (TextView) rootView.findViewById(R.id.fragment_detail_caption);
        mCaption.setText( mGalleryItem.getCaption());


        mIconAddress = (ImageView) rootView.findViewById(R.id.fragment_detail_ic_address);
        mIconTime= (ImageView)rootView.findViewById(R.id.fragment_detail_ic_time);

        // ImageView
        //mIconAddress.setImageDrawable();
        //mIconTime.setImageDrawable();

        return rootView;
    }


}
