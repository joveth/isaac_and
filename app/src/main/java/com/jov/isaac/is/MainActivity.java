package com.jov.isaac.is;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jov.isaac.is.adapter.DataAdapter;
import com.jov.isaac.is.util.ExitAppUtil;
import com.jov.isaac.is.util.ProgressHUD;

public class MainActivity extends BaseActivity implements View.OnClickListener{
  private long exitTime;
  private View vIsaac,vBoss,vSmall,vBase,vRoom,vEarth,vDlc,vMore,vAbout,vCheckUpdate;
  private TextView vVersionTxt;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    UpdateManager updateManager = new UpdateManager(this);
    updateManager.checkVersionThread();
    initView();
  }
  private void initView(){
    vVersionTxt = (TextView)findViewById(R.id.version_txt);
    vIsaac = findViewById(R.id.isaac_id);
    vBoss = findViewById(R.id.boss_id);
    vSmall = findViewById(R.id.small_id);
    vBase = findViewById(R.id.base_id);
    vRoom = findViewById(R.id.room_id);
    vEarth = findViewById(R.id.earth_id);
    vDlc = findViewById(R.id.dlc_id);
    vMore = findViewById(R.id.more_id);
    vAbout = findViewById(R.id.about_id);
    vCheckUpdate = findViewById(R.id.checkupdate_lay);
    getVersion();
    vIsaac.setOnClickListener(this);
    vBoss.setOnClickListener(this);
    vSmall.setOnClickListener(this);
    vRoom.setOnClickListener(this);
    vBase.setOnClickListener(this);
    vEarth.setOnClickListener(this);
    vDlc.setOnClickListener(this);
    vMore.setOnClickListener(this);
    vAbout.setOnClickListener(this);
    vCheckUpdate.setOnClickListener(this);
    initData();
  }
  private void initData(){
    int total = dbHelper.getTotalCount();
    Log.d(TAG,total+"");
    if(total==0){
      if(mProgressHUD==null){
        mProgressHUD = ProgressHUD.show(this, "初始化中", false, null);
      }
      try {
        dbHelper.initInsert();
      }catch (Exception e){

      }finally {
        hiddenProgress();
      }
    }
  }
  private void getVersion() {
    PackageManager manager = this.getPackageManager();
    PackageInfo info = null;
    try {
      info = manager.getPackageInfo(this.getPackageName(), 0);
      if (info != null) {
        vVersionTxt.setText(info.versionName);
      }
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void backTo() {
    if ((System.currentTimeMillis() - exitTime) > 2000) {
      Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
      exitTime = System.currentTimeMillis();
    } else {
      ExitAppUtil.exit();
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
      backTo();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }

  @Override
  public void onClick(View v) {
    if(v==vIsaac){
      switchTo(IsaacActivity.class);
      return;
    }
    if(v==vBoss){
      switchTo(BossActivity.class);
      return;
    }
    if(v==vSmall){
      switchTo(SmallActivity.class);
      return;
    }
    if(v==vRoom){
      Bundle bundle =new Bundle();
      bundle.putString("type","3");
      bundle.putString("title","房间说明");
      switchTo(OtherActivity.class,bundle);
      return;
    }
    if(v==vBase){
      Bundle bundle =new Bundle();
      bundle.putString("type","1");
      bundle.putString("title", "基础掉落");
      switchTo(OtherActivity.class,bundle);
      return;
    }
    if(v==vEarth){
      Bundle bundle =new Bundle();
      bundle.putString("type","2");
      bundle.putString("title", "地形物体");
      switchTo(OtherActivity.class,bundle);
      return;
    }
    if(v==vDlc){
      switchTo(DLCActivity.class);
      return;
    }
    if(v==vAbout){
      switchTo(AboutActivity.class);
      return;
    }
    if(v==vMore){
      switchTo(MoreActivity.class);
      return;
    }
    if(v==vCheckUpdate){
      showSimpleMessageDialog("亲，没有新版本，哈~");
      return;
    }
  }
}
