package mvp.ujang.posmvp.module.kategori;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.BasePresenter;
import mvp.ujang.posmvp.base.BaseView;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public class KategoriContract {

    public interface KategoriView extends BaseView<Presenter>
    {
        void listKategori(List<Kategori> response);
        void addKategori(Kategori response);
    }

    public interface Presenter extends BasePresenter {
        void loadKategori();
        void addKategori(@NonNull Kategori kategori);
        void editKategori(@NonNull Kategori kategori);
        void deleteKategori(@NonNull Kategori kategori);
    }

}
