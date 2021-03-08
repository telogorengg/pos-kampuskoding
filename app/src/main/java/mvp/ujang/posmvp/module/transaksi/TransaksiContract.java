package mvp.ujang.posmvp.module.transaksi;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;

public class TransaksiContract {

    public interface TransaksiView extends BaseView<Presenter> {
        void listTransaksi(List<Transaksi> response);
    }

    public interface Presenter extends BasePresenter {
        void loadTransaksi();
    }


}
