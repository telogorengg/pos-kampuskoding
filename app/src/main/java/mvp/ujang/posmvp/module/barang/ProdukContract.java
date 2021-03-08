package mvp.ujang.posmvp.module.barang;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public class ProdukContract {

    public interface BarangView extends BaseView<Presenter> {
            void listBarang(List<Barang> response);
            void listKategori(List<Kategori> response);
            void addBarang(Barang response);
            void detailBarang(Barang response);
    }

    public interface Presenter extends BasePresenter {
        void loadBarang();

        void searchBarang(@NonNull Barang param);

        void addBarang(@NonNull Barang barang);

        void editBarang(@NonNull Barang barang);

        void deleteBarang(@NonNull Barang barang);

        void loadKategori();
    }


}
