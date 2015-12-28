package com.jov.isaac.is.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jov on 2015/1/16.
 */
public class CommonUtil {
  public static String getNowDate() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
    return format.format(new Date());
  }

  public static String getNowDateNoFormat() {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHssmm");
    return format.format(new Date());
  }

  public static boolean isEmpty(String str) {
    return str == null || str.trim().length() == 0;
  }

  public static boolean isNetWorkConnected(Context context) {
    if (context != null) {
      ConnectivityManager mConnectivityManager = (ConnectivityManager) context
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
      if (mNetworkInfo != null) {
        return mNetworkInfo.isAvailable();
      }
    }
    return false;
  }

  public static String encodingMD5(String val) {
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("MD5 should be unsupported", e);
    }
    md5.update(val.getBytes());
    byte[] m = md5.digest();// 加密
    return getString(m);
  }

  private static String getString(byte[] b) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      sb.append(b[i]);
    }
    return sb.toString();
  }

  /**
   * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
   */
  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int px2sp(Context context, float pxValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (pxValue / fontScale + 0.5f);
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
   */
  public static int px2dip(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public static String dateStrFormat(String str) {
    if (str == null)
      return null;
    if (str.length() == 6)
      return str.substring(0, 4) + "-" + str.substring(4, 6);
    if (str.length() == 8)
      return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);

    if (str.length() == 10)
      return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10);

    if (str.length() == 12)
      return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10)
          + ":" + str.substring(10, 12);
    if (str.length() == 14)
      return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10)
          + ":" + str.substring(10, 12) + ":" + str.substring(12, 14);
    return str;
  }
  /**
   * xxxx/xx/xx xx:xx
   */
  public static String dateStrFormatTwo(String str) {
    if (str == null)
      return null;
    if (str.length() == 6)
      return str.substring(0, 4) + "/" + str.substring(4, 6);
    if (str.length() == 8)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8);

    if (str.length() == 10)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8) + " " + str.substring(8, 10);

    if (str.length() >= 12)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8) + " " + str.substring(8, 10)
          + ":" + str.substring(10, 12);
    return str;
  }
  /**
   * xxxx/xx/xx xx:xx:xx
   */
  public static String dateStrFormatTwoFull(String str) {
    if (str == null)
      return null;
    if (str.length() == 6)
      return str.substring(0, 4) + "/" + str.substring(4, 6);
    if (str.length() == 8)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8);

    if (str.length() == 10)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8) + " " + str.substring(8, 10);

    if (str.length() == 12)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8) + " " + str.substring(8, 10)
          + ":" + str.substring(10, 12);
    if (str.length() == 14)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8) + " " + str.substring(8, 10)
          + ":" + str.substring(10, 12) + ":" + str.substring(12, 14);
    return str;
  }
  public static String dateStrFormatShort(String str) {
    if (str == null)
      return null;
    if (str.length() == 6)
      return str.substring(0, 4) + "/" + str.substring(4, 6);
    if (str.length() >= 8)
      return str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8);
    return str;
  }
  public static String timeFormat(String str){
    if (str == null)
      return null;
    if (str.length() <= 4)
      return str.substring(0, 2) + ":" + str.substring(2, 4);
    if(str.length()>4)
      return str.substring(0, 2) + ":" + str.substring(2, 4);
    return str;
  }

  // xx月xx日
  public static String dateMonthFormatZH(String str) {
    if (str == null)
      return null;
    if (str.length() == 6)
      return str.substring(0, 4) + "年" + str.substring(4, 6) + "月";
    if (str.length() >= 8)
      return str.substring(4, 6) + "月" + str.substring(6, 8) + "日";
    return str;
  }

  // xx-xx
  public static String dateMonthFormatEN(String str) {
    if (str == null)
      return null;
    if (str.length() == 6)
      return str.substring(0, 4) + "-" + str.substring(4, 6);
    if (str.length() >= 8)
      return str.substring(4, 6) + "-" + str.substring(6, 8);
    return str;
  }


  public static String dateTransToDayOrYester(String date) {
    if (date == null) {
      return null;
    }
    if (date.indexOf("-") < 0) {
      return date;
    }
    String notificationdate = date.substring(0, 10);
    String year = date.substring(0, 4);
    String notificationtime = date.substring(11);
    String hourAndMin = notificationtime.substring(0, 5);
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    if (format.format(calendar.getTime()).equals(notificationdate)) {
      String hour = hourAndMin.substring(0, 2);
      return "今天 " + hourAndMin;
    } else {
      try {
        Date locDate = format.parse(notificationdate);
        Calendar col = Calendar.getInstance();
        col.setTime(locDate);
        Calendar yesterday = Calendar.getInstance();
        yesterday.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        if (format.format(yesterday.getTime()).equals(notificationdate)) {
          return "昨天 " + hourAndMin;
        }
        if (year.equals(String.valueOf(col.get(Calendar.YEAR)))) {
          return dateMonthFormatEN(notificationdate.replaceAll("-", ""));
        }
        return date;
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return notificationdate;
    }
  }

  private static java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");
  private static java.text.DecimalFormat decimalFormatNo = new java.text.DecimalFormat("0");
  public static String decialStrFormat(String doubleStr) {
    try {
      double temp = Double.valueOf(doubleStr);
      return decimalFormat.format(temp);
    } catch (Exception e) {
      return null;
    }
  }

  public static String decialStrFormat(double d) {
    try {
      return decimalFormat.format(d);
    } catch (Exception e) {
      return null;
    }
  }
  public static String decialStrFormatNo(double d) {
    try {
      return decimalFormatNo.format(d);
    } catch (Exception e) {
      return null;
    }
  }
  private static long lastClickTime;

  public static boolean isFastDoubleClick() {
    long time = System.currentTimeMillis();
    long timeD = time - lastClickTime;
    if (timeD >= 0 && timeD <= 600) {
      return true;
    } else {
      lastClickTime = time;
      return false;
    }
  }

  /**
   * 以最省内存的方式读取本地资源的图片
   *
   * @param context
   * @param resId
   * @return
   */
  public static Bitmap readBitMap(Context context, int resId) {
    BitmapFactory.Options opt = new BitmapFactory.Options();
    opt.inPreferredConfig = Bitmap.Config.RGB_565;
    opt.inPurgeable = true;
    opt.inInputShareable = true;
    //获取资源图片
    InputStream is = context.getResources().openRawResource(resId);
    return BitmapFactory.decodeStream(is, null, opt);
  }

  public static double mapValueFromRangeToRange(
      double value,
      double fromLow,
      double fromHigh,
      double toLow,
      double toHigh) {
    double fromRangeSize = fromHigh - fromLow;
    double toRangeSize = toHigh - toLow;
    double valueScale = (value - fromLow) / fromRangeSize;
    return toLow + (valueScale * toRangeSize);
  }

  /**
   * set margins of the specific view
   *
   * @param target
   * @param l
   * @param t
   * @param r
   * @param b
   */
  public static void setMargin(View target, int l, int t, int r, int b) {
    if (target.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
      ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) target.getLayoutParams();
      p.setMargins(l, t, r, b);
      target.requestLayout();
    }
  }

  /**
   * convert drawable to bitmap
   *
   * @param drawable
   * @return
   */
  public static Bitmap drawableToBitmap(Drawable drawable) {
    int width = drawable.getIntrinsicWidth();
    int height = drawable.getIntrinsicHeight();
    Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, width, height);
    drawable.draw(canvas);
    return bitmap;

  }

  public static void showSimpleMessageDialog(Context context, String msg) {
    if (CommonUtil.isEmpty(msg)) {
      msg = "请求失败了，请稍后再试";
    }
    new AlertDialog.Builder(context).setTitle(null).setNegativeButton("确定", null).setMessage(msg).show();
  }
  public static String billStatusTrans(String billstatus,String status){
    if(isEmpty(billstatus)&&isEmpty(status)){
      return "处理失败";
    }
    if("1".equals(billstatus)){
      return "订单无效";
    }
    if("0".equals(billstatus)&&"0".equals(status)){
      return "等待用餐";
    }
    if("0".equals(billstatus)&&"1".equals(status)){
      return "已完成";
    }
    if("0".equals(billstatus)&&"2".equals(status)){
      return "付款失败";
    }
    if("2".equals(billstatus)){
      return "已撤销";
    }
    if("3".equals(billstatus)){
      return "已过期";
    }
    return "处理失败";
  }
}
