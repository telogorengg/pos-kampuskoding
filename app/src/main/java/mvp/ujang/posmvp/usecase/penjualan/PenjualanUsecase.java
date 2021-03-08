package mvp.ujang.posmvp.usecase.penjualan;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.penjualan.datasource.PenjualanDataSource;
import mvp.ujang.posmvp.module.penjualan.datasource.PenjualanRepository;
import mvp.ujang.posmvp.module.penjualan.datasource.local.PenjualanLocalDataSource;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;

public class PenjualanUsecase implements PenjualanDataSource {

    private static PenjualanUsecase sInstance = null;
    private PenjualanRepository penjualanRepository;

    public PenjualanUsecase(Context context) {
        this.penjualanRepository = PenjualanRepository.getInstance(PenjualanLocalDataSource.getInstance(context));
    }

    public static PenjualanUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new PenjualanUsecase(context);

        return sInstance;
    }


    @Override
    public void loadPenjualan(@NonNull Callback.LoadCallback<Penjualan> callback) {
        penjualanRepository.loadPenjualan(callback);
    }

    @Override
    public void searchPenjualan(Penjualan parameter, @NonNull Callback.LoadCallback<Penjualan> callback) {
        penjualanRepository.searchPenjualan(parameter,callback);
    }
}
