package com.cc.base.log;


import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;

/**
 *  Log帮助类，实现一些方便的功能，可以打印方法名称和所在行，打印json，xml，支持打印信息到文件
 */
public class CCLog
{
    /**
     * 默认打印的tag
     */
    /**
     * 打印的信息为null的时候显示的提示信息
     */
    private static final String PARAM = "Param";
    private static final String NULL = "null";
    /**
     * 默认打印的Tag
     */
    private static final String TAG_DEFAULT = "CCLog";
    private static final String SUFFIX = ".java";


    /**
     * 栈信息的索引
     */
    private static final int STACK_TRACE_INDEX = 5;
    /**
     * 打印的时候显示的全局Tag
     */
    private static String mGlobalTag;
    /**
     * 打印Log的时候，全局的tag是否为空
     */
    private static boolean mIsGlobalTagEmpty = true;
    /**
     * 是否显示Log
     */
    private static boolean IS_SHOW_LOG = true;

    /**
     * 初始化方法
     *
     * @param isShowLog 是否显示Log
     */
    public static void init(boolean isShowLog)
    {
        IS_SHOW_LOG = isShowLog;
    }

    /**
     * 初始化方法
     *
     * @param isShowLog 是否显示Log
     * @param tag       打印Log的时候显示的Tag
     */
    public static void init(boolean isShowLog, @Nullable String tag)
    {
        IS_SHOW_LOG = isShowLog;
        mGlobalTag = tag;
        mIsGlobalTagEmpty = TextUtils.isEmpty(mGlobalTag);
    }


    /**
     * 打印Verbose Log
     *
     * @param msg 打印的信息
     */
    public static void v(Object msg)
    {
        printLog(CCLogLevel.V, null, msg);
    }

    /**
     * 打印Verbose Log
     *
     * @param tag     打印的tag
     * @param objects 打印的信息
     */
    public static void v(String tag, Object... objects)
    {
        printLog(CCLogLevel.V, tag, objects);
    }

    /**
     * 打印Debug Log
     *
     * @param msg 打印的信息
     */
    public static void d(Object msg)
    {
        printLog(CCLogLevel.D, null, msg);
    }

    /**
     * 打印Debug Log
     *
     * @param tag     打印的tag
     * @param objects 打印的信息
     */
    public static void d(String tag, Object... objects)
    {
        printLog(CCLogLevel.D, tag, objects);
    }


    /**
     * 打印Info Log
     *
     * @param msg 打印的消息
     */
    public static void i(Object msg)
    {
        printLog(CCLogLevel.I, null, msg);
    }

    /**
     * 打印Info Log
     *
     * @param tag     打印的Tag
     * @param objects 打印的信息
     */
    public static void i(String tag, Object... objects)
    {
        printLog(CCLogLevel.I, tag, objects);
    }


    /**
     * 打印Warning Log
     *
     * @param msg 打印的信息
     */
    public static void w(Object msg)
    {
        printLog(CCLogLevel.W, null, msg);
    }

    /**
     * 打印Warning Log
     *
     * @param tag     打印的Tag
     * @param objects 打印的信息
     */
    public static void w(String tag, Object... objects)
    {
        printLog(CCLogLevel.W, tag, objects);
    }


    /**
     * 打印Error Log
     *
     * @param msg 打印的信息
     */
    public static void e(Object msg)
    {
        printLog(CCLogLevel.E, null, msg);
    }

    /**
     * 打印Error Log
     *
     * @param tag     打印的Tag
     * @param objects 打印的信息
     */
    public static void e(String tag, Object... objects)
    {
        printLog(CCLogLevel.E, tag, objects);
    }


    /**
     * 打印Assert Log
     *
     * @param msg 打印的信息
     */
    public static void a(Object msg)
    {
        printLog(CCLogLevel.A, null, msg);
    }

    /**
     * 打印Assert Log
     *
     * @param tag     打印的Tag
     * @param objects 打印的信息
     */
    public static void a(String tag, Object... objects)
    {
        printLog(CCLogLevel.A, tag, objects);
    }

    /**
     * 打印Json信息
     *
     * @param jsonFormat 打印的信息
     */
    public static void json(String jsonFormat)
    {
        printLog(CCLogLevel.JSON, null, jsonFormat);
    }

