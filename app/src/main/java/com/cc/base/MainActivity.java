package com.cc.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.cc.base.log.CCLogTest;

import java.util.ArrayList;

/**
 * Created by C&C on 2016/6/23.
 */

public class MainActivity extends Activity
{
    private ListView listView;
    private ArrayList<MainModel> list;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        listView= (ListView) findViewById(R.id.listview);
        addModel();
        MainAdapter adapter=new MainAdapter(this,list);
        listView.setAdapter(adapter);
    }
    private void addModel()
    {
        if (list==null)
        {
            list=new ArrayList<>();
        }
        list.add(MainModel.getNewInstance(this, CCLogTest.class));
    }
}
