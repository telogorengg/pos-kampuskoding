package mvp.ujang.posmvp.module.dashboard.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.datasource.local.DashboardLocalDataSource;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;

public class DashboardRepository implements DashboardDataSource {
    private static DashboardRepository sInstance = null;
    private DashboardLocalDataSource dashboardLocalDataSource;

    private DashboardRepository(@NonNull DashboardLocalDataSource dashboardLocalDataSource) {
        this.dashboardLocalDataSource = dashboardLocalDataSource;
    }

    public static DashboardRepository getInstance(@NonNull DashboardLocalDataSource strukDetailLocalDataSource) {
        if (sInstance == null)
            sInstance = new DashboardRepository(strukDetailLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadInfoPenjualan(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        dashboardLocalDataSource.loadInfoPenjualan(dashboardDetail,callback);
    }

    @Override
    public void loadInfoGross(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        dashboardLocalDataSource.loadInfoGross(dashboardDetail,callback);
    }

    @Override
    public void loadListBarang(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback) {
        dashboardLocalDataSource.loadListBarang(dashboardDetail,callback);
    }
}
