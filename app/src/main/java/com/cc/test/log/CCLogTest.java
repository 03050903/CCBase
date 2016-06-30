package com.cc.test.log;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cc.base.log.CCLog;
import com.cc.test.R;


/**
 * Created by C&C on 2016/6/23.
 */

public class CCLogTest extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_activity);
    }
    public void showLog(View v)
    {
        CCLog.v("test CCLog.v(void)");
        CCLog.v("CCLogTest","test CCLog.v(args)");
        CCLog.json("{\"name\":\"zhangshan\",\"age\":12}");
    }
}
