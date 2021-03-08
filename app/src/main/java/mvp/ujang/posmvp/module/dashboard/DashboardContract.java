package mvp.ujang.posmvp.module.dashboard;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;

public class DashboardContract {

    public interface DashboardView extends BaseView<Presenter> {
        void listInfoPenjualan(List<DashboardDetail> response);
        void listInfoGross(List<DashboardDetail> response);
        void listInfoBarang(List<DashboardDetail> response);
    }

    public interface Presenter extends BasePresenter {
        void loadInfoPenjualan(DashboardDetail dashboardDetail);
        void loadInfoGross(DashboardDetail dashboardDetail);
        void loadListBarang(DashboardDetail dashboardDetail);
    }


}
