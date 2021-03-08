package mvp.ujang.posmvp.module.refund.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.datasource.RefundDetailDataSource;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;
import mvp.ujang.posmvp.utils.Connection;

public class RefundDetailLocalDataSource implements RefundDetailDataSource {

    private static RefundDetailLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private RefundDetailLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static RefundDetailLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new RefundDetailLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadStruk(RefundDetail strukDetail, @NonNull Callback.LoadCallback<RefundDetail> callback) {
        ArrayList<RefundDetail> list = new ArrayList<>();
        String query = "select T.kd_transaksi,T.tgl_transaksi,T.total_pembayaran,T.transaksi_oleh,D.kd_barang,D.jumlah,D.harga_barang,B.nama_barang\n" +
                "from Transaksi T join Detail_Transaksi D on T.kd_transaksi = D.kd_transaksi join Barang B on D.kd_barang = B.kd_barang " +
                "WHERE T.KD_TRANSAKSI = '"+strukDetail.getKdTransaksi()+"'" +
                "\n";
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
                        RefundDetail item = new RefundDetail();
                        item.setKdTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("kd_transaksi")));
                        item.setKdBarang(cursor.getString(cursor.getColumnIndexOrThrow("kd_barang")));
                        item.setTglTransaksi(cursor.getString(cursor.getColumnIndexOrThrow("tgl_transaksi")));
                        item.setJumlah(cursor.getString(cursor.getColumnIndexOrThrow("jumlah")));
                        item.setHargaBarang(cursor.getString(cursor.getColumnIndexOrThrow("harga_barang")));
                        item.setNamaBarang(cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")));
                        item.setTotalPembayaran(cursor.getString(cursor.getColumnIndexOrThrow("total_pembayaran")));
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

    @Override
    public void refundBarang(RefundDetail strukDetail, @NonNull Callback.AddCallback<RefundDetail> callback) {
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        ContentValues values = new ContentValues();
        values.put("kd_barang", strukDetail.getKdBarang());
        values.put("kd_transaksi",strukDetail.getKdTransaksi());
        values.put("jumlah",strukDetail.getJumlah());
        values.put("harga_barang",strukDetail.getHargaBarang());
        long returnValue = database.insert("Refund", null, values);

        if (returnValue!=0)
            callback.onAddSuccess(strukDetail);
        else
            callback.onAddFailed();
    }

    @Override
    public void loadTotalRefund(RefundDetail strukDetail, @NonNull Callback.LoadCallback<RefundDetail> callback) {
        ArrayList<RefundDetail> list = new ArrayList<>();
        String query = "select ifnull(sum(jumlah * harga_barang),0) as total_refund from Refund WHERE KD_TRANSAKSI = '"+strukDetail.getKdTransaksi()+"'";
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
                        RefundDetail item = new RefundDetail();
                        item.setTotalRefund(cursor.getString(cursor.getColumnIndexOrThrow("total_refund")));
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


