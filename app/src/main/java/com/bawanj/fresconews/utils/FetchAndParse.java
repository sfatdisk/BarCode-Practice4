package com.bawanj.fresconews.utils;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bawanj.fresconews.adapter.GalleryListAdapter;
import com.bawanj.fresconews.model.GalleryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchAndParse {

    private static final String TAG= "FetchAndParse";

    private ArrayList<GalleryItem> mGalleryItems;

    public FetchAndParse(ArrayList<GalleryItem> galleryItems){
        mGalleryItems = galleryItems;
    }


    public void fetchJsonObject( final GalleryListAdapter adapter , String url ){

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "JSONObject is  " + response );

                        try {
                            parseJSONObject( adapter, response );

                        } catch (JSONException jse) {
                            Log.d(TAG, "Unable to fetch JsonObject: " + jse.getMessage());
                            jse.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d (TAG, error.getMessage(),error);
                        error.printStackTrace();
                    }
                });

        VolleyHelper.getInstance().getRequestQueue().add(jsObjRequest);
    }

    public void parseJSONObject( GalleryListAdapter adapter , JSONObject response )throws JSONException {

        JSONObject data = response.getJSONObject("data");
        JSONArray posts = data.getJSONArray("posts");

        mGalleryItems.clear(); //  clear the list first

        for(int i=0;i<posts.length();++i){

            JSONObject post = posts.getJSONObject(i);

            String header  = post.getString("image");
            String time   = post.getString("time_created");
            String caption= post.getString("caption");

            JSONObject owner = post.getJSONObject("owner");
            String firstName= owner.getString("firstname");
            String lastName= owner.getString("lastname");
            String avatar= owner.getString("avatar");

            JSONObject location= post.getJSONObject("location");
            String address= location.getString("address");

            GalleryItem item= new GalleryItem( firstName,lastName, avatar, address, time, header, caption );

            mGalleryItems.add(item);
            Log.i(TAG, header+" "+time+" "+caption+" "+firstName+" "+lastName+" "+avatar+" "+ address);
        }

        Log.i(TAG, "mGalleryItems size= "+mGalleryItems.size() );
        // notify the data updated
        adapter.notifyDataSetChanged();

    }

    public void fetchJsonArray( final GalleryListAdapter adapter ){  // a thread


        final String url= "https://api.dribbble.com/v1/shots?sort=recent&access_token=d40cff3dab6677286b8fb77fba2232eab7e60e76c591eb78043e1c65be564c8e";

        JsonArrayRequest jsArrayRequest= new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            Log.d( TAG, "JSONArray is  "+response );
                            parseJSONArray( adapter, response );

                        }catch(JSONException jse){
                            Log.d(TAG, "Unable to fetch JsonArray: " + jse.getMessage());
                            jse.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d (TAG, error.getMessage(),error);
                        error.printStackTrace();
                    }
                }
        );

        VolleyHelper.getInstance().getRequestQueue().add(jsArrayRequest);

    }

    private void parseJSONArray( GalleryListAdapter adapter, JSONArray jsonArray )throws JSONException {

        mGalleryItems.clear(); //  clear the list first

        for(int i=0;i<jsonArray.length();++i){

            JSONObject post = jsonArray.getJSONObject(i);
            String header  = post.getString("image");
            String time   = post.getString("time_created");
            String caption= post.getString("caption");

            JSONObject owner = post.getJSONObject("owner");
                String firstName= owner.getString("firstname");
                String lastName= owner.getString("lastname");
                String avatar= owner.getString("avatar");

            JSONObject location= post.getJSONObject("location");
                    String address= location.getString("address");

            GalleryItem item= new GalleryItem( firstName,lastName, avatar, address, time, header, caption );

            mGalleryItems.add(item);
            Log.i(TAG, header+" "+time+" "+caption+" "+firstName+" "+lastName+" "+avatar+" "+ address);
        }

        Log.i(TAG, "mGalleryItems size= "+mGalleryItems.size() );
        // notify the data updated
        adapter.notifyDataSetChanged();
    }
}
