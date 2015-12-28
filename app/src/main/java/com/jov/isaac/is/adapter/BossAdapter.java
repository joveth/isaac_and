package com.jov.isaac.is.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jov.isaac.is.R;
import com.jov.isaac.is.db.ToolBean;
import java.util.List;

/**
 * Created by shuwei on 15/11/17.
 */
public class BossAdapter extends BaseAdapter {
  private List<ToolBean> list;
  private Context context;
  private BossClickListener bossClickListener;
  public BossAdapter(Context context, List<ToolBean> list) {
    this.context = context;
    this.list = list;
  }

  public BossClickListener getBossClickListener() {
    return bossClickListener;
  }

  public void setBossClickListener(BossClickListener bossClickListener) {
    this.bossClickListener = bossClickListener;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(final int position, View view, ViewGroup parent) {
    final Holder hold;
    final ToolBean bean = list.get(position);
    if (view == null) {
      hold = new Holder();
      view = View.inflate(context, R.layout.item_boss, null);
      hold.itemName = (TextView) view.findViewById(R.id.item_name);
      hold.itemPrice = (TextView) view.findViewById(R.id.item_price);
      hold.itemImage = (ImageView) view.findViewById(R.id.item_image);
      hold.lay = view.findViewById(R.id.item_lay);
      //hold.ratingBar = (RatingBar)view.findViewById(R.id.star_rate);
      view.setTag(hold);
    } else {
      hold = (Holder) view.getTag();
    }
    String name = bean.getName();
    hold.itemName.setText(name);
    hold.itemPrice.setText("战斗值 " + bean.getPower());
    try{
    hold.itemImage.setImageBitmap(BitmapFactory.decodeStream(context
        .getAssets().open(bean.getImg())));
    }catch (Exception e){

    }
    hold.lay.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(bossClickListener!=null){
          bossClickListener.doClick(bean);
        }
      }
    });

    return view;
  }

  public static class Holder {
    public TextView itemName, itemPrice;
    public ImageView itemImage;
    public View lay;
  }

  public interface BossClickListener{
  void doClick(ToolBean bean);
  }

}
