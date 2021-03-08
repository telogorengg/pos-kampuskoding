package mvp.ujang.posmvp.module.refund.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;

public interface RefundDetailDataSource {
    void loadStruk(RefundDetail strukDetail, @NonNull Callback.LoadCallback<RefundDetail> callback);
    void refundBarang(RefundDetail strukDetail, @NonNull Callback.AddCallback<RefundDetail> callback);
    void loadTotalRefund(RefundDetail strukDetail,@NonNull Callback.LoadCallback<RefundDetail> callback);
}