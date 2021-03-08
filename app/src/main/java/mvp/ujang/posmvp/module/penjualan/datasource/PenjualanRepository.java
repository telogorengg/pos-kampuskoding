package mvp.ujang.posmvp.module.penjualan.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.penjualan.datasource.local.PenjualanLocalDataSource;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;

public class PenjualanRepository implements PenjualanDataSource{
    private static PenjualanRepository sInstance = null;
    private PenjualanLocalDataSource penjualanLocalDataSource;

    private PenjualanRepository(@NonNull PenjualanLocalDataSource penjualanLocalDataSource) {
        this.penjualanLocalDataSource = penjualanLocalDataSource;
    }

    public static PenjualanRepository getInstance(@NonNull PenjualanLocalDataSource penjualanLocalDataSource) {
        if (sInstance == null)
            sInstance = new PenjualanRepository(penjualanLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadPenjualan(@NonNull Callback.LoadCallback<Penjualan> callback) {
        penjualanLocalDataSource.loadPenjualan(callback);
    }

    @Override
    public void searchPenjualan(Penjualan parameter, @NonNull Callback.LoadCallback<Penjualan> callback) {
        penjualanLocalDataSource.searchPenjualan(parameter,callback);
    }
}
