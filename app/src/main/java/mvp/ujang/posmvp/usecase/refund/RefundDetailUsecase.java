package mvp.ujang.posmvp.usecase.refund;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.refund.datasource.RefundDetailDataSource;
import mvp.ujang.posmvp.module.refund.datasource.RefundDetailRepository;
import mvp.ujang.posmvp.module.refund.datasource.local.RefundDetailLocalDataSource;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;

public class RefundDetailUsecase implements RefundDetailDataSource {

    private static RefundDetailUsecase sInstance = null;
    private RefundDetailRepository refundDetailRepository;

    public RefundDetailUsecase(Context context) {
        this.refundDetailRepository = RefundDetailRepository.getInstance(RefundDetailLocalDataSource.getInstance(context));
    }

    public static RefundDetailUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new RefundDetailUsecase(context);

        return sInstance;
    }

    @Override
    public void loadStruk(RefundDetail strukDetail, @NonNull Callback.LoadCallback<RefundDetail> callback) {
        refundDetailRepository.loadStruk(strukDetail,callback);
    }

    @Override
    public void refundBarang(RefundDetail strukDetail, @NonNull Callback.AddCallback<RefundDetail> callback) {
        refundDetailRepository.refundBarang(strukDetail,callback);
    }

    @Override
    public void loadTotalRefund(RefundDetail strukDetail, @NonNull Callback.LoadCallback<RefundDetail> callback) {
        refundDetailRepository.loadTotalRefund(strukDetail,callback);
    }
}
