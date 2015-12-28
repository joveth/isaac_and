package com.jov.isaac.is;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.jov.isaac.is.adapter.DataAdapter;
import com.jov.isaac.is.db.ToolBean;
import com.jov.isaac.is.util.CommonUtil;

import java.util.List;

/**
 * Created by shuwei on 15/12/22.
 */
public class OtherActivity extends BaseActivity {
  private DataAdapter adapter;
  private ListView mListView;
  private List<ToolBean> list;
  private String type;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_isaac);
    getActionBar().setDisplayHomeAsUpEnabled(true);

    Intent intent = getIntent();
    type = intent.getStringExtra("type");
    String title = intent.getStringExtra("title");
    if(!CommonUtil.isEmpty(title)){
      getActionBar().setTitle(title);
    }
    if(CommonUtil.isEmpty(type)){
      type="1";
    }
    mListView = (ListView)findViewById(R.id.listview);
    list = dbHelper.getOther(type);
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
