package mvp.ujang.posmvp.module.pembayaran;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;

public class PembayaranContract {

    public interface PembayaranView extends BaseView<Presenter> {
            void totalPembayaran(List<Pembayaran> response);
    }

    public interface Presenter extends BasePresenter {
        void totalPembayaran();

        void addPembayaran(@NonNull Pembayaran pembayaran);

    }


}
