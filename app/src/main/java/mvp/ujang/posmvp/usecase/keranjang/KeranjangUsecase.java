package mvp.ujang.posmvp.usecase.keranjang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.keranjang.datasource.KeranjangDataSource;
import mvp.ujang.posmvp.module.keranjang.datasource.KeranjangRepository;
import mvp.ujang.posmvp.module.keranjang.datasource.local.KeranjangLocalDataSource;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;

public class KeranjangUsecase implements KeranjangDataSource{

    private static KeranjangUsecase sInstance = null;
    private KeranjangRepository keranjangRepository;
    private Context context;

    public KeranjangUsecase(Context context){
        this.keranjangRepository = KeranjangRepository.getInstance(KeranjangLocalDataSource.getInstance(context));
        this.context = context;
    }


    public static KeranjangUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new KeranjangUsecase(context);

        return sInstance;
    }

    @Override
    public void loadKeranjang(@NonNull Callback.LoadCallback<Keranjang> callback) {
        keranjangRepository.loadKeranjang(callback);
    }

    @Override
    public void addKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.AddCallback<Keranjang> callback) {
        keranjangRepository.addKeranjang(keranjang,callback);
    }

    @Override
    public void editKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.EditCallback<Keranjang> callback) {
        keranjangRepository.editKeranjang(keranjang,callback);
    }

    @Override
    public void deleteKeranjang(@NonNull Keranjang keranjang, @NonNull Callback.DeleteCallback<Keranjang> callback) {
        keranjangRepository.deleteKeranjang(keranjang,callback);
    }

}