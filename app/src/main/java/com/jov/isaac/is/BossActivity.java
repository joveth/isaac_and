package com.jov.isaac.is;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jov.isaac.is.adapter.BossAdapter;
import com.jov.isaac.is.adapter.DataAdapter;
import com.jov.isaac.is.db.ToolBean;

import java.util.List;

/**
 * Created by shuwei on 15/12/23.
 */
public class BossActivity extends BaseActivity {
  private BossAdapter adapter;
  private GridView gridView;
  private List<ToolBean> list;
  private String type;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_boss);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setTitle("Boss图鉴");
    gridView = (GridView)findViewById(R.id.listview);
    list = dbHelper.getBoss();
    adapter = new BossAdapter(this,list);
    gridView.setAdapter(adapter);
    BossAdapter.BossClickListener clickListener = new BossAdapter.BossClickListener() {
      @Override
      public void doClick(ToolBean bean) {
        doShow(bean);
      }
    };
    initDialog();
    adapter.setBossClickListener(clickListener);
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
  private static Dialog showDialog;
  public TextView itemName, itemValue,vItemDesc,vItemEname;
  public ImageView itemImage;
  private void initDialog(){
    showDialog =   new Dialog(this, R.style.CustomDialog);
    showDialog.setTitle(null);
    showDialog.setCancelable(true);
    showDialog.setCanceledOnTouchOutside(false);
    showDialog.setContentView(R.layout.show_boss_dialog);
    itemName = (TextView)showDialog.findViewById(R.id.item_name);
    itemValue = (TextView)showDialog.findViewById(R.id.item_value);
    vItemDesc = (TextView)showDialog.findViewById(R.id.item_desc);
    vItemEname = (TextView)showDialog.findViewById(R.id.item_ename);
    itemImage = (ImageView)showDialog.findViewById(R.id.item_image);
    showDialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showDialog.dismiss();
      }
    });
  }
  private void doShow(ToolBean bean){
    itemName.setText(bean.getName());
    itemValue.setText(bean.getPower());
    try{
      itemImage.setImageBitmap(BitmapFactory.decodeStream(this
          .getAssets().open(bean.getImg())));
    }catch (Exception e){
    }
    vItemDesc.setText(bean.getDesc());
    vItemEname.setText(bean.getEnName());
    showDialog.show();
  }
}
