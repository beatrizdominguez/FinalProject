package com.closet.beatriz.closet;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);


        // Add Drawer Item to dataList
        dataList.add(new DrawerItem(getString(R.string.title_section1), R.drawable.add_ic));
        dataList.add(new DrawerItem(getString(R.string.title_section2), R.drawable.add_ic));
        dataList.add(new DrawerItem(getString(R.string.title_section3), R.drawable.add_ic));
        dataList.add(new DrawerItem(getString(R.string.title_section4), R.drawable.add_ic));
        dataList.add(new DrawerItem(getString(R.string.title_section5), R.drawable.add_ic));
        dataList.add(new DrawerItem(getString(R.string.title_section6), R.drawable.add_ic));
        dataList.add(new DrawerItem(getString(R.string.title_section7), R.drawable.add_ic));


        adapter = new CustomDrawerAdapter(MainActivity.this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }

    }


    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new FmItems();
                args.putString(FmItems.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FmItems.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 1:
                fragment = new FmOutfit();
                args.putString(FmOutfit.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FmOutfit.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 2:
                fragment = new FmCalendar();
                args.putString(FmCalendar.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FmCalendar.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 3:
                fragment = new FmMyClosets();
                args.putString(FmMyClosets.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FmMyClosets.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 4:
                fragment = new FmEstadisticas();
                args.putString(FmEstadisticas.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FmEstadisticas.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 5:
                fragment = new FmConfig();
                args.putString(FmConfig.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FmConfig.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 6:
                //change when fragment created
                fragment = new FmItems();
                args.putString(FmItems.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FmItems.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;

            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }
}
