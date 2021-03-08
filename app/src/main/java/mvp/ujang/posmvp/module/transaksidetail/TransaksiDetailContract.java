package mvp.ujang.posmvp.module.transaksidetail;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;

public class TransaksiDetailContract {

    public interface TransaksiView extends BaseView<Presenter> {
        void listTransaksi(List<TransksiDetail> response);
    }

    public interface Presenter extends BasePresenter {
        void loadTransaksi(TransksiDetail transksiDetail);
    }


}
