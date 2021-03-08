package mvp.ujang.posmvp.module.kategori.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;


import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.view.DashboardFragment;
import mvp.ujang.posmvp.module.kategori.KategoriContract;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.kategori.view.KategoriFragment;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.utils.Common;

public class KategoriPresenter implements KategoriContract.Presenter {

    private KategoriContract.KategoriView view;
    private Context context;
    private KategoriUsecase kategoriUsecase;
    private String TAG = KategoriFragment.class.getSimpleName();

    public KategoriPresenter(KategoriUsecase kategoriUsecase, KategoriContract.KategoriView view, Context context) {
        this.view = view;
        this.context = context;
        this.kategoriUsecase = kategoriUsecase;
    }


    @Override
    public void start() {
        loadKategori();
    }

    @Override
    public void loadKategori() {
        final long startTime = System.currentTimeMillis();
        kategoriUsecase.loadKategori(new Callback.LoadCallback<Kategori>() {
            @Override
            public void onLoadSuccess(List<Kategori> response) {
                view.listKategori(response);
                Common.printTimeMillis(TAG+" Load Data Kategori",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Kategori",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void addKategori(@NonNull Kategori kategori) {
        final long startTime = System.currentTimeMillis();
        kategoriUsecase.addKategori(kategori, new Callback.AddCallback<Kategori>() {
            @Override
            public void onAddSuccess(@NonNull Kategori response) {
                view.addKategori(response);
                Common.printTimeMillis(TAG+" Add Data Kategori",startTime,System.currentTimeMillis());
            }

            @Override
            public void onAddFailed() {
                Common.printTimeMillis(TAG+" Add Data Kategori",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void editKategori(@NonNull Kategori kategori) {

    }

    @Override
    public void deleteKategori(@NonNull Kategori kategori) {

    }
}
