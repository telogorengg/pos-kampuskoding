package mvp.ujang.posmvp.base;

import android.support.annotation.NonNull;

import java.util.List;

public class Callback {
    public interface LoadCallback<T> {
        void onLoadSuccess(List<T> response);
        void onLoadFailed();
    }

    public interface SearchCallback<T> {
        void onLoadSuccess(List<T> response);
        void onLoadFailed();
    }

    public interface AddCallback<T> {
        void onAddSuccess(@NonNull T response);
        void onAddFailed();
    }

    public interface EditCallback<T> {
        void onEditSuccess(@NonNull T response);
        void onEditFailed();
    }

    public interface DeleteCallback<T> {
        void onDeleteSuccess(@NonNull T produk);
        void onDeleteFailed();
    }
}
