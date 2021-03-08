package mvp.ujang.posmvp.module.penjualan.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.module.penjualan.PenjualanContract;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;
import mvp.ujang.posmvp.module.penjualan.view.PenjualanFragment;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.usecase.penjualan.PenjualanUsecase;
import mvp.ujang.posmvp.utils.Common;

public class PenjualanPresenter implements PenjualanContract.Presenter {

    private PenjualanContract.PenjualanView view;
    private KategoriUsecase  kategoriUsecase;
    private PenjualanUsecase penjualanUsecase;
    private KeranjangUsecase keranjangUsecase;
    private Context context;
    private String TAG = PenjualanFragment.class.getSimpleName();
    private List<Penjualan> listPenjualan = new ArrayList<>();

    public PenjualanPresenter(PenjualanUsecase penjualanUsecase,
                              KategoriUsecase kategoriUsecase,
                              KeranjangUsecase keranjangUsecase,
                              PenjualanContract.PenjualanView view,
                              Context context) {
        this.kategoriUsecase = kategoriUsecase;
        this.penjualanUsecase = penjualanUsecase;
        this.keranjangUsecase = keranjangUsecase;
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        loadPenjualan();
    }

    @Override
    public void loadPenjualan() {
        final long startTime = System.currentTimeMillis();
        penjualanUsecase.loadPenjualan(new Callback.LoadCallback<Penjualan>() {
            @Override
            public void onLoadSuccess(List<Penjualan> response) {
                listPenjualan.clear();
                listPenjualan.addAll(response);
                view.listProduk(response);
                Common.printTimeMillis(TAG+" Load Data Penjualan",startTime,System.currentTimeMillis());
            }

            @Override
            public void onLoadFailed() {
                Common.printTimeMillis(TAG+" Load Data Penjualan",startTime,System.currentTimeMillis());
            }
        });
    }

    @Override
    public void searchPenjualan(@NonNull Penjualan param) {
        final long startTime = System.currentTimeMillis();
        List<Penjualan> filter = new ArrayList<>();
        for(int i = 0; i < listPenjualan.size(); i++)
        {
            if((listPenjualan.get(i).getNamaBarang().toUpperCase().contains(param.getNamaBarang().toUpperCase()) || param.getNamaBarang().equals("")) &&
                    (listPenjualan.get(i).getIdKategori().toUpperCase().contains(param.getIdKategori().toUpperCase()) || param.getIdKategori().equals("0")))
                filter.add(listPenjualan.get(i));
        }
        view.listProduk(filter);
        Common.printTimeMillis(TAG+" Search Data Penjualan",startTime,System.currentTimeMillis());
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

}
