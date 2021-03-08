package mvp.ujang.posmvp.module.dashboard.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.DashboardContract;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.module.dashboard.view.DashboardFragment;
import mvp.ujang.posmvp.usecase.dashboard.DashboardDetailUsecase;
import mvp.ujang.posmvp.usecase.refund.RefundDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.DashboardView view;
    private DashboardDetailUsecase dashboardDetailUsecase;
    private Context context;
    private String TAG = DashboardFragment.class.getSimpleName();

    public DashboardPresenter(DashboardDetailUsecase dashboardDetailUsecase,
                              DashboardContract.DashboardView view,
                              Context context) {
        this.dashboardDetailUsecase = dashboardDetailUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadInfoPenjualan(null);
    }


    @Override
    public void loadInfoPenjualan(DashboardDetail dashboardDetail) {
        final long startTime = System.currentTimeMillis();
        dashboardDetailUsecase.loadInfoPenjualan(dashboardDetail, new Callback.LoadCallback<DashboardDetail>() {
            @Override
            public void onLoadSuccess(List<DashboardDetail> response) {
                view.listInfoPenjualan(response);
                Common.printTimeMillis(TAG+" Load Info Penjualan",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Info Penjualan",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void loadInfoGross(DashboardDetail dashboardDetail) {
        final long startTime = System.currentTimeMillis();
        dashboardDetailUsecase.loadInfoGross(dashboardDetail, new Callback.LoadCallback<DashboardDetail>() {
            @Override
            public void onLoadSuccess(List<DashboardDetail> response) {
                view.listInfoGross(response);
                Common.printTimeMillis(TAG+" Load Info Gross",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Info Gross",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void loadListBarang(DashboardDetail dashboardDetail) {
        final long startTime = System.currentTimeMillis();
        dashboardDetailUsecase.loadListBarang(dashboardDetail, new Callback.LoadCallback<DashboardDetail>() {
            @Override
            public void onLoadSuccess(List<DashboardDetail> response) {
                view.listInfoBarang(response);
                Common.printTimeMillis(TAG+" Load List Barang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load List Barang",startTime,System.currentTimeMillis());
            }
        });
    }
}
