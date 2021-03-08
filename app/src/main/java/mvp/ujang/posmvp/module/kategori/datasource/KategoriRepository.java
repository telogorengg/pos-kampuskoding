package mvp.ujang.posmvp.module.kategori.datasource;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.kategori.datasource.local.KategoriLocalDataSource;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public class KategoriRepository implements KategoriDataSource {
    private static KategoriRepository sInstance = null;
    private KategoriLocalDataSource kategoriLocalDataSource;

    private KategoriRepository(@NonNull KategoriLocalDataSource kategoriLocalDataSource) {
        this.kategoriLocalDataSource = kategoriLocalDataSource;
    }

    public static KategoriRepository getInstance(@NonNull KategoriLocalDataSource kategoriLocalDataSource) {
        if (sInstance == null)
            sInstance = new KategoriRepository(kategoriLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadKategori(@NonNull Callback.LoadCallback<Kategori> callback) {
        kategoriLocalDataSource.loadKategori(callback);
    }

    @Override
    public void searchKategori(Kategori parameter, @NonNull Callback.LoadCallback<Kategori> callback) {
        kategoriLocalDataSource.searchKategori(parameter,callback);
    }

    @Override
    public void addKategori(@NonNull Kategori kategori, @NonNull Callback.AddCallback<Kategori> callback) {
        kategoriLocalDataSource.addKategori(kategori,callback);
    }

    @Override
    public void editKategori(@NonNull Kategori kategori, @NonNull Callback.EditCallback<Kategori> callback) {

    }

    @Override
    public void deleteKategori(@NonNull Kategori kategori, @NonNull Callback.DeleteCallback<Kategori> callback) {

    }
}
