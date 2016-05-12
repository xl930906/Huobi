package com.android.HuoBiAssistant.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.ui.activity.MainActivity;

import java.math.BigDecimal;
import java.security.MessageDigest;

/**
 * Created by Dragon丶Lz on 2016/4/9.
 */
public class HuobiUtils {


    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 使用MD5对post 进行加密
     * @param s
     * @return
     */

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     *  将Double 类型的值保留四位小数,后面的部分进行四舍五入
     * @param value 需要处理的double
     * @return
     */

    public static Double DealDouble(double value,int point) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(point, BigDecimal.ROUND_HALF_UP);
        return Double.parseDouble(bd.toString());
    }


    public static void ShowNotification(Context context,String title,String content){

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        // 震动相关
        if (true) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {100, 300, 0, 0}; // 轻微震动一下就可以了
            vibrator.vibrate(pattern, -1);// 仅仅震动一次
        }

        // 声音（系统默认的声音）
        if (true) {
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, soundUri);
            r.play();
        }
    }


}
