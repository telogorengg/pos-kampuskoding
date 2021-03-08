package mvp.ujang.posmvp.module.keranjang.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.keranjang.KeranjangContract;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.module.keranjang.view.KeranjangFragment;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.utils.Common;

public class KeranjangPresenter implements KeranjangContract.Presenter {

    private KeranjangContract.KeranjangView view;
    private KeranjangUsecase keranjangUsecase;
    private Context context;
    private String TAG = KeranjangFragment.class.getSimpleName();

    public KeranjangPresenter(KeranjangUsecase keranjangUsecase, KeranjangContract.KeranjangView view, Context context) {
        this.keranjangUsecase = keranjangUsecase;
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadKeranjang();
    }


    @Override
    public void loadKeranjang() {
        final long startTime = System.currentTimeMillis();
        keranjangUsecase.loadKeranjang(new Callback.LoadCallback<Keranjang>() {
            @Override
            public void onLoadSuccess(List<Keranjang> response) {
                view.listKeranjang(response);
                Common.printTimeMillis(TAG+" Load Data Keranjang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Keranjang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void addKeranjang(@NonNull Keranjang keranjang) {
        final long startTime = System.currentTimeMillis();
        keranjangUsecase.addKeranjang(keranjang, new Callback.AddCallback<Keranjang>() {
            @Override
            public void onAddSuccess(@NonNull Keranjang response) {
                Common.printTimeMillis(TAG+" Add Data Keranjang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onAddFailed() {
                Common.printTimeMillis(TAG+" Add Data Keranjang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void editKeranjang(@NonNull Keranjang keranjang) {
        final long startTime = System.currentTimeMillis();
        keranjangUsecase.editKeranjang(keranjang, new Callback.EditCallback<Keranjang>() {
            @Override
            public void onEditSuccess(@NonNull Keranjang response) {
                Common.printTimeMillis(TAG+" Edit Data Keranjang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onEditFailed() {
                Common.printTimeMillis(TAG+" Edit Data Keranjang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void deleteKeranjang(@NonNull Keranjang keranjang) {
        final long startTime = System.currentTimeMillis();
        keranjangUsecase.deleteKeranjang(keranjang, new Callback.DeleteCallback<Keranjang>() {
            @Override
            public void onDeleteSuccess(@NonNull Keranjang produk) {
                Common.printTimeMillis(TAG+" Delete Data Keranjang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onDeleteFailed() {
                Common.printTimeMillis(TAG+" Delete Data Keranjang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void loadKategori() {
    }

}
