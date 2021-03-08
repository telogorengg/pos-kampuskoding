package mvp.ujang.posmvp.module.keranjang.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.keranjang.datasource.KeranjangDataSource;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.utils.Connection;

public class KeranjangLocalDataSource implements KeranjangDataSource {

    private static KeranjangLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private KeranjangLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static KeranjangLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new KeranjangLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadKeranjang(@NonNull Callback.LoadCallback<Keranjang> callback) {
        ArrayList<Keranjang> list = new ArrayList<Keranjang>();
        String query = "SELECT B.*,K.ID_KERANJANG,K.JUMLAH FROM BARANG B JOIN KERANJANG K ON B.KD_BARANG = K.KD_BARANG";
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        try {
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Keranjang item = new Keranjang(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_keranjang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("jumlah")),
                                cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("harga_jual_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("stok_barang"))
                        );
                        list.add(item);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {

        }

        connection.close();
    }

    @Override
    public void addKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.AddCallback<Keranjang> callback) {
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        long returnValue = 0;
        String query = "SELECT 1 FROM KERANJANG K WHERE K.KD_BARANG = '"+keranjang.getKdBarang()+"'";
        String[] args = new String[]{keranjang.getKdBarang()};
        Cursor cursor;
        cursor = database.rawQuery(query,null);

        if (cursor.getCount() > 0 ){
            String queryUpdate = "update keranjang set jumlah = "+keranjang.getJumlah()+" WHERE kd_barang = '"+keranjang.getKdBarang()+"'";
            database.execSQL(queryUpdate);
        }else{
            ContentValues values = new ContentValues();
            values.put("kd_barang", keranjang.getKdBarang());
            values.put("jumlah", Integer.parseInt(keranjang.getJumlah()));
            Log.d("insertValue",keranjang.toString());
            returnValue = database.insert("Keranjang", null, values);
        }

        if (returnValue!=0)
            callback.onAddSuccess(keranjang);
        else
            callback.onAddFailed();

    }

    @Override
    public void editKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.EditCallback<Keranjang> callback) {
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        String queryUpdate = "update keranjang set jumlah = "+keranjang.getJumlah()+" WHERE kd_barang = '"+keranjang.getKdBarang()+"'";
        database.execSQL(queryUpdate);
        callback.onEditSuccess(keranjang);
    }

    @Override
    public void deleteKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.DeleteCallback<Keranjang> callback) {

    }
}


