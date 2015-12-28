package com.jov.isaac.is;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by shuwei on 15/12/28.
 */
public class MoreActivity extends BaseActivity implements View.OnClickListener{
  private View v1,v2,v3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_more);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    v1 = findViewById(R.id.m1_lay);
    v2 = findViewById(R.id.m2_lay);
    v3 = findViewById(R.id.m3_lay);
    v1.setOnClickListener(this);
    v2.setOnClickListener(this);
    v3.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Bundle bundle = new Bundle();
    if(v1==v){
      bundle.putString("html","1.html");
      bundle.putString("title","Boss Rush");
      switchTo(WebAcitivity.class,bundle);
      return;
    }
    if(v2==v){
      bundle.putString("html","3.html");
      bundle.putString("title", "套装");
      switchTo(WebAcitivity.class,bundle);
      return;
    }
    if(v3==v){
      bundle.putString("html","2.html");
      bundle.putString("title","捐款机和献血机");
      switchTo(WebAcitivity.class,bundle);
      return;
    }
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
