package com.jov.isaac.is;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.jov.isaac.is.adapter.DataAdapter;
import com.jov.isaac.is.db.ToolBean;
import com.jov.isaac.is.util.CommonUtil;

import java.util.List;

/**
 * Created by shuwei on 15/12/22.
 */
public class SmallActivity extends BaseActivity {
  private DataAdapter adapter;
  private ListView mListView;
  private List<ToolBean> list;
  private String type;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_isaac);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setTitle("小怪图鉴");
    mListView = (ListView)findViewById(R.id.listview);
    list = dbHelper.getSmall();
    adapter = new DataAdapter(this,list);
    mListView.setAdapter(adapter);
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
