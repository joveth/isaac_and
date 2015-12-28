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
public class IsaacActivity extends BaseActivity implements ActionBar.OnNavigationListener,SearchView.OnQueryTextListener {
  private DataAdapter adapter;
  private ListView mListView;
  private List<ToolBean> list;
  private String type;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_isaac);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    actionBar.setListNavigationCallbacks(new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_dropdown_item,
        android.R.id.text1, new String[]{"全部", "主动", "被动", "饰品",
        "塔牌", "符文", "胶囊", "人物", "成就","DLC新物"}), this);
    mListView = (ListView)findViewById(R.id.listview);
    list = dbHelper.getBeans();
    adapter = new DataAdapter(this,list);
    mListView.setAdapter(adapter);
  }


  @Override
  public boolean onNavigationItemSelected(int itemPosition, long itemId) {
    list.clear();
    type=null;
    if(itemPosition==0){
      list.addAll(dbHelper.getBeans());
    }else if(itemPosition==9){
      type="A";
      list.addAll(dbHelper.getBeans("A"));
    }else{
      type = ""+itemPosition;
      list.addAll(dbHelper.getBeans(type));
    }
    adapter.notifyDataSetChanged();
    return false;
  }

  @Override
  public boolean onQueryTextSubmit(String query) {
    if (CommonUtil.isEmpty(query)) {
      return true;
    } else {
      list.clear();
      list.addAll(dbHelper.getBeansByKeywords(query));
    }
    adapter.notifyDataSetChanged();
    return true;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    if (CommonUtil.isEmpty(newText)) {
      list.clear();
      if (CommonUtil.isEmpty(type)) {
        list.addAll(dbHelper.getBeans());
      } else {
        list.addAll(dbHelper.getBeans(type));
      }
    } else {
      list.clear();
      list.addAll(dbHelper.getBeansByKeywords(newText));
    }
    adapter.notifyDataSetChanged();
    return true;
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search)
        .getActionView();
    searchView.setSubmitButtonEnabled(true);
    searchView.setOnQueryTextListener(this);
    return true;
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
