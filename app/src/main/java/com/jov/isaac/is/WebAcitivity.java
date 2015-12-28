package com.jov.isaac.is;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by shuwei on 15/12/28.
 */
public class WebAcitivity extends BaseActivity {
  private WebView webView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    webView  = (WebView)findViewById(R.id.webview);
    Intent intent = getIntent();
    String html = intent.getStringExtra("html");
    String title = intent.getStringExtra("title");
    getActionBar().setTitle(title);
    webView.loadUrl("file:///android_asset/www/"+html);
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
