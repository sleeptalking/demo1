package com.hflh.demo.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static Toast toast;

    /**
     * 共用一个toast对象  可以解决 连续点击之后一直弹出的问题。
     * @param context
     * @param message
     * @param isLong
     */
    public static void showToastByOne(Context context, String message, boolean isLong) {
        if (toast == null) {
            if (isLong) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            }
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 共用一个toast对象  可以解决 连续点击之后一直弹出的问题。
     * @param context
     * @param resId
     * @param isLong
     */
    public static void showToastByOne(Context context, int resId, boolean isLong) {
        if (toast == null) {
            if (isLong) {
                toast = Toast.makeText(context, context.getString(resId), Toast.LENGTH_LONG);
            } else {
                toast = Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT);
            }
        } else {
            toast.setText(context.getString(resId));
        }
        toast.show();
    }

    public static void detach(){
        if(toast != null) toast = null;
    }

    /**
     * 参数：消息 显示时长
     */
    public static void showToast(Context context, String str, boolean isLong) {
        if (isLong) {
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 参数：消息id
     */
    public static void showToast(Activity context,int resId, boolean isLong) {
        showToast(context,context.getString(resId),isLong);
    }


    //测试git 上传

}
