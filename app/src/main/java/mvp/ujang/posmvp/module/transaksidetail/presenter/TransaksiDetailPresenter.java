package mvp.ujang.posmvp.module.transaksidetail.presenter;

import android.content.Context;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksidetail.TransaksiDetailContract;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;
import mvp.ujang.posmvp.module.transaksidetail.view.TransaksiDetailActivity;
import mvp.ujang.posmvp.usecase.transaksidetail.TransaksiDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class TransaksiDetailPresenter implements TransaksiDetailContract.Presenter {

    private TransaksiDetailContract.TransaksiView view;
    private TransaksiDetailUsecase strukUsecase;
    private Context context;
    private String TAG = TransaksiDetailActivity.class.getSimpleName();

    public TransaksiDetailPresenter(TransaksiDetailUsecase strukUsecase,
                                    TransaksiDetailContract.TransaksiView view,
                                    Context context) {
        this.strukUsecase = strukUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadTransaksi(null);
    }



    @Override
    public void loadTransaksi(TransksiDetail transksiDetail) {
        final long startTime = System.currentTimeMillis();
        strukUsecase.loadTransaksi(transksiDetail,new Callback.LoadCallback<TransksiDetail>() {
            @Override
            public void onLoadSuccess(List<TransksiDetail> response) {
                view.listTransaksi(response);
                Common.printTimeMillis(TAG+" Load Data Transaksi Detail",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Transaksi Detail",startTime,System.currentTimeMillis());
            }
        });
    }
}
