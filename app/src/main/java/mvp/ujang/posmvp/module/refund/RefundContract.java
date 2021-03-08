package mvp.ujang.posmvp.module.refund;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;

public class RefundContract {

    public interface StrukView extends BaseView<Presenter> {
        void listStruk(List<RefundDetail> response);
        void totalRefund(List<RefundDetail> response);
    }

    public interface Presenter extends BasePresenter {
        void loadStruk(RefundDetail strukDetail);
        void refundBarang(RefundDetail strukDetail);
        void loadTotalRefund(RefundDetail strukDetail);
    }


}
