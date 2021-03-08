package mvp.ujang.posmvp.usecase.barang;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.barang.datasource.BarangDataSource;
import mvp.ujang.posmvp.module.barang.datasource.BarangRepository;
import mvp.ujang.posmvp.module.barang.datasource.local.BarangLocalDataSource;
import mvp.ujang.posmvp.module.barang.model.Barang;

public class BarangUsecase implements BarangDataSource {

    private static BarangUsecase sInstance = null;
    private BarangRepository barangRepository;

    public BarangUsecase(Context context) {
        this.barangRepository = BarangRepository.getInstance(BarangLocalDataSource.getInstance(context));
    }

    public static BarangUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new BarangUsecase(context);

        return sInstance;
    }


    @Override
    public void loadBarang(@NonNull Callback.LoadCallback<Barang> loadProdukCallback) {
        barangRepository.loadBarang(loadProdukCallback);
    }

    @Override
    public void searchBarang(Barang parameter, @NonNull Callback.LoadCallback<Barang> loadProdukCallback) {
        barangRepository.searchBarang(parameter,loadProdukCallback);
    }

    @Override
    public void addBarang(@NonNull Barang barang, @NonNull Callback.AddCallback<Barang> addProdukCallback) {
        barangRepository.addBarang(barang,addProdukCallback);
    }

    @Override
    public void editBarang(@NonNull Barang barang, @NonNull Callback.EditCallback<Barang> editProdukCallback) {

    }

    @Override
    public void deleteBarang(@NonNull Barang barang, @NonNull Callback.DeleteCallback<Barang> deleteProdukCallback) {

    }
}
