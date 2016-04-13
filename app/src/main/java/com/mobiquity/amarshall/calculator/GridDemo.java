package com.mobiquity.amarshall.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.GridView;


public class GridDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_demo);

        GridView gridView = (GridView) findViewById(R.id.gird_layout);
//        gridView.setAdapter();


    }



}
