package com.juanapps.shopwme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "productName";
    private static final String COLUMN_PRODUCTPRICE = "productPrice";
    private static final String COLUMN_PRODUCTSTORE = "productStore";
    private static final String COLUMN_PRODUCTDATE = "productDate";
    private static final String COLUMN_PRODUCTLOCATION = "productLocation";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT NOT NULL, " +
                COLUMN_PRODUCTPRICE + " TEXT, " +
                COLUMN_PRODUCTSTORE + " TEXT NOT NULL, " +
                COLUMN_PRODUCTDATE + " DATE NOT NULL, " +
                COLUMN_PRODUCTLOCATION + " TEXT NOT NULL " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productName());
        values.put(COLUMN_PRODUCTPRICE, product.get_productPrice());
        values.put(COLUMN_PRODUCTSTORE, product.get_productStore());
        values.put(COLUMN_PRODUCTDATE, product.get_productDate());
        values.put(COLUMN_PRODUCTLOCATION, product.get_productLocation());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    //Keep products in an array
    public ArrayList<Products> getProductsForStore(String store){

        ArrayList<Products> productList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE productStore = '" + store + "'";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results

        if(c.moveToFirst()){
            do{
                Products p = new Products();
                p.set_productName(c.getString(c.getColumnIndex("productName")));
                p.set_productPrice(c.getString(c.getColumnIndex("productPrice")));
                p.set_productStore(c.getString(c.getColumnIndex("productStore")));
                p.set_productDate(c.getString(c.getColumnIndex("productDate")));
                p.set_productLocation(c.getString(c.getColumnIndex("productLocation")));
                productList.add(p);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return (productList);
    }

    public void deletetable(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

}
