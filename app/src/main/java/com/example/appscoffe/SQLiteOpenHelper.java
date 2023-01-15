package com.example.appscoffe;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    //versi database
    private static final int DATABASE_VERSION = 1;
    //nama database
    private static final String DATABASE_NAME = "coffe_db";

    public SQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //membuat tabel
        db.execSQL(Order.CREATE_TABLE);
        //masukkan data awal sebagai petunjuk penggunaan aplikasi ini
        String catatan = "Tap-lama utk edit/hapus catatan. Tap plus utk membuat catatan baru";
        db.execSQL("INSERT INTO " + Order.NAMA_TABEL + "(" + Order.KOLOM_COFFE +") VALUES('" + catatan + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //proses upgrade database, khususnya jika ada perubahan struktur tabel:
        //1. drop tabel yang lama jika ada (semua data dihapus juga)
        db.execSQL("DROP TABLE IF EXISTS " + Order.NAMA_TABEL);
        //2. Buat tabel baru
        onCreate(db);
    }
    //insert catatan
    public long insertOrder(String order){
        //buka database utk ditulisi
        SQLiteDatabase db = this.getWritableDatabase();
        //proses menulisi ke tabel, id dan timestamp akan di-isi secara otomatis
        ContentValues values = new ContentValues();
        values.put(Order.KOLOM_COFFE, order);
        //tambahkan catatan ke tabel
        long id = db.insert(Order.NAMA_TABEL, null, values);
        //tutup koneksi ke database
        db.close();
        //return id yg baru ditambahkan
        return id;
    }
    //membaca satu catatan
    public Order getOrder(long id){
        //buka database utk dibaca saja
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Order.NAMA_TABEL,
                new String[]{Order.KOLOM_ID, Order.KOLOM_COFFE, Order.KOLOM_TIMESTAMP},
                Order.KOLOM_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        //persiapkan object catatan
        @SuppressLint("Range") Order order = new Order(
                cursor.getInt(cursor.getColumnIndex(Order.KOLOM_ID)),
                cursor.getString(cursor.getColumnIndex(Order.KOLOM_COFFE)),
                cursor.getString(cursor.getColumnIndex(Order.KOLOM_TIMESTAMP))
        );
        cursor.close();
        return order;
    }
    //ambil semua catatan
    @SuppressLint("Range")
    public List<Order> getSemuaOrder(){
        List<Order> catatanList = new ArrayList<>();
        //query SELECT semua data
        String selectQuery = "SELECT * FROM " + Order.NAMA_TABEL + " ORDER BY "
                + Order.KOLOM_TIMESTAMP + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping semua baris dan tambahkan ke list
        if(cursor.moveToFirst()){
            do{
                Order order = new Order();
                order.setId(cursor.getInt(cursor.getColumnIndex(Order.KOLOM_ID)));
                order.setOrder(cursor.getString(cursor.getColumnIndex(Order.KOLOM_COFFE)));
                order.setTimestamp(cursor.getString(cursor.getColumnIndex(Order.KOLOM_TIMESTAMP)));
                catatanList.add(order);
            } while(cursor.moveToNext());
        }
        db.close();
        return catatanList;
    }
    //mengambil jumlah catatan
    public int getJumlahOrder(){
        String countQuery = "SELECT * FROM " + Order.NAMA_TABEL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int jml = cursor.getCount();
        cursor.close();
        return jml;
    }
    //update catatan
    public int updateOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Order.KOLOM_COFFE, order.getCoffe());
        //memperbarui baris catatan
        return db.update(Order.NAMA_TABEL, values, Order.KOLOM_ID + "=?",
                new String[]{String.valueOf(order.getId())});
    }
    //menghapus catatan
    public void deleteOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Order.NAMA_TABEL, Order.KOLOM_ID + "=?",
                new String[]{String.valueOf(Order.KOLOM_TIMESTAMP)});
        db.close();
    }
}
