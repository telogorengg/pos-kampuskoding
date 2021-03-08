package mvp.ujang.posmvp.module.penjualan.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;

public interface PenjualanDataSource {
    void loadPenjualan(@NonNull Callback.LoadCallback<Penjualan> callback);
    void searchPenjualan(Penjualan parameter, @NonNull Callback.LoadCallback<Penjualan> callback);
}