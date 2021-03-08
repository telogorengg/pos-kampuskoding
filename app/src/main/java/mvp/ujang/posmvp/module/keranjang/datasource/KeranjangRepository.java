package mvp.ujang.posmvp.module.keranjang.datasource;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.keranjang.datasource.local.KeranjangLocalDataSource;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;

public class KeranjangRepository implements KeranjangDataSource {

    private static KeranjangRepository sInstance = null;
    private KeranjangLocalDataSource keranjangLocalDataSource;

    private KeranjangRepository(@NonNull KeranjangLocalDataSource keranjangLocalDataSource) {
        this.keranjangLocalDataSource = keranjangLocalDataSource;
    }

    public static KeranjangRepository getInstance(@NonNull KeranjangLocalDataSource keranjangLocalDataSource) {
        if (sInstance == null)
            sInstance = new KeranjangRepository(keranjangLocalDataSource);

        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void loadKeranjang(@NonNull Callback.LoadCallback<Keranjang> callback) {
        keranjangLocalDataSource.loadKeranjang(callback);
    }

    @Override
    public void addKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.AddCallback<Keranjang> callback) {
        keranjangLocalDataSource.addKeranjang(keranjang,callback);
    }

    @Override
    public void editKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.EditCallback<Keranjang> callback) {
        keranjangLocalDataSource.editKeranjang(keranjang,callback);
    }

    @Override
    public void deleteKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.DeleteCallback<Keranjang> callback) {

    }
}
