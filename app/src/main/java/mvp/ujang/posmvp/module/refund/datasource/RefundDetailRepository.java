package mvp.ujang.posmvp.module.refund.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.datasource.local.RefundDetailLocalDataSource;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;

public class RefundDetailRepository implements RefundDetailDataSource {
    private static RefundDetailRepository sInstance = null;
    private RefundDetailLocalDataSource strukDetailLocalDataSource;

    private RefundDetailRepository(@NonNull RefundDetailLocalDataSource strukDetailLocalDataSource) {
        this.strukDetailLocalDataSource = strukDetailLocalDataSource;
    }

    public static RefundDetailRepository getInstance(@NonNull RefundDetailLocalDataSource strukDetailLocalDataSource) {
        if (sInstance == null)
            sInstance = new RefundDetailRepository(strukDetailLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadStruk(RefundDetail strukDetail, @NonNull Callback.LoadCallback<RefundDetail> callback) {
        strukDetailLocalDataSource.loadStruk(strukDetail,callback);
    }

    @Override
    public void refundBarang(RefundDetail strukDetail, @NonNull Callback.AddCallback<RefundDetail> callback) {
        strukDetailLocalDataSource.refundBarang(strukDetail,callback);
    }

    @Override
    public void loadTotalRefund(RefundDetail strukDetail, @NonNull Callback.LoadCallback<RefundDetail> callback) {
        strukDetailLocalDataSource.loadTotalRefund(strukDetail,callback);
    }
}
