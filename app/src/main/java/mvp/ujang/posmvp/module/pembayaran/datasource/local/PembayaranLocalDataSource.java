package mvp.ujang.posmvp.module.pembayaran.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.pembayaran.datasource.PembayaranDataSource;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;
import mvp.ujang.posmvp.utils.Connection;

public class PembayaranLocalDataSource implements PembayaranDataSource {

    private static PembayaranLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private PembayaranLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static PembayaranLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new PembayaranLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadTotalPembayaran(@NonNull Callback.LoadCallback<Pembayaran> callback) {
        ArrayList<Pembayaran> list = new ArrayList<Pembayaran>();
        String query = "SELECT SUM(K.JUMLAH*HARGA_JUAL_BARANG) AS total_pembayaran FROM KERANJANG K JOIN BARANG B ON K.KD_BARANG = B.KD_BARANG";
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
                        Pembayaran item = new Pembayaran();
                        item.setTotalPembayaran(cursor.getString(cursor.getColumnIndexOrThrow("total_pembayaran")));
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
    public void addPembayaran(@NonNull Pembayaran pembayaran, @NonNull Callback.AddCallback<Pembayaran> callback) {
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        ContentValues values = new ContentValues();
        values.put("kd_transaksi", pembayaran.getKdTransaksi());
        values.put("tgl_transaksi", pembayaran.getTglTransaksi());
        values.put("total_pembayaran", pembayaran.getTotalPembayaran());
        values.put("transaksi_oleh", pembayaran.getTransaksiOleh());
        values.put("tunai",pembayaran.getTunai());
        long returnValue = database.insert("Transaksi", null, values);

        if (returnValue!=0)
            callback.onAddSuccess(pembayaran);
        else
            callback.onAddFailed();
    }
}


