package com.cc.base.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * 打印Log的一个帮助类
 */
class CCLogUtils
{

    /**
     * 打印的信息为null时显示的提示信息
     */
    public static final String NULL_TIPS="Log with null object";
    /**
     * 获取特定系统中一行的分隔符
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * 打印Json的时候使用的缩进
     */
    public static final int JSON_INDENT = 4;

    /**
     * 判断给定的字符串是不是空的或者空白
     * @param line      判断的字符串
     * @return          true：给定的字符串是空的，false:不是空的
     */
    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    /**
     * 打印横线
     * @param tag           打印的标签
     * @param isTop         是否在顶部打印，true顶部，false底部
     */
    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

}
