package mvp.ujang.posmvp.module.barang.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.barang.ProdukContract;
import mvp.ujang.posmvp.module.barang.view.BarangFragment;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.usecase.barang.BarangUsecase;
import mvp.ujang.posmvp.utils.Common;

public class BarangPresenter implements ProdukContract.Presenter {

    private ProdukContract.BarangView view;
    private KategoriUsecase  kategoriUsecase;
    private BarangUsecase barangUsecase;
    private KeranjangUsecase keranjangUsecase;
    private Context context;
    private String TAG = BarangFragment.class.getSimpleName();
    private List<Barang> listBarang = new ArrayList<>();

    public BarangPresenter(BarangUsecase barangUsecase,
                           KategoriUsecase kategoriUsecase,
                           ProdukContract.BarangView view,
                           Context context) {
        this.kategoriUsecase = kategoriUsecase;
        this.barangUsecase = barangUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadBarang();
    }

    @Override
    public void loadBarang() {
        final long startTime = System.currentTimeMillis();
        barangUsecase.loadBarang(new Callback.LoadCallback<Barang>() {
            @Override
            public void onLoadSuccess(List<Barang> response) {
                listBarang.clear();
                listBarang.addAll(response);
                view.listBarang(response);
                Common.printTimeMillis(TAG+" Load Data Barang",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Barang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void searchBarang(@NonNull Barang param) {
        final long startTime = System.currentTimeMillis();
        List<Barang> filter = new ArrayList<>();
        for(int i = 0; i < listBarang.size(); i++)
        {
            if((listBarang.get(i).getNamaBarang().toUpperCase().contains(param.getNamaBarang().toUpperCase()) || param.getNamaBarang().equals("")) &&
                    (listBarang.get(i).getIdKategori().toUpperCase().contains(param.getIdKategori().toUpperCase()) || param.getIdKategori().equals("")))
                filter.add(listBarang.get(i));
        }
        view.listBarang(filter);
        Common.printTimeMillis(TAG+" Search Data Barang",startTime,System.currentTimeMillis());
    }


    @Override
    public void addBarang(@NonNull Barang barang) {
        final long startTime = System.currentTimeMillis();
        barangUsecase.addBarang(barang, new Callback.AddCallback<Barang>() {
            @Override
            public void onAddSuccess(@NonNull Barang response) {
                view.addBarang(response);
                Common.printTimeMillis(TAG+" Add Data Barang",startTime,System.currentTimeMillis());

            }

            @Override
            public void onAddFailed() {
                Common.printTimeMillis(TAG+" Add Data Barang",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void editBarang(@NonNull Barang barang) {

    }

    @Override
    public void deleteBarang(@NonNull Barang barang) {

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

}
