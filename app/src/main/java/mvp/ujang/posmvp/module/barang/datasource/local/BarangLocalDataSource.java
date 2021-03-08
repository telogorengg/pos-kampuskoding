package mvp.ujang.posmvp.module.barang.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.barang.datasource.BarangDataSource;
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.utils.Connection;

public class BarangLocalDataSource implements BarangDataSource {

    private static BarangLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private BarangLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static BarangLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new BarangLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadBarang(@NonNull Callback.LoadCallback<Barang> loadProdukCallback) {
        ArrayList<Barang> list = new ArrayList<Barang>();
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();
        try {
            list.clear();
            Cursor cursor;
            cursor = database.query("Barang", new String[] { "satuan","gambar_barang","stok_barang","harga_jual_barang","id_kategori","kd_barang","deskripsi_barang","nama_barang"},null,null, null, null, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Barang item = new Barang(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("harga_jual_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("satuan")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("deskripsi_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("stok_barang"))
                        );
                        list.add(item);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            Log.d("loadData",list.toString());
            loadProdukCallback.onLoadSuccess(list);
        } catch (Exception e) {

        }

        connection.close();
    }

    @Override
    public void searchBarang(Barang parameter, @NonNull Callback.LoadCallback<Barang> loadProdukCallback) {
        String query =  "(id_kategori = '"+parameter.getIdKategori()+ "' or "+parameter.getIdKategori()+" = 0) "+
                " and (nama_barang like '%"+parameter.getNamaBarang()+  "%' or '"+parameter.getNamaBarang()+"' isnull) ";

        ArrayList<Barang> list = new ArrayList<Barang>();
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();
        try {
            list.clear();
            Cursor cursor;
            cursor = database.query("Barang", new String[] { "satuan","gambar_barang","stok_barang","harga_jual_barang","id_kategori","kd_barang","deskripsi_barang","nama_barang"},query,null, null, null, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Barang item = new Barang(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("harga_jual_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("satuan")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("deskripsi_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("stok_barang"))
                        );
                        list.add(item);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            loadProdukCallback.onLoadSuccess(list);
        } catch (Exception e) {

        }

        connection.close();
    }

    @Override
    public void addBarang(@NonNull Barang barang, @NonNull Callback.AddCallback<Barang> addProdukCallback) {
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        ContentValues values = new ContentValues();
        values.put("kd_barang", barang.getKdBarang());
        values.put("nama_barang", barang.getNamaBarang());
        values.put("harga_jual_barang", barang.getHargaJualBarang());
        values.put("satuan", barang.getSatuan());
        values.put("stok_barang", barang.getStokBarang());
        values.put("deskripsi_barang", barang.getDeskripsiBarang());
        values.put("gambar_barang", barang.getGambarBarang());
        values.put("id_kategori", barang.getIdKategori());
        values.put("harga_beli_barang",barang.getHargaBeliBarang());
        long returnValue = database.insert("Barang", null, values);

        if (returnValue!=0)
            addProdukCallback.onAddSuccess(barang);
        else
            addProdukCallback.onAddFailed();
    }

    @Override
    public void editBarang(@NonNull Barang barang, @NonNull Callback.EditCallback<Barang> editProdukCallback) {

    }

    @Override
    public void deleteBarang(@NonNull Barang barang, @NonNull Callback.DeleteCallback<Barang> deleteProdukCallback) {

    }
}


