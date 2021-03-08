package mvp.ujang.posmvp.usecase.transaksidetail;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksidetail.datasource.TransaksiDetailDataSource;
import mvp.ujang.posmvp.module.transaksidetail.datasource.TransaksiDetailRepository;
import mvp.ujang.posmvp.module.transaksidetail.datasource.local.TransaksiDetailLocalDataSource;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;

public class TransaksiDetailUsecase implements TransaksiDetailDataSource {

    private static TransaksiDetailUsecase sInstance = null;
    private TransaksiDetailRepository transaksiDetailRepository;

    public TransaksiDetailUsecase(Context context) {
        this.transaksiDetailRepository = TransaksiDetailRepository.getInstance(TransaksiDetailLocalDataSource.getInstance(context));
    }

    public static TransaksiDetailUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new TransaksiDetailUsecase(context);

        return sInstance;
    }

    @Override
    public void loadTransaksi(TransksiDetail transksiDetail, @NonNull Callback.LoadCallback<TransksiDetail> callback) {
        transaksiDetailRepository.loadTransaksi(transksiDetail,callback);
    }
}
