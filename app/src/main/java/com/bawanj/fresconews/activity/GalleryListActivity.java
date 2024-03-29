package com.bawanj.fresconews.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bawanj.fresconews.R;
import com.bawanj.fresconews.fragment.GalleryListFragment;

public class GalleryListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_fragment);

        FragmentManager fm= getSupportFragmentManager();
        Fragment fragment= fm.findFragmentById(R.id.fragment_container_list);

        if(fragment==null){
            fragment= GalleryListFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.fragment_container_list, fragment)
                    .commit();

        }

    }


}







//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_gallery_detail, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

