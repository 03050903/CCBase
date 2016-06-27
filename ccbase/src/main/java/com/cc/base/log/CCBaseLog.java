package com.cc.base.log;

import android.util.Log;

/**
 * Log的基类,没有最多4000个字符的限制，外部类不可以访问
 */
class CCBaseLog
{

    /**
     * Android 默认打印的最大限制
     */
    private static final int DEFAULT_MAX_LENGTH = 4000;

    /**
     * 打印数据
     *
     * @param level     打印的级别
     * @param tag       打印的标签
     * @param msg       打印的信息
     */
    public static void printDefault(CCLogLevel level, String tag, String msg)
    {

        int index = 0;
        //判断当前需要打印多少次
        int countOfSub = msg.length() / DEFAULT_MAX_LENGTH;
        //当前需要打印的字符数超过了最大限制，需要分批打印
        if (countOfSub > 0)
        {
            for (int i = 0; i < countOfSub; i++)
            {
                //截取字符串，打印对应位置的字符串
                String sub = msg.substring(index, index + DEFAULT_MAX_LENGTH);
                printSub(level, tag, sub);
                index += DEFAULT_MAX_LENGTH;
            }
            //打印剩余的字符串
            printSub(level, tag, msg.substring(index, msg.length()));
        } else
        {
            printSub(level, tag, msg);
        }
    }

    /**
     * 打印字符串
     * @param level      打印的类型
     * @param tag       打印的标签
     * @param sub       打印的内容
     */
    private static void printSub(CCLogLevel level, String tag, String sub)
    {
        switch (level)
        {
            case V:
                Log.v(tag, sub);
                break;
            case D:
                Log.d(tag, sub);
                break;
            case I:
                Log.i(tag, sub);
                break;
            case W:
                Log.w(tag, sub);
                break;
            case E:
                Log.e(tag, sub);
                break;
            case A:
                Log.wtf(tag, sub);
                break;
        }
    }

}
