package mvp.ujang.posmvp.module.kategori.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.kategori.datasource.KategoriDataSource;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.utils.Connection;

public class KategoriLocalDataSource implements KategoriDataSource {

    private static KategoriLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private KategoriLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static KategoriLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new KategoriLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadKategori(@NonNull Callback.LoadCallback<Kategori> callback) {
        ArrayList<Kategori> list = new ArrayList<Kategori>();
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        String sql = "SELECT ifnull(COUNT(b.nama_barang),0) AS total_item,ifnull(SUM(B.STOK_BARANG),0) as total_barang,K.NAMA_KATEGORI,K.ID_KATEGORI,K.GAMBAR_KATEGORI FROM KATEGORI K left JOIN BARANG B ON B.ID_KATEGORI = K.ID_KATEGORI GROUP BY K.ID_KATEGORI";

        try {
            list.clear();
            Cursor cursor;
            cursor = database.rawQuery(sql, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Kategori item = new Kategori(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("total_item")),
                                cursor.getString(cursor.getColumnIndexOrThrow("total_barang"))
                        );
                        list.add(item);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {
            Log.d("ErrorMessage",e.toString());
        }

        connection.close();
    }

    @Override
    public void searchKategori(Kategori parameter, @NonNull Callback.LoadCallback<Kategori> callback) {

    }

    @Override
    public void addKategori(@NonNull Kategori kategori, @NonNull Callback.AddCallback<Kategori> callback) {
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        ContentValues values = new ContentValues();
        values.put("nama_kategori", kategori.getNamaKategori());
        values.put("gambar_kategori",kategori.getGambarKategori());
        long returnValue = database.insert("Kategori", null, values);

        if (returnValue!=0)
            callback.onAddSuccess(kategori);
        else
            callback.onAddFailed();
    }

    @Override
    public void editKategori(@NonNull Kategori kategori, @NonNull Callback.EditCallback<Kategori> callback) {

    }

    @Override
    public void deleteKategori(@NonNull Kategori kategori, @NonNull Callback.DeleteCallback<Kategori> callback) {

    }
}


