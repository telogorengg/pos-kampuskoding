package mvp.ujang.posmvp.module.transaksi.datasource.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksi.datasource.TransaksiDataSource;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;
import mvp.ujang.posmvp.utils.Connection;

public class TransaksiLocalDataSource implements TransaksiDataSource {

    private static TransaksiLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private TransaksiLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static TransaksiLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new TransaksiLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadTransaksi(@NonNull Callback.LoadCallback<Transaksi> callback) {
        ArrayList<Transaksi> list = new ArrayList<>();
        String query = "SELECT TGL_TRANSAKSI,KD_TRANSAKSI,TOTAL_PEMBAYARAN,DATE(TGL_TRANSAKSI) AS tanggal,TUNAI,TIME(TGL_TRANSAKSI) AS waktu,\n" +
                "(SELECT COUNT()+1 FROM (\n" +
                "    SELECT DISTINCT DATE(TGL_TRANSAKSI) FROM TRANSAKSI AS T WHERE DATE(TGL_TRANSAKSI) < DATE(TRANSAKSI.TGL_TRANSAKSI))\n" +
                ") AS grouping_date\n" +
                "FROM TRANSAKSI\n" +
                "ORDER BY TGL_TRANSAKSI DESC\n";
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        try {
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            Log.d("success_struk","success_struk"+cursor.getCount());
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Transaksi item = new Transaksi();
                        item.setKdTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("kd_transaksi")));
                        item.setGroupingDate(cursor.getString(cursor.getColumnIndexOrThrow("grouping_date")));
                        item.setTglTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("tgl_transaksi")));
                        item.setTanggal(cursor.getString(cursor.getColumnIndexOrThrow("tanggal")));
                        item.setWaktu(cursor.getString(cursor.getColumnIndexOrThrow("waktu")));
                        item.setTotalPembayaran(cursor.getString(cursor.getColumnIndexOrThrow("total_pembayaran")));
                        item.setTunai(cursor.getString(cursor.getColumnIndexOrThrow("tunai")));
                        list.add(item);

                        Log.d("data_struk",item.toString());

                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {
            Log.d("error_struk","error_struk",e);
        }

        connection.close();
    }
}


