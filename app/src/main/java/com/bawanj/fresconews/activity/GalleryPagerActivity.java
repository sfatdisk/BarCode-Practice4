package com.bawanj.fresconews.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bawanj.fresconews.R;
import com.bawanj.fresconews.fragment.GalleryPagerFragment;
import com.bawanj.fresconews.model.GalleryItem;

import java.util.ArrayList;

public class GalleryPagerActivity extends AppCompatActivity {

    private static final String TAG= "GalleryPagerActivity";

    public static final String EXTRA_CURRENT_POSITION= "current_position";
    public static final String EXTRA_GALLERY_LIST= "gallery_item_list";

    private ViewPager mViewPager;
    private ArrayList<GalleryItem> mGalleryItems;

    private GalleryItem mGalleryItem;
    private int mCurPosition=0;

    public static Intent newIntent( Context from , int position, ArrayList<GalleryItem> galleryItems  ){
    // this Method is for GalleryListAdapter
        Intent intent= new Intent(from, GalleryPagerActivity.class );
        intent.putExtra( EXTRA_CURRENT_POSITION, position);
        intent.putExtra( EXTRA_GALLERY_LIST, galleryItems);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_pager);

        mViewPager=(ViewPager)findViewById(R.id.activity_gallery_view_pager);

        mGalleryItems= (ArrayList<GalleryItem>) getIntent()
                .getSerializableExtra( EXTRA_GALLERY_LIST );

        Log.d( TAG, "GalleryItems size= "+ mGalleryItems.size() );

        mCurPosition= getIntent().getIntExtra( EXTRA_CURRENT_POSITION, 0 );
        Log.d( TAG, "mCurPosition "+ mCurPosition );

        FragmentManager fm= getSupportFragmentManager();

        mViewPager.setAdapter( new FragmentStatePagerAdapter(fm) {

            @Override
            public Fragment getItem(int position) {

                mGalleryItem= mGalleryItems.get(position);
                return GalleryPagerFragment.newInstance(mGalleryItem);
            }

            @Override
            public int getCount() {
                return mGalleryItems.size() ;
            }
        });

        mViewPager.setCurrentItem(mCurPosition);


//        Fragment fragment= fm.findFragmentById(R.id.fragment_container_list);
//
//
//        if(fragment==null){
//            fragment= GalleryPagerFragment.newInstance(mGalleryItem);
//            fm.beginTransaction()
//                    .add(R.id.fragment_container_list, fragment)
//                    .commit();
//
//        }else{
//
//            fragment= GalleryPagerFragment.newInstance(mGalleryItem);
//            fm.beginTransaction()
//                    .replace(R.id.fragment_container_list, fragment)
//                    .commit();
//
//        }
    }




}
