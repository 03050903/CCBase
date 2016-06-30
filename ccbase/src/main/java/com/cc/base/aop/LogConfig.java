package com.cc.base.aop;

import org.aspectj.lang.JoinPoint;

/**
 * 打印Log的配置信息，可以配置是否打印类的全名，是否打印方法执行的结果；
 * 同时可以自己配置Log的打印，阻断默认的Log打印；也可以自定义Log打印的内容
 * Created by C&C on 2016/6/27.
 */

public class LogConfig
{
    /**
     * 是否打印类的全名
     */
    private static boolean mClassFullName=true;
    /**
     * 是否打印结果
     */
    private static boolean mPrintResult =false;
    /**
     * 打印Log的中断器
     */
    private static LogPrintInteruptor mLogPrintInteruptor;
    /**
     * 打印自定义Log
     */
    private static CostomPrinter mCostomPrinter;
    /**
     * log打印的中断器，如果设置，所有打印的Log将交由用户控制
     * ，但是打印的内容是确定的，用户不能自定义，如果需要自定义就
     * 需要添加CostomPrinter，可以配置自己需要打印的内容
     */
    public static interface LogPrintInteruptor
    {
        void log(String printMsg);
    }

    /**
     * 打印自定义消息
     */
    public static interface CostomPrinter
    {
        void log(JoinPoint joinPoint);
    }

    /**
     * 设置是否打印结果
     * @param printResult
     * @return
     */
    public LogConfig printResult(boolean printResult)
    {
        mPrintResult =printResult;
        return logConfig;
    }

    /**
     * 设置是否打印类的全名
     * @param classFullName
     * @return
     */
    public LogConfig printClassFullName(boolean classFullName)
    {
        mClassFullName =classFullName;
        return logConfig;
    }

    /**
     * 设置Log打印的中断器
     * @param interuptor
     * @return
     */
    public LogConfig logPrintInteruptor(LogPrintInteruptor interuptor)
    {
        mLogPrintInteruptor =interuptor;
        return logConfig;
    }

    /**
     * 设置自定义的Log打印
     * @param printer
     * @return
     */
    public LogConfig costomPrinter(CostomPrinter printer)
    {
        mCostomPrinter =printer;
        return logConfig;
    }

    /**
     * 私有化构造函数
     */
    private LogConfig(){}

    /**
     * LogConfig的实例
     */
    private static LogConfig logConfig=new LogConfig();

    /**
     * 获取当前配置信息的实例
     * @return
     */
    public static LogConfig getInstance()
    {
        return logConfig;
    }

    /**
     * 是否打印函数的结果
     * @return
     */
    public boolean isPrintResult()
    {
        return mPrintResult;
    }

    /**
     * 是否打印类的全名，包名+类名
     * @return
     */
    public boolean isPrintClassFullName()
    {
        return mClassFullName;
    }

}
