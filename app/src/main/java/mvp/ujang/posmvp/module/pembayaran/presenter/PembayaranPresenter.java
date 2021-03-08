package mvp.ujang.posmvp.module.pembayaran.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.pembayaran.PembayaranContract;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;
import mvp.ujang.posmvp.module.pembayaran.view.PembayaranActivity;
import mvp.ujang.posmvp.usecase.pembayaran.PembayaranUsecase;
import mvp.ujang.posmvp.utils.Common;

public class PembayaranPresenter implements PembayaranContract.Presenter {

    private PembayaranContract.PembayaranView view;
    private PembayaranUsecase pembayaranUsecase;
    private Context context;
    private String TAG = PembayaranActivity.class.getSimpleName();

    public PembayaranPresenter(PembayaranUsecase pembayaranUsecase,
                               PembayaranContract.PembayaranView view,
                               Context context) {
        this.pembayaranUsecase = pembayaranUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        totalPembayaran();
    }


    @Override
    public void totalPembayaran() {
        final long startTime = System.currentTimeMillis();
        pembayaranUsecase.loadTotalPembayaran(new Callback.LoadCallback<Pembayaran>() {
            @Override
            public void onLoadSuccess(List<Pembayaran> response) {
                view.totalPembayaran(response);
                Common.printTimeMillis(TAG+" Get Total Pembayaran",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Get Total Pembayaran",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void addPembayaran(@NonNull Pembayaran pembayaran) {
        final long startTime = System.currentTimeMillis();
        pembayaranUsecase.addPembayaran(pembayaran, new Callback.AddCallback<Pembayaran>() {
            @Override
            public void onAddSuccess(@NonNull Pembayaran response) {
                Common.printTimeMillis(TAG+" Add Total Pembayaran",startTime,System.currentTimeMillis());
            }

            @Override
            public void onAddFailed() {
                Common.printTimeMillis(TAG+" Add Total Pembayaran",startTime,System.currentTimeMillis());
            }
        });
    }
}
