package mvp.ujang.posmvp.module.transaksi.presenter;

import android.content.Context;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksi.TransaksiContract;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;
import mvp.ujang.posmvp.module.transaksi.view.TransaksiFragment;
import mvp.ujang.posmvp.usecase.transaksi.TransaksiUsecase;
import mvp.ujang.posmvp.utils.Common;

public class TransaksiPresenter implements TransaksiContract.Presenter {

    private TransaksiContract.TransaksiView view;
    private TransaksiUsecase transaksiUsecase;
    private Context context;
    private String TAG = TransaksiFragment.class.getSimpleName();

    public TransaksiPresenter(TransaksiUsecase transaksiUsecase,
                              TransaksiContract.TransaksiView view,
                              Context context) {
        this.transaksiUsecase = transaksiUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadTransaksi();
    }


    @Override
    public void loadTransaksi() {
        final long startTime = System.currentTimeMillis();
        transaksiUsecase.loadTransaksi(new Callback.LoadCallback<Transaksi>() {
            @Override
            public void onLoadSuccess(List<Transaksi> response) {
                view.listTransaksi(response);
                Common.printTimeMillis(TAG+" Load Data Transaksi",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Transaksi",startTime,System.currentTimeMillis());
            }
        });
    }
}
