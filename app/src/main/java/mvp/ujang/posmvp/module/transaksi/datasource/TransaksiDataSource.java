package mvp.ujang.posmvp.module.transaksi.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;

public interface TransaksiDataSource {
    void loadTransaksi(@NonNull Callback.LoadCallback<Transaksi> callback);
//    void searchBarang(Barang parameter, @NonNull Callback.LoadCallback<Barang> loadProdukCallback);
//    void addBarang(@NonNull Barang produk, @NonNull Callback.AddCallback<Barang> addProdukCallback);
//    void editBarang(@NonNull Barang produk, @NonNull Callback.EditCallback<Barang> editProdukCallback);
//    void deleteBarang(@NonNull Barang produk, @NonNull Callback.DeleteCallback<Barang> deleteProdukCallback);
}