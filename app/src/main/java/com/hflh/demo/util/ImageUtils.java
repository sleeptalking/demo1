package com.hflh.demo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ImageUtils {


    /**
     * 将base64转换为bitmap
     *
     * @param string  base64 格式的字符串
     * @return
     */
    public static Bitmap stringtoBitmap(String string) {
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            string = string.replace("data:image/jpeg;base64,","");
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
