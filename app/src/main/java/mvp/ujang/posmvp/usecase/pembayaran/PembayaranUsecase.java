package mvp.ujang.posmvp.usecase.pembayaran;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.pembayaran.datasource.PembayaranDataSource;
import mvp.ujang.posmvp.module.pembayaran.datasource.PembayaranRepository;
import mvp.ujang.posmvp.module.pembayaran.datasource.local.PembayaranLocalDataSource;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;

public class PembayaranUsecase implements PembayaranDataSource {

    private static PembayaranUsecase sInstance = null;
    private PembayaranRepository pembayaranRepository;
    private Context context;

    public PembayaranUsecase(Context context){
        this.pembayaranRepository = PembayaranRepository.getInstance(PembayaranLocalDataSource.getInstance(context));
        this.context = context;
    }


    public static PembayaranUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new PembayaranUsecase(context);

        return sInstance;
    }

    @Override
    public void loadTotalPembayaran(@NonNull Callback.LoadCallback<Pembayaran> callback) {
        pembayaranRepository.loadTotalPembayaran(callback);
    }

    @Override
    public void addPembayaran(@NonNull Pembayaran pembayaran, @NonNull Callback.AddCallback<Pembayaran> callback) {
        pembayaranRepository.addPembayaran(pembayaran,callback);
    }
}