package mvp.ujang.posmvp.usecase.kategori;

import android.content.Context;
import android.support.annotation.NonNull;

import mvp.ujang.posmvp.base.Callback;
import mvp.ujang.posmvp.module.kategori.datasource.KategoriDataSource;
import mvp.ujang.posmvp.module.kategori.datasource.KategoriRepository;
import mvp.ujang.posmvp.module.kategori.datasource.local.KategoriLocalDataSource;
import mvp.ujang.posmvp.module.kategori.model.Kategori;

public class KategoriUsecase implements KategoriDataSource {

    private static KategoriUsecase sInstance = null;
    private KategoriRepository kategoriRepository;


    public KategoriUsecase(Context context){
        this.kategoriRepository = KategoriRepository.getInstance(KategoriLocalDataSource.getInstance(context));
    }


    public static KategoriUsecase getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new KategoriUsecase(context);

        return sInstance;
    }

    @Override
    public void loadKategori(@NonNull Callback.LoadCallback<Kategori> callback) {
        kategoriRepository.loadKategori(callback);
    }

    @Override
    public void searchKategori(Kategori parameter, @NonNull Callback.LoadCallback<Kategori> callback) {

    }

    @Override
    public void addKategori(@NonNull Kategori kategori, @NonNull Callback.AddCallback<Kategori> callback) {
        kategoriRepository.addKategori(kategori,callback);
    }

    @Override
    public void editKategori(@NonNull Kategori kategori, @NonNull Callback.EditCallback<Kategori> callback) {

    }

    @Override
    public void deleteKategori(@NonNull Kategori kategori, @NonNull Callback.DeleteCallback<Kategori> callback) {

    }
}