    /**
     * 打印Json信息
     *
     * @param tag        打印的Tag
     * @param jsonFormat 打印的信息
     */
    public static void json(String tag, String jsonFormat)
    {
        printLog(CCLogLevel.JSON, tag, jsonFormat);
    }

    /**
     * 打印Xml信息
     *
     * @param xml 打印的信息
     */
    public static void xml(String xml)
    {
        printLog(CCLogLevel.XML, null, xml);
    }

    /**
     * 打印Xml信息
     *
     * @param tag 打印的Tag
     * @param xml 打印的信息
     */
    public static void xml(String tag, String xml)
    {
        printLog(CCLogLevel.XML, tag, xml);
    }

    /**
     * 打印到文件
     *
     * @param targetDirectory 文件所处的文件夹
     * @param msg             打印的信息
     */
    public static void file(File targetDirectory, Object msg)
    {
        printFile(null, targetDirectory, null, msg);
    }

    /**
     * 打印到文件
     *
     * @param tag             打印的Tag
     * @param targetDirectory 文件所处的文件夹
     * @param msg             打印的信息
     */
    public static void file(String tag, File targetDirectory, Object msg)
    {
        printFile(tag, targetDirectory, null, msg);
    }

    /**
     * 打印到文件
     *
     * @param tag             打印的Tag
     * @param targetDirectory 文件所处的文件夹
     * @param fileName        打印到的文件
     * @param msg             打印的信息
     */
    public static void file(String tag, File targetDirectory, String fileName, Object msg)
    {
        printFile(tag, targetDirectory, fileName, msg);
    }

    /**
     * 打印Log
     *
     * @param level   打印Log的级别
     * @param tagStr  打印的tag信息
     * @param objects 打印的信息
     */
    private static void printLog(CCLogLevel level, String tagStr, Object... objects)
    {

        if (!IS_SHOW_LOG)
        {
            return;
        }

        String[] contents = wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (level)
        {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                CCBaseLog.printDefault(level, tag, headString + msg);
                break;
            case JSON:
                CCJsonLog.printJson(tag, msg, headString);
                break;
            case XML:
                CCXmlLog.printXml(tag, msg, headString);
                break;
        }
    }


    private static void printFile(String tagStr, File targetDirectory, String fileName, Object objectMsg)
    {
        if (!IS_SHOW_LOG)
        {
            return;
        }
        String[] contents = wrapperContent(tagStr, objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        CCFileLog.printFile(tag, targetDirectory, fileName, headString, msg);
    }

    /**
     * 包装格式化打印的内容
     *
     * @param tagStr  打印的Tag
     * @param objects 打印的信息
     * @return 打印的信息，是一个一维数组，还有三个值，tag:打印的Tag，msg:打印的信息，headstring：打印的头信息
     */
    private static String[] wrapperContent(String tagStr, Object... objects)
    {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[STACK_TRACE_INDEX];
        //获取类名
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0)
        {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }
        //如果是内部类的话，显示的是外边的父类
        if (className.contains("$"))
        {
            className = className.split("\\$")[0] + SUFFIX;
        }
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0)
        {
            lineNumber = 0;
        }
        //执行的方法的名称
        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        //设置打印的时候打印的Tag
        String tag = (tagStr == null ? className: tagStr);
//        String tag = (tagStr == null ? (IS_SHOW_CLASS_NAME?className:TAG): tagStr);
        if (mIsGlobalTagEmpty && TextUtils.isEmpty(tag))
        {
            tag = TAG_DEFAULT;
        } else if (!mIsGlobalTagEmpty)
        {
            tag = mGlobalTag;
        }
        //获取打印的信息
        String msg = (objects == null) ? CCLogUtils.NULL_TIPS : getObjectsString(objects);
        //打印的时候显示的头信息
        String headString = "[ (" + className + ":" + lineNumber + ")#" + methodNameShort + " ] ";
        return new String[]{tag, msg, headString};
    }

    /**
     * 获取打印的数据
     *
     * @param objects 打印到信息
     * @return 打印的字符串
     */
    private static String getObjectsString(Object... objects)
    {
        if (objects.length > 1)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++)
            {
                Object object = objects[i];
                if (object == null)
                {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else
                {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else
        {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }

}
