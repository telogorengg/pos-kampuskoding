package mvp.ujang.posmvp.module.transaksidetail.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksidetail.datasource.local.TransaksiDetailLocalDataSource;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;

public class TransaksiDetailRepository implements TransaksiDetailDataSource {
    private static TransaksiDetailRepository sInstance = null;
    private TransaksiDetailLocalDataSource transaksiDetailLocalDataSource;

    private TransaksiDetailRepository(@NonNull TransaksiDetailLocalDataSource transaksiDetailLocalDataSource) {
        this.transaksiDetailLocalDataSource = transaksiDetailLocalDataSource;
    }

    public static TransaksiDetailRepository getInstance(@NonNull TransaksiDetailLocalDataSource transaksiDetailLocalDataSource) {
        if (sInstance == null)
            sInstance = new TransaksiDetailRepository(transaksiDetailLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadTransaksi(TransksiDetail transksiDetail, @NonNull Callback.LoadCallback<TransksiDetail> callback) {
        transaksiDetailLocalDataSource.loadTransaksi(transksiDetail,callback);
    }
}
