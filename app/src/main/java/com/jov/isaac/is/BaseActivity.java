package com.jov.isaac.is;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.jov.isaac.is.db.DBHelper;
import com.jov.isaac.is.util.CommonUtil;
import com.jov.isaac.is.util.ExitAppUtil;
import com.jov.isaac.is.util.ProgressHUD;

/**
 * Created by shuwei on 15/11/13.
 */
public class BaseActivity extends Activity {
  protected String userid;
  protected String TAG =BaseActivity.class.getName();
  protected ProgressHUD mProgressHUD;
  protected DBHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ExitAppUtil.add(this);
    dbHelper = new DBHelper(this);
  }
  protected void showSimpleMessageDialog(String msg) {
    if (isFinishing()) {
      return;
    }
    if(CommonUtil.isEmpty(msg)){
      msg = "请求失败了，请稍后再试";
    }
    new AlertDialog.Builder(this).setTitle(null).setNegativeButton("确定", null).setMessage(msg).show();
  }

  protected void showSimpleMessageDialogWithTitle(String title, String msg) {
    if (isFinishing()) {
      return;
    }
    if(CommonUtil.isEmpty(msg)){
      msg = "请求失败了，请稍后再试";
    }
    new AlertDialog.Builder(this).setTitle(title).setNegativeButton("确定", null).setMessage(msg).show();
  }
  protected void showSimpleMessageDialog(String msg,DialogInterface.OnClickListener onClickListener) {
    if (isFinishing()) {
      return;
    }
    if(CommonUtil.isEmpty(msg)){
      msg = "请求失败了，请稍后再试";
    }
    new AlertDialog.Builder(this).setTitle(null).setCancelable(false).setNegativeButton("确定", onClickListener).setMessage(msg).show();
  }
  protected void switchTo(Class clazz){
    Intent intent = new Intent(this,clazz);
    startActivity(intent);
  }

  protected void switchTo(Class clazz,Bundle bundle){
    Intent intent = new Intent(this,clazz);
    if(bundle!=null){
      intent.putExtras(bundle);
    }
    startActivity(intent);
  }
  protected void showMessageDialogWithOkAndCancel(String msg,DialogInterface.OnClickListener onClickListener) {
    if (isFinishing()) {
      return;
    }
    if(CommonUtil.isEmpty(msg)){
      msg = "请求失败了，请稍后再试";
    }
    new AlertDialog.Builder(this).setTitle(null).setPositiveButton("确定", onClickListener).setNegativeButton("取消",null).setMessage(msg).show();
  }
  protected void showMessageDialogWithOkAndCancel(String msg,String cancel,String ok,DialogInterface.OnClickListener onClickListener) {
    if (isFinishing()) {
      return;
    }
    if(CommonUtil.isEmpty(msg)){
      msg = "请求失败了，请稍后再试";
    }
    new AlertDialog.Builder(this).setTitle(null).setPositiveButton(ok, onClickListener).setNegativeButton(cancel,null).setMessage(msg).show();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ExitAppUtil.remove(this);
    hiddenProgress();
  }
  protected void hiddenProgress(){
    if (mProgressHUD!=null&&mProgressHUD.isShowing()) {
      mProgressHUD.dismiss();
    }
  }
}
