package mvp.ujang.posmvp.module.keranjang.datasource;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;

public interface KeranjangDataSource {
    void loadKeranjang(@NonNull Callback.LoadCallback<Keranjang> callback);
    void addKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.AddCallback<Keranjang> callback);
    void editKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.EditCallback<Keranjang> callback);
    void deleteKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.DeleteCallback<Keranjang> callback);
}