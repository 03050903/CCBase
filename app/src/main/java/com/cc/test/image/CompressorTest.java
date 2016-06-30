package com.cc.test.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.cc.base.image.compressor.ImageCompressor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by C&C on 2016/6/30.
 */

public class CompressorTest extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        testJpeg();
    }
    private void testJpeg()
    {
        Log.i("chenTest","开始测试");
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Log.i("chenTest", "1");
                    int quality = 70;
                    InputStream in = getResources().getAssets()
                            .open("test.jpg");
                    Bitmap bit = BitmapFactory.decodeStream(in);
                    File dirFile = Environment.getExternalStorageDirectory();
                    Log.i("chenTest", "2");
                    if (!dirFile.exists())
                    {
                        dirFile.mkdirs();
                    }
                    Log.i("chenTest", "3");
                    File originalFile = new File(dirFile, "original.jpg");
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            originalFile);
                    bit.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
                    File jpegTrueFile = new File(dirFile, "jpegtrue.jpg");
                    File jpegFalseFile = new File(dirFile, "jpegfalse.jpg");
                    Log.i("chenTest", "4");
                    ImageCompressor.compressBitmap(bit, quality,
                            jpegTrueFile.getAbsolutePath(), true);
                    ImageCompressor.compressBitmap(bit, quality,
                            jpegFalseFile.getAbsolutePath(), false);
                    Log.i("chenTest", "5");
                } catch (Exception e)
                {

                    e.printStackTrace();
                    Log.i("chenTest", "error:" + e.getMessage());

                }

            }
        }).start();
    }
}
