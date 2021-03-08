package mvp.ujang.posmvp.module.refund.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.RefundContract;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;
import mvp.ujang.posmvp.module.refund.view.RefundDetailActivity;
import mvp.ujang.posmvp.usecase.refund.RefundDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class RefundDetailPresenter implements RefundContract.Presenter {

    private RefundContract.StrukView view;
    private RefundDetailUsecase strukUsecase;
    private Context context;
    private String TAG = RefundDetailActivity.class.getSimpleName();

    public RefundDetailPresenter(RefundDetailUsecase strukUsecase,
                                 RefundContract.StrukView view,
                                 Context context) {
        this.strukUsecase = strukUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadStruk(null);
    }



    @Override
    public void loadStruk(RefundDetail strukDetail) {
        final long startTime = System.currentTimeMillis();
        strukUsecase.loadStruk(strukDetail,new Callback.LoadCallback<RefundDetail>() {
            @Override
            public void onLoadSuccess(List<RefundDetail> response) {
                view.listStruk(response);
                Common.printTimeMillis(TAG+" Load Data Transaksi",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Transaksi",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void refundBarang(RefundDetail strukDetail) {
        final long startTime = System.currentTimeMillis();
        strukUsecase.refundBarang(strukDetail, new Callback.AddCallback<RefundDetail>() {
            @Override
            public void onAddSuccess(@NonNull RefundDetail response) {
                Common.printTimeMillis(TAG+" Refund Barang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onAddFailed() {
                Common.printTimeMillis(TAG+" Refund Barang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void loadTotalRefund(RefundDetail strukDetail) {
        final long startTime = System.currentTimeMillis();
        strukUsecase.loadTotalRefund(strukDetail, new Callback.LoadCallback<RefundDetail>() {
            @Override
            public void onLoadSuccess(List<RefundDetail> response) {
                view.totalRefund(response);
                Common.printTimeMillis(TAG+" Get Total Refund Barang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Get Total Refund Barang",startTime,System.currentTimeMillis());
            }
        });
    }
}
