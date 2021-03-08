package mvp.ujang.posmvp.module.penjualan.datasource.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.penjualan.datasource.PenjualanDataSource;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;
import mvp.ujang.posmvp.utils.Connection;

public class PenjualanLocalDataSource implements PenjualanDataSource {

    private static PenjualanLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private PenjualanLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static PenjualanLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new PenjualanLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }




    @Override
    public void loadPenjualan(@NonNull Callback.LoadCallback<Penjualan> callback) {
        ArrayList<Penjualan> list = new ArrayList<Penjualan>();
        String query = "SELECT B.*,ifnull(K.JUMLAH,'0') AS jumlah  FROM BARANG B LEFT JOIN KERANJANG K ON B.KD_BARANG  = K.KD_BARANG";
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();
        try {
            list.clear();
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Penjualan item = new Penjualan(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("harga_jual_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("satuan")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("deskripsi_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("stok_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("jumlah"))
                        );
                        list.add(item);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            Log.d("loadData",list.toString());
            callback.onLoadSuccess(list);
        } catch (Exception e) {

        }

        connection.close();
    }

    @Override
    public void searchPenjualan(Penjualan parameter, @NonNull Callback.LoadCallback<Penjualan> callback) {
        String query = "SELECT B.*,ifnull(K.JUMLAH,'0') AS jumlah  FROM BARANG B LEFT JOIN KERANJANG K ON B.KD_BARANG  = K.KD_BARANG";
        query +=  " where (B.id_kategori = '"+parameter.getIdKategori()+ "' or "+parameter.getIdKategori()+" = 0) "+
                " and (B.nama_barang like '%"+parameter.getNamaBarang()+  "%' or '"+parameter.getNamaBarang()+"' isnull) ";

        ArrayList<Penjualan> list = new ArrayList<>();
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();
        try {
            list.clear();
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Penjualan item = new Penjualan(
                                cursor.getString(cursor.getColumnIndexOrThrow("id_kategori")),
                                cursor.getString(cursor.getColumnIndexOrThrow("harga_jual_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("satuan")),
                                cursor.getString(cursor.getColumnIndexOrThrow("gambar_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("deskripsi_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("stok_barang")),
                                cursor.getString(cursor.getColumnIndexOrThrow("jumlah"))
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
}


