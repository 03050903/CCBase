package com.cc.base.log;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * 打印文件的Log
 */
class CCFileLog
{

    /**
     * 打印信息到文件
     * @param tag                   标签
     * @param targetDirectory       文件所在的文件夹
     * @param fileName              文件名
     * @param headString            消息的头部标示
     * @param msg                   打印的信息
     */
    static void printFile(String tag, File targetDirectory, String fileName, String headString, String msg)
    {

        //判断文件名的有效性，然后获取一个文件名
        fileName = (fileName == null) ? getFileName() : fileName;
        //打印信息
        if (save(tag,targetDirectory, fileName, msg))
        {
            //在文件中打印信息成功的话，打印一个提示信息，表示打印信息成功,并且显示了打印到文件的本地地址
            Log.d(tag, headString + " save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/" + fileName);
        } else
        {
            //打印到文件失败的情况下，打印日志提示失败
            Log.e(tag, headString + "save log fails !");
        }
    }

    /**
     * 打印信息到文件
     * @param tag           打印的Tag
     * @param dic           文件夹名称
     * @param fileName      文件名称
     * @param msg           打印的信息
     * @return              是否打印成功
     */
    private static boolean save(String tag,File dic, String fileName, String msg)
    {
        File file = new File(dic, fileName);
        try
        {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStream.close();
        } catch (Exception e)
        {
            Log.e(tag,"save log to file failed:"+e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取一个随机的文件名
     *
     * @return 随机的文件名
     */
    private static String getFileName()
    {
        Random random = new Random();
        return "KLog_" + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + ".txt";
    }

}
