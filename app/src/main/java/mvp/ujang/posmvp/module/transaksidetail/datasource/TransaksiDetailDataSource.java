package mvp.ujang.posmvp.module.transaksidetail.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;

public interface TransaksiDetailDataSource {
    void loadTransaksi(TransksiDetail transksiDetail, @NonNull Callback.LoadCallback<TransksiDetail> callback);
//    void searchBarang(Barang parameter, @NonNull Callback.LoadCallback<Barang> loadProdukCallback);
//    void addBarang(@NonNull Barang produk, @NonNull Callback.AddCallback<Barang> addProdukCallback);
//    void editBarang(@NonNull Barang produk, @NonNull Callback.EditCallback<Barang> editProdukCallback);
//    void deleteBarang(@NonNull Barang produk, @NonNull Callback.DeleteCallback<Barang> deleteProdukCallback);
}