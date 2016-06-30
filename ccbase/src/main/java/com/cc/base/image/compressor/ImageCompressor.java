/*
 * Copyright 2014 http://Bither.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cc.base.image.compressor;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;

/**
 * 压缩图片，默认的图片的质量是70
 */
public class ImageCompressor
{
    /**
     * 图片压缩的时候默认的质量
     */
    private static int DEFAULT_QUALITY = 70;

    /**
     * 压缩图片
     *
     * @param bit      压缩的Bitmap
     * @param fileName 压缩到文件
     * @param optimize 这个参数决定了图片显示的质量，true：图片压缩出来大小会比较小，质量也相对较高
     * @return true压缩成功
     */
    public static boolean compressBitmap(Bitmap bit, String fileName, boolean optimize)
    {
        return compressBitmap(bit, DEFAULT_QUALITY, fileName, optimize);

    }

    /**
     * 压缩图片
     *
     * @param bit      压缩的Bitmap
     * @param quality  压缩的质量
     * @param fileName 压缩到的文件
     * @param optimize 这个参数决定了图片显示的质量，true：图片压缩出来大小会比较小，质量也相对较高
     * @return true压缩成功
     */
    public static boolean compressBitmap(Bitmap bit, int quality, String fileName, boolean optimize)
    {
        String savePath=null;
        if (bit.getConfig() != Config.ARGB_8888)
        {
            Bitmap result = null;
            result = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            Rect rect = new Rect(0, 0, bit.getWidth(), bit.getHeight());
            canvas.drawBitmap(bit, null, rect, null);
            savePath=saveBitmap(result, quality, fileName, optimize);
            result.recycle();
        } else
        {
            savePath=saveBitmap(bit, quality, fileName, optimize);
        }
        return TextUtils.isEmpty(savePath);

    }

    /**
     * 压缩图片
     *
     * @param bit      压缩的Bitmap
     * @param quality  压缩的质量
     * @param fileName 压缩到的文件
     * @param optimize 这个参数决定了图片显示的质量，true：图片压缩出来大小会比较小，质量也相对较高
     * @return  压缩文件的保存路径
     */
    private static String saveBitmap(Bitmap bit, int quality, String fileName, boolean optimize)
    {
        return compressBitmap(bit, bit.getWidth(), bit.getHeight(), quality, fileName.getBytes(), optimize);

    }
    private static native String compressBitmap(Bitmap bit, int w, int h, int quality, byte[] fileNameBytes, boolean optimize);

    static
    {
        System.loadLibrary("jpegbither");
        System.loadLibrary("bitherjni");

    }

}
