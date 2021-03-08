package mvp.ujang.posmvp.module.dashboard.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.datasource.DashboardDataSource;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.utils.Connection;

public class DashboardLocalDataSource implements DashboardDataSource {

    private static DashboardLocalDataSource sInstance = null;
    private Context context;
    private Connection connection;
    private SQLiteDatabase database;

    private DashboardLocalDataSource(@NonNull Context context) {
        this.context = context;
    }

    public static DashboardLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new DashboardLocalDataSource(context);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadInfoPenjualan(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        String tanggal = dashboardDetail.getTanggal();
        ArrayList<DashboardDetail> list = new ArrayList<>();

        String where = "";
        if (dashboardDetail.getMode().equals("Harian"))
            where = "where date(b.tgl_transaksi) = '"+dashboardDetail.getTanggal()+"'";
        else if (dashboardDetail.getMode().equals("Bulanan"))
            where = "where strftime('%m', b.tgl_transaksi) = substr('00'||"+dashboardDetail.getBulan()+",-2)";
        else if (dashboardDetail.getMode().equals("Tahunan"))
            where = "where strftime('%Y', b.tgl_transaksi) = '"+dashboardDetail.getTahun()+"'";

        String query = "select a.*,b.*,c.*,d.*,e.*,f.*,g.* from \n" +
                "(select \n" +
                "ifnull(sum((b.total_pembayaran)),0) + \n" +
                "ifnull(sum((a.harga_barang * a.jumlah )),0) as gross_sales from transaksi b left join refund a on a.kd_transaksi = b.kd_transaksi "+where+") a,\n" +
                "(select ifnull(sum((harga_barang * jumlah )),0) as refunds from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  "+where+") b,\n" +
                "(select ifnull(sum((c.harga_jual_barang*a.jumlah)),0)-ifnull(sum((c.harga_beli_barang*a.jumlah)),0) as gross_profit \n" +
                "from transaksi b join detail_transaksi a on a.kd_transaksi = b.kd_transaksi\n" +
                "join barang c on a.kd_barang = c.kd_barang "+where+") c,\n" +
                "(select ifnull(sum((b.total_pembayaran)),0) as net_sales from transaksi b "+where+") d,\n" +
                "(select ifnull(count(*),0) as total_sales from transaksi b "+where+") e,\n" +
                "(select ifnull(sum(jumlah),0) as total_item from detail_transaksi a join transaksi b on a.kd_transaksi = b.kd_transaksi  "+where+") f,\n" +
                "(select ifnull(count(distinct a.kd_transaksi),0) as total_refund from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  "+where+")g\n";


//        String query = "select a.*,b.*,c.*,d.*,e.*,f.*,g.* from \n" +
//                "(select ifnull(sum(total_pembayaran),0) as gross_sales from transaksi where date(tgl_transaksi) = '"+tanggal+"') a,\n" +
//                "(select ifnull(sum((harga_barang * jumlah )),0) as refunds from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  where date(b.tgl_transaksi) = '"+tanggal+"') b,\n" +
//                "(select ifnull(sum((total_pembayaran)),0) as gross_profit from transaksi where date(tgl_transaksi) = '"+tanggal+"') c,\n" +
//                "(select ifnull(sum((total_pembayaran)),0) as net_sales from transaksi where date(tgl_transaksi) = '"+tanggal+"') d,\n" +
//                "(select ifnull(count(*),0) as total_sales from transaksi where date(tgl_transaksi) = '"+tanggal+"') e,\n" +
//                "(select ifnull(sum(jumlah),0) as total_item from detail_transaksi a join transaksi b on a.kd_transaksi = b.kd_transaksi  where date(b.tgl_transaksi) = '"+tanggal+"') f,\n" +
//                "(select ifnull(count(distinct a.kd_transaksi),0) as total_refund from refund a join transaksi b on a.kd_transaksi = b.kd_transaksi  where date(b.tgl_transaksi) = '"+tanggal+"')g\n";
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
                        DashboardDetail item = new DashboardDetail();
                        item.setGrossProfit(cursor.getString(cursor.getColumnIndexOrThrow("gross_profit")));
                        item.setGrossSales(cursor.getString(cursor.getColumnIndexOrThrow("gross_sales")));
                        item.setNetSales(cursor.getString(cursor.getColumnIndexOrThrow("net_sales")));
                        item.setRefunds(cursor.getString(cursor.getColumnIndexOrThrow("refunds")));
                        item.setTotalRefund(cursor.getString(cursor.getColumnIndexOrThrow("total_refund")));
                        item.setTotalItem(cursor.getString(cursor.getColumnIndexOrThrow("total_item")));
                        item.setTotalSales(cursor.getString(cursor.getColumnIndexOrThrow("total_sales")));
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
    public void loadInfoGross(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        String tanggal = dashboardDetail.getTanggal();
        String where = "";
        String query = "";

        if (dashboardDetail.getMode().equals("Harian")){
            query = "WITH DATEDATA(N) AS(\n" +
                    "SELECT 1\n" +
                    "UNION ALL\n" +
                    "SELECT N+1\n" +
                    "FROM DATEDATA\n" +
                    "WHERE N < 7)\n" +
                    "SELECT substr('00'||N,-2) as bulan,IFNULL((b.GROSS_SALES),0) as gross_sales,tgl_transaksi\n" +
                    "FROM DATEDATA  A left join (SELECT strftime('%w',tgl_transaksi) +1 as bulan,\n" +
                    "\tIFNULL(SUM(TOTAL_PEMBAYARAN),0) AS GROSS_SALES,\n" +
                    "\ttgl_transaksi\n" +
                    "\tFROM TRANSAKSI where\n" +
                    "tgl_transaksi > (SELECT DATETIME('"+dashboardDetail.getTanggal()+"', '-7 day'))\n" +
                    "and tgl_transaksi < (SELECT DATETIME('"+dashboardDetail.getTanggal()+"', '0 day'))\n" +
                    "GROUP by tgl_transaksi\n" +
                    "order by tgl_transaksi ASC ) B ON substr('00'||N,-2) = substr('00'||b.bulan,-2)\n" +
                    "ORDER by tgl_transaksi asc";
        } else if (dashboardDetail.getMode().equals("Bulanan"))
            where = "where tahun = strftime('%Y','"+dashboardDetail.getTanggal()+"')";
        else if (dashboardDetail.getMode().equals("Tahunan"))
            where = "where tahun = '"+dashboardDetail.getTahun()+"'";





        ArrayList<DashboardDetail> list = new ArrayList<>();
        if (query.equals(""))
            query = "WITH DATEDATA(N) AS\n" +
                    "                (\n" +
                    "                        SELECT 1\n" +
                    "                        UNION ALL\n" +
                    "                        SELECT N+1\n" +
                    "                        FROM DATEDATA\n" +
                    "                        WHERE N < 12\n" +
                    "                )\n" +
                    "\n" +
                    "\n" +
                    "        SELECT substr('00'||N,-2) as bulan,IFNULL((b.GROSS_SALES),0) as gross_sales\n" +
                    "        FROM DATEDATA  A left join (SELECT strftime('%m',tgl_transaksi) as bulan,strftime('%Y',tgl_transaksi) as tahun,IFNULL(SUM(TOTAL_PEMBAYARAN),0) AS GROSS_SALES FROM TRANSAKSI\n" +
                    "        "+where+"           \n" +
                    "                group by strftime('%m',tgl_transaksi) , strftime('%Y',tgl_transaksi)) B ON substr('00'||N,-2) = b.bulan\n";



        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        try {
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            Log.d("success_dashboard","success_dashboard"+cursor.getCount());
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        DashboardDetail item = new DashboardDetail();
                        item.setBulan(cursor.getString(cursor.getColumnIndexOrThrow("bulan")));
                        item.setGrossSales(cursor.getString(cursor.getColumnIndexOrThrow("gross_sales")));
                        list.add(item);

                        Log.d("data_dashboard",item.toString());
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {
            Log.d("error_dashboard","error_dashboard",e);
        }

        connection.close();
    }

    @Override
    public void loadListBarang(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        String tanggal = dashboardDetail.getTanggal();
        String where = "";
        if (dashboardDetail.getMode().equals("Harian"))
            where = "where date(a.tgl_transaksi) = '"+dashboardDetail.getTanggal()+"'";
        else if (dashboardDetail.getMode().equals("Bulanan"))
            where = "where strftime('%m', a.tgl_transaksi) = substr('00'||"+dashboardDetail.getBulan()+",-2)";
        else if (dashboardDetail.getMode().equals("Tahunan"))
            where = "where strftime('%Y', a.tgl_transaksi) = '"+dashboardDetail.getTahun()+"'";

        ArrayList<DashboardDetail> list = new ArrayList<>();
        String query = "        SELECT C.nama_barang,SUM(B.JUMLAH) AS total, SUM((B.JUMLAH * B.HARGA_BARANG)) AS total_harga\n" +
                "        FROM TRANSAKSI A JOIN DETAIL_TRANSAKSI B ON A.KD_TRANSAKSI = B.KD_TRANSAKSI JOIN Barang C ON B.KD_BARANG = C.KD_BARANG "+where+" group by c.nama_barang,c.kd_barang ";
        connection = new Connection(context);
        connection.open();
        database = connection.dbHelper().getReadableDatabase();
        database = connection.database();

        try {
            Cursor cursor;
            cursor = database.rawQuery(query,null);
            Log.d("success_dashboard","success_dashboard"+cursor.getCount());
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        DashboardDetail item = new DashboardDetail();
                        item.setNamaBarang(cursor.getString(cursor.getColumnIndexOrThrow("nama_barang")));
                        item.setTotal(cursor.getString(cursor.getColumnIndexOrThrow("total")));
                        item.setTotalHarga(cursor.getString(cursor.getColumnIndexOrThrow("total_harga")));
                        list.add(item);

                        Log.d("data_dashboard",item.toString());
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            callback.onLoadSuccess(list);
        } catch (Exception e) {
            Log.d("error_dashboard","error_dashboard",e);
        }

        connection.close();
    }
}


