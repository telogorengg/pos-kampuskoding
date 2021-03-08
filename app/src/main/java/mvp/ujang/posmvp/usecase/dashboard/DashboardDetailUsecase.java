package mvp.ujang.posmvp.usecase.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.datasource.DashboardDataSource;
import mvp.ujang.posmvp.module.dashboard.datasource.DashboardRepository;
import mvp.ujang.posmvp.module.dashboard.datasource.local.DashboardLocalDataSource;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.module.refund.datasource.RefundDetailDataSource;
import mvp.ujang.posmvp.module.refund.datasource.RefundDetailRepository;
import mvp.ujang.posmvp.module.refund.datasource.local.RefundDetailLocalDataSource;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;

public class DashboardDetailUsecase implements DashboardDataSource {

    private static DashboardDetailUsecase sInstance = null;
    private DashboardRepository dashboardRepository;

    public DashboardDetailUsecase(Context context) {
        this.dashboardRepository = DashboardRepository.getInstance(DashboardLocalDataSource.getInstance(context));
    }

    public static DashboardDetailUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new DashboardDetailUsecase(context);

        return sInstance;
    }

    @Override
    public void loadInfoPenjualan(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        dashboardRepository.loadInfoPenjualan(dashboardDetail,callback);
    }

    @Override
    public void loadInfoGross(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        dashboardRepository.loadInfoGross(dashboardDetail,callback);
    }

    @Override
    public void loadListBarang(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        dashboardRepository.loadListBarang(dashboardDetail,callback);
    }
}
