package mvp.ujang.posmvp.module.penjualan;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;

public class PenjualanContract {

    public interface PenjualanView extends BaseView<Presenter> {
            void listProduk    (List <Penjualan> response);
            void listKategori  (List <Kategori> response);
            void listKeranjang (List <Keranjang> response);
            void detailProduk  (Penjualan response);
    }

    public interface Presenter extends BasePresenter {
        void loadPenjualan();

        void searchPenjualan(@NonNull Penjualan param);

        void loadKategori();

        void addKeranjang(@NonNull Keranjang keranjang);

        void loadKeranjang();
    }


}
