package mvp.ujang.posmvp.usecase.transaksi;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksi.datasource.TransaksiDataSource;
import mvp.ujang.posmvp.module.transaksi.datasource.TransaksiRepository;
import mvp.ujang.posmvp.module.transaksi.datasource.local.TransaksiLocalDataSource;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;

public class TransaksiUsecase implements TransaksiDataSource {

    private static TransaksiUsecase sInstance = null;
    private TransaksiRepository transaksiRepository;

    public TransaksiUsecase(Context context) {
        this.transaksiRepository = TransaksiRepository.getInstance(TransaksiLocalDataSource.getInstance(context));
    }

    public static TransaksiUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new TransaksiUsecase(context);

        return sInstance;
    }

    @Override
    public void loadTransaksi(@NonNull Callback.LoadCallback<Transaksi> callback) {
        transaksiRepository.loadTransaksi(callback);
    }
}
