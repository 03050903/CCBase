package com.cc.base.log;

/**
 * 定义了打印Log的级别，对应于Logcat中的级别
 * Created by C&C on 2016/6/23.
 */
enum CCLogLevel
{
    /**
     * Verbose
     */
    V,
    /**
     * Info
     */
    I,
    /**
     * Dubug
     */
    D,
    /**
     * Warn
     */
    W,
    /**
     * Error
     */
    E,
    /**
     * Assert
     */
    A,
    /**
     * 打印的是Json
     */
    JSON,
    /**
     * 打印的是XML
     */
    XML,
    /**
     * 打印Log到文件
     */
    FILE
}
