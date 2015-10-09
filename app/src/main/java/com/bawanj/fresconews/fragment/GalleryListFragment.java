package com.bawanj.fresconews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawanj.fresconews.R;
import com.bawanj.fresconews.activity.GalleryListActivity;
import com.bawanj.fresconews.adapter.GalleryListAdapter;
import com.bawanj.fresconews.model.GalleryItem;
import com.bawanj.fresconews.utils.FetchAndParse;

import java.util.ArrayList;

public class GalleryListFragment extends Fragment {

    private static final String TAG= "NewsPrimaryFragment" ;
    private static final String URL= "http://staging.fresconews.com/v1/gallery/get?id=560d5c8a948f373a1fba8868";

    // UI
    private RecyclerView mRecyclerView;
    private GalleryListAdapter mGalleryListAdapter;
    // fetch data
    private ArrayList<GalleryItem> mGalleryItems;
    private FetchAndParse mFetchAndParse;

    public static GalleryListFragment newInstance(){
        return new GalleryListFragment();
    }


    @Override
    public void onCreate( Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ( (GalleryListActivity)getActivity() )
                .getSupportActionBar()
                .setTitle(R.string.fragment_gallery_list);

        // save variables
        setRetainInstance(true);
        // ----- keep data because of setRetainInstance(true) -----

        mGalleryItems = new ArrayList<>();

        mGalleryListAdapter= new GalleryListAdapter(mGalleryItems );

        mFetchAndParse= new FetchAndParse(mGalleryItems);

        mFetchAndParse.fetchJsonObject(mGalleryListAdapter, URL);  // load the list data
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){

        // Define the xml for the fragment
        View rootView= inflater.inflate(R.layout.fragment_gallery_list, container, false);

        // Define recyclerView
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity());

        mRecyclerView= (RecyclerView)rootView.findViewById(R.id.fragment_gallery_list_recycler_view);
        mRecyclerView.setHasFixedSize(true); // improve the performance
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mGalleryListAdapter);

        return rootView;
    }





}
