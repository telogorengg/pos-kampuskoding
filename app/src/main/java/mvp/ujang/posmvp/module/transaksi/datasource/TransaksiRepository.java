package mvp.ujang.posmvp.module.transaksi.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksi.datasource.local.TransaksiLocalDataSource;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;

public class TransaksiRepository implements TransaksiDataSource {
    private static TransaksiRepository sInstance = null;
    private TransaksiLocalDataSource strukLocalDataSource;

    private TransaksiRepository(@NonNull TransaksiLocalDataSource strukLocalDataSource) {
        this.strukLocalDataSource = strukLocalDataSource;
    }

    public static TransaksiRepository getInstance(@NonNull TransaksiLocalDataSource strukLocalDataSource) {
        if (sInstance == null)
            sInstance = new TransaksiRepository(strukLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadTransaksi(@NonNull Callback.LoadCallback<Transaksi> callback) {
        strukLocalDataSource.loadTransaksi(callback);
    }
}
