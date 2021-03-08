package mvp.ujang.posmvp.module.barang.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.barang.model.Barang;

public interface BarangDataSource {
    void loadBarang(@NonNull Callback.LoadCallback<Barang> loadProdukCallback);
    void searchBarang(Barang parameter, @NonNull Callback.LoadCallback<Barang> loadProdukCallback);
    void addBarang(@NonNull Barang barang, @NonNull Callback.AddCallback<Barang> addProdukCallback);
    void editBarang(@NonNull Barang barang, @NonNull Callback.EditCallback<Barang> editProdukCallback);
    void deleteBarang(@NonNull Barang barang, @NonNull Callback.DeleteCallback<Barang> deleteProdukCallback);
}