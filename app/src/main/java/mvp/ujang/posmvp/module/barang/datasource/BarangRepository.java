package mvp.ujang.posmvp.module.barang.datasource;

import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.barang.datasource.local.BarangLocalDataSource;
import mvp.ujang.posmvp.module.barang.model.Barang;

public class BarangRepository implements BarangDataSource {
    private static BarangRepository sInstance = null;
    private BarangLocalDataSource barangLocalDataSource;

    private BarangRepository(@NonNull BarangLocalDataSource barangLocalDataSource) {
        this.barangLocalDataSource = barangLocalDataSource;
    }

    public static BarangRepository getInstance(@NonNull BarangLocalDataSource barangLocalDataSource) {
        if (sInstance == null)
            sInstance = new BarangRepository(barangLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }


    @Override
    public void loadBarang(@NonNull Callback.LoadCallback<Barang> loadProdukCallback) {
        barangLocalDataSource.loadBarang(loadProdukCallback);
    }

    @Override
    public void searchBarang(Barang parameter, @NonNull Callback.LoadCallback<Barang> loadProdukCallback) {
        barangLocalDataSource.searchBarang(parameter,loadProdukCallback);
    }

    @Override
    public void addBarang(@NonNull Barang barang, @NonNull Callback.AddCallback<Barang> addProdukCallback) {
        barangLocalDataSource.addBarang(barang,addProdukCallback);
    }

    @Override
    public void editBarang(@NonNull Barang barang, @NonNull Callback.EditCallback<Barang> editProdukCallback) {

    }

    @Override
    public void deleteBarang(@NonNull Barang barang, @NonNull Callback.DeleteCallback<Barang> deleteProdukCallback) {

    }

}
