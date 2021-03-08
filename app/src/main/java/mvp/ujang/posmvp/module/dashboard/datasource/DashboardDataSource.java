package mvp.ujang.posmvp.module.dashboard.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;

public interface DashboardDataSource {
    void loadInfoPenjualan(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);
    void loadInfoGross(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);
    void loadListBarang(DashboardDetail dashboardDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);

    //    void loadTransaksi(DashboardDetail strukDetail, @NonNull Callback.LoadCallback<DashboardDetail> callback);
//    void refundBarang(DashboardDetail strukDetail, @NonNull Callback.AddCallback<DashboardDetail> callback);
//    void searchBarang(Barang parameter, @NonNull Callback.LoadCallback<Barang> loadProdukCallback);
//    void addBarang(@NonNull Barang produk, @NonNull Callback.AddCallback<Barang> addProdukCallback);
//    void editBarang(@NonNull Barang produk, @NonNull Callback.EditCallback<Barang> editProdukCallback);
//    void deleteBarang(@NonNull Barang produk, @NonNull Callback.DeleteCallback<Barang> deleteProdukCallback);
}