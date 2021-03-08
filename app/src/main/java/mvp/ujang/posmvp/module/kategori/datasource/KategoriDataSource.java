package mvp.ujang.posmvp.module.kategori.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public interface KategoriDataSource {
    void loadKategori(@NonNull Callback.LoadCallback<Kategori> callback);
    void searchKategori(Kategori parameter, @NonNull Callback.LoadCallback<Kategori> callback);
    void addKategori(@NonNull Kategori kategori, @NonNull Callback.AddCallback<Kategori> callback);
    void editKategori(@NonNull Kategori kategori, @NonNull Callback.EditCallback<Kategori> callback);
    void deleteKategori(@NonNull Kategori kategori, @NonNull Callback.DeleteCallback<Kategori> callback);
}