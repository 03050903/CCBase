package com.cc.base.log;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 打印Json的Log
 */
class CCJsonLog
{

    /**
     * 打印json
     *
     * @param tag        打印的标签
     * @param msg        打印的消息
     * @param headString 打印的头部信息
     */
    static void printJson(String tag, String msg, String headString)
    {
        //获取需要打印的数据
        String message;
        try
        {
            if (msg.startsWith("{"))
            {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(CCLogUtils.JSON_INDENT);
            } else if (msg.startsWith("["))
            {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(CCLogUtils.JSON_INDENT);
            } else
            {
                message = msg;
            }
        } catch (JSONException e)
        {
            message = msg;
        }
        //打印分隔线
        CCLogUtils.printLine(tag, true);
        message = headString + CCLogUtils.LINE_SEPARATOR + message;
        //分隔当前的需要打印的数据，然后按行打印数据
        String[] lines = message.split(CCLogUtils.LINE_SEPARATOR);
        for (String line : lines)
        {
            Log.d(tag, "║ " + line);
        }
        //打印底部分隔线
        CCLogUtils.printLine(tag, false);
    }
}
