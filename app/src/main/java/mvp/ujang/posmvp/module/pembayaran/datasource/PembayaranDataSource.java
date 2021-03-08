package mvp.ujang.posmvp.module.pembayaran.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;

public interface PembayaranDataSource {
    void loadTotalPembayaran(@NonNull Callback.LoadCallback<Pembayaran> callback);
    void addPembayaran(@NonNull Pembayaran pembayaran, @NonNull Callback.AddCallback<Pembayaran> callback);
}