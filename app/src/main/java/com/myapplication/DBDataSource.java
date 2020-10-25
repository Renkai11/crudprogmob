package com.myapplication;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAMA, DBHelper.COLUMN_ALAMAT,DBHelper.COLUMN_NIM};

    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Mahasiswa createMahasiswa(String nama, String alamat, String nim) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA, nama);
        values.put(DBHelper.COLUMN_ALAMAT, alamat);
        values.put(DBHelper.COLUMN_NIM, nim);

        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);


        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Mahasiswa newMahasiswa = cursorToMahasiswa(cursor);

        cursor.close();

        return newMahasiswa;
    }

    private Mahasiswa cursorToMahasiswa(Cursor cursor)
    {
        Mahasiswa mahasiswa = new Mahasiswa();

        mahasiswa.setId(cursor.getLong(0));
        mahasiswa.setNama_mahasiswa(cursor.getString(1));
        mahasiswa.setAlamat_mahasiswa(cursor.getString(2));
        mahasiswa.setNIM_mahasiswa(cursor.getString(3));

        return mahasiswa;
    }


    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<Mahasiswa>();


        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mahasiswa mahasiswa = cursorToMahasiswa(cursor);
            daftarMahasiswa.add(mahasiswa);
            cursor.moveToNext();
        }

        cursor.close();
        return daftarMahasiswa;
    }

    public Mahasiswa getMahasiswa(long id)
    {
       Mahasiswa mahasiswa= new Mahasiswa();
       Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id ="+id, null, null, null, null);
       cursor.moveToFirst();
       mahasiswa = cursorToMahasiswa(cursor);
       cursor.close();
       return mahasiswa;
    }

    public void updateMahasiswa(Mahasiswa m)
    {
        String strFilter = "_id=" + m.getId();
        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_NAMA, m.getNama_mahasiswa());
        args.put(DBHelper.COLUMN_ALAMAT, m.getAlamat_mahasiswa());
        args.put(DBHelper.COLUMN_NIM, m.getNIM_mahasiswa() );
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public void deleteMahasiswa(long id)
    {
        String strFilter = "_id=" + id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}