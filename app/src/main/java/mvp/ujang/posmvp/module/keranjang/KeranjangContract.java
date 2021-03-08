package mvp.ujang.posmvp.module.keranjang;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;

public interface KeranjangContract {

    public interface KeranjangView extends BaseView<Presenter> {
        void listKeranjang(List<Keranjang> response);
        void listKategori(List<Kategori> response);
    }

    interface Presenter extends BasePresenter {
        void loadKeranjang();

        void addKeranjang(@NonNull Keranjang keranjang);

        void editKeranjang(@NonNull Keranjang keranjang);

        void deleteKeranjang(@NonNull Keranjang keranjang);

        void loadKategori();
    }
}
