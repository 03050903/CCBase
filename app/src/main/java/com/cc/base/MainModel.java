package com.cc.base;

import android.content.Context;
import android.content.Intent;

/**
 * Created by C&C on 2016/6/23.
 */

public class MainModel
{
    private String name;
    private Intent intent;

    public String getName()
    {
        return name;
    }
    public Intent getIntent()
    {
        return intent;
    }
    public static MainModel getNewInstance(Context context,Class clazz)
    {
        Intent intent=new Intent(context,clazz);
        String name=clazz.getSimpleName();
        return new MainModel(intent,name);
    }
    private MainModel(Intent intent,String name)
    {
        this.intent=intent;
        this.name=name;
    }
}
