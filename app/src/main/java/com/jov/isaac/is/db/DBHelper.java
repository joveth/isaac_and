package com.jov.isaac.is.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jov.isaac.is.util.CommonUtil;

public class DBHelper extends SQLiteOpenHelper {
  /**
   * table
   */
  public static String TABLE_NAME_ISAAC = "tb_isaac";
  public static String TABLE_NAME_BOSS = "tb_isaac_boss";
  public static String TABLE_NAME_SMALL = "tb_isaac_small";
  public static String TABLE_NAME_OTHER = "tb_other";
  private static final String DB_NAME = "isaac.db";
  /**
   * version
   */
  private static final int VERSION = 1;
  /**
   * SQL for create table
   */
  private static final String CREATE_TABLE_ISAAC = "create table IF NOT EXISTS "
      + TABLE_NAME_ISAAC
      + "(id integer primary key autoincrement,image varchar(20),name varchar(60),enname varchar(60),content varchar(500),"
      + "power varchar(20),unlock varchar(200),type char(1))";

  private static final String CREATE_TABLE_BOSS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_BOSS + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, image varchar(20),name varchar(60),enname varchar(60) ,content varchar(1200),score varchar(5))";
  private static final String CREATE_TABLE_SMALL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SMALL + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, image varchar(20),name varchar(60),enname varchar(60) ,content varchar(500))";
  private static final String CREATE_TABLE_OTHER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_OTHER + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, image varchar(20),name varchar(60),enname varchar(60) ,content varchar(1000),type char(1))";

  /**
   * SQL for drop table
   */
  private static final String DROP_TABLE_BLOG = "DROP TABLE IF EXISTS "
      + TABLE_NAME_ISAAC;
  private SQLiteDatabase db;
  private Context context;

  public DBHelper(Context context) {
    super(context, DB_NAME, null, VERSION);
    this.context = context;
  }

  public DBHelper(Context context, String name, int version) {
    super(context, name, null, version);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE_ISAAC);
    db.execSQL(CREATE_TABLE_BOSS);
    db.execSQL(CREATE_TABLE_SMALL);
    db.execSQL(CREATE_TABLE_OTHER);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(DROP_TABLE_BLOG);
    onCreate(db);
  }

  public List<ToolBean> getBeans(String type) {
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    Cursor cursor = null;
    List<ToolBean> list = new ArrayList<ToolBean>();
    String sql = "";
    sql = "select * from " + TABLE_NAME_ISAAC
        + " where type=? " ;
    cursor = db.rawQuery(sql, new String[]{type});
    ToolBean obj = null;
    while (cursor != null && cursor.moveToNext()) {
      obj = new ToolBean();
      obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
      obj.setImg(cursor.getString(cursor.getColumnIndex("image")));
      obj.setName(cursor.getString(cursor.getColumnIndex("name")));
      obj.setEnName(cursor.getString(cursor.getColumnIndex("enname")));
      obj.setDesc(cursor.getString(cursor.getColumnIndex("content")));
      obj.setPower(cursor.getString(cursor.getColumnIndex("power")));
      obj.setType(cursor.getString(cursor.getColumnIndex("type")));
      obj.setUnlock(cursor.getString(cursor.getColumnIndex("unlock")));
      list.add(obj);
    }
    cursor.close();
    db.close();
    return list;
  }

  public void initInsert() throws IOException {

    InputStreamReader reader = new InputStreamReader(context.getAssets()
        .open("atore.db"));
    BufferedReader br = new BufferedReader(reader);
    String s1 = "";
    db = getReadableDatabase();
    db.beginTransaction();
    while ((s1 = br.readLine()) != null) {
      if (CommonUtil.isEmpty(s1)) {
        continue;
      }
      try {
        Log.d("sql=",s1);
        db.execSQL(s1);
      } catch (Exception e) {
        continue;
      }
    }
    db.setTransactionSuccessful();
    db.endTransaction();
    br.close();
    reader.close();
    db.close();

  }

  public List<ToolBean> getBeans() {
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    Cursor cursor = null;
    List<ToolBean> list = new ArrayList<ToolBean>();
    String sql = "";
    sql = "select * from " + TABLE_NAME_ISAAC ;
    cursor = db.rawQuery(sql, null);
    ToolBean obj = null;
    while (cursor != null && cursor.moveToNext()) {
      obj = new ToolBean();
      obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
      obj.setImg(cursor.getString(cursor.getColumnIndex("image")));
      obj.setName(cursor.getString(cursor.getColumnIndex("name")));
      obj.setEnName(cursor.getString(cursor.getColumnIndex("enname")));
      obj.setDesc(cursor.getString(cursor.getColumnIndex("content")));
      obj.setPower(cursor.getString(cursor.getColumnIndex("power")));
      obj.setType(cursor.getString(cursor.getColumnIndex("type")));
      obj.setUnlock(cursor.getString(cursor.getColumnIndex("unlock")));
      list.add(obj);
    }
    cursor.close();
    db.close();
    return list;
  }

  public List<ToolBean> getBeansByKeywords(String keyWords) {
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    Cursor cursor = null;
    List<ToolBean> list = new ArrayList<ToolBean>();
    String sql = "";
    sql = "select * from " + TABLE_NAME_ISAAC + " where enname like  '%"
        + keyWords + "%' or name like '%" + keyWords
        + "%' or content like '%" + keyWords + "%' or power like '%"
        + keyWords + "%' or unlock like  '%" + keyWords + "%' ";

    cursor = db.rawQuery(sql, null);
    ToolBean obj = null;
    while (cursor != null && cursor.moveToNext()) {
      obj = new ToolBean();
      obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
      obj.setImg(cursor.getString(cursor.getColumnIndex("image")));
      obj.setName(cursor.getString(cursor.getColumnIndex("name")));
      obj.setEnName(cursor.getString(cursor.getColumnIndex("enname")));
      obj.setDesc(cursor.getString(cursor.getColumnIndex("content")));
      obj.setPower(cursor.getString(cursor.getColumnIndex("power")));
      obj.setType(cursor.getString(cursor.getColumnIndex("type")));
      obj.setUnlock(cursor.getString(cursor.getColumnIndex("unlock")));
      list.add(obj);
    }
    cursor.close();
    db.close();
    return list;
  }

  public ToolBean getBeanByid(int id) {
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    Cursor cursor = null;
    cursor = db.rawQuery("select * from " + TABLE_NAME_ISAAC
        + " where id=? ", new String[]{String.valueOf(id)});
    ToolBean obj = null;
    while (cursor != null && cursor.moveToNext()) {
      obj = new ToolBean();
      obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
      obj.setImg(cursor.getString(cursor.getColumnIndex("image")));
      obj.setName(cursor.getString(cursor.getColumnIndex("name")));
      obj.setEnName(cursor.getString(cursor.getColumnIndex("enname")));
      obj.setDesc(cursor.getString(cursor.getColumnIndex("content")));
      obj.setPower(cursor.getString(cursor.getColumnIndex("power")));
      obj.setType(cursor.getString(cursor.getColumnIndex("type")));
      obj.setUnlock(cursor.getString(cursor.getColumnIndex("unlock")));
    }
    cursor.close();
    db.close();
    return obj;
  }

  public int getTotalCount(String type) {
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    String sql = "select count(*) from " + TABLE_NAME_ISAAC
        + " where type=? ";
    Cursor rec = db.rawQuery(sql, new String[]{type});
    rec.moveToLast();
    long recSize = rec.getLong(0);
    rec.close();
    db.close();
    return (int) recSize;
  }

  public int getTotalCount() {
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    String sql = "select count(*) from " + TABLE_NAME_ISAAC;
    Cursor rec = db.rawQuery(sql, null);
    rec.moveToLast();
    long recSize = rec.getLong(0);
    rec.close();
    db.close();
    return (int) recSize;
  }

  public void insertBean(ToolBean bean) {

    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    String sql = "insert into " + TABLE_NAME_ISAAC
        + "  values(null,?,?,?,?,?,?,?)";
    db.execSQL(
        sql,
        new String[]{bean.getImg(), bean.getName(), bean.getEnName(),
            bean.getDesc(), bean.getPower(), bean.getUnlock(),
            bean.getType()});
    db.close();
  }

  public void deleteById(int id) {
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    String sql = "delete from  " + TABLE_NAME_ISAAC + "  where id=?";
    db.execSQL(sql, new String[]{String.valueOf(id)});
    db.close();
  }

  public List<ToolBean> getBoss(){
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    Cursor cursor = null;
    List<ToolBean> list = new ArrayList<ToolBean>();
    String sql = "";
    sql = "select * from " + TABLE_NAME_BOSS ;
    cursor = db.rawQuery(sql, null);
    ToolBean obj = null;
    while (cursor != null && cursor.moveToNext()) {
      obj = new ToolBean();
      obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
      obj.setImg(cursor.getString(cursor.getColumnIndex("image")));
      obj.setName(cursor.getString(cursor.getColumnIndex("name")));
      obj.setEnName(cursor.getString(cursor.getColumnIndex("enname")));
      obj.setDesc(cursor.getString(cursor.getColumnIndex("content")));
      obj.setPower(cursor.getString(cursor.getColumnIndex("score")));
      list.add(obj);
    }
    cursor.close();
    db.close();
    return list;
  }
  public List<ToolBean> getSmall(){
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    Cursor cursor = null;
    List<ToolBean> list = new ArrayList<ToolBean>();
    String sql = "";
    sql = "select * from " + TABLE_NAME_SMALL ;
    cursor = db.rawQuery(sql, null);
    ToolBean obj = null;
    while (cursor != null && cursor.moveToNext()) {
      obj = new ToolBean();
      obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
      obj.setImg(cursor.getString(cursor.getColumnIndex("image")));
      obj.setName(cursor.getString(cursor.getColumnIndex("name")));
      obj.setEnName(cursor.getString(cursor.getColumnIndex("enname")));
      obj.setDesc(cursor.getString(cursor.getColumnIndex("content")));
      list.add(obj);
    }
    cursor.close();
    db.close();
    return list;
  }
  public List<ToolBean> getOther(String type){
    if (db == null || !db.isOpen()) {
      db = this.getReadableDatabase();
    }
    Cursor cursor = null;
    List<ToolBean> list = new ArrayList<ToolBean>();
    String sql = "";
    sql = "select * from " + TABLE_NAME_OTHER +" where type="+type;
    cursor = db.rawQuery(sql, null);
    ToolBean obj = null;
    while (cursor != null && cursor.moveToNext()) {
      obj = new ToolBean();
      obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
      obj.setImg(cursor.getString(cursor.getColumnIndex("image")));
      obj.setName(cursor.getString(cursor.getColumnIndex("name")));
      obj.setEnName(cursor.getString(cursor.getColumnIndex("enname")));
      obj.setDesc(cursor.getString(cursor.getColumnIndex("content")));
      list.add(obj);
    }
    cursor.close();
    db.close();
    return list;
  }
}
