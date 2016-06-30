package com.cc.test;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.cc.base.aop.DebugTrace;
import com.cc.test.image.CompressorTest;
import com.cc.test.log.CCLogTest;

import java.util.ArrayList;

/**
 * Created by C&C on 2016/6/23.
 */
@DebugTrace
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                MainModel model = list.get(i);
                startActivity(model.getIntent());
            }
        });
    }
    private void addModel()
    {
        if (list==null)
        {
            list=new ArrayList<>();
        }
        list.add(MainModel.getNewInstance(this, CCLogTest.class));
        list.add(MainModel.getNewInstance(this, CompressorTest.class));
    }
}
