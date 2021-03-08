package mvp.ujang.posmvp.module.pembayaran.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.pembayaran.datasource.local.PembayaranLocalDataSource;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;

public class PembayaranRepository implements PembayaranDataSource {

    private static PembayaranRepository sInstance = null;
    private PembayaranLocalDataSource pembayaranLocalDataSource;

    private PembayaranRepository(@NonNull PembayaranLocalDataSource pembayaranLocalDataSource) {
        this.pembayaranLocalDataSource = pembayaranLocalDataSource;
    }

    public static PembayaranRepository getInstance(@NonNull PembayaranLocalDataSource pembayaranLocalDataSource) {
        if (sInstance == null)
            sInstance = new PembayaranRepository(pembayaranLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadTotalPembayaran(@NonNull Callback.LoadCallback<Pembayaran> callback) {
        pembayaranLocalDataSource.loadTotalPembayaran(callback);
    }

    @Override
    public void addPembayaran(@NonNull Pembayaran pembayaran, @NonNull Callback.AddCallback<Pembayaran> callback) {
        pembayaranLocalDataSource.addPembayaran(pembayaran,callback);
    }
}
