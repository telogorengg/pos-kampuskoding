package mvp.ujang.posmvp.module.kategori.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.KategoriAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.kategori.KategoriContract;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.kategori.presenter.KategoriPresenter;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.utils.Common;

public class KategoriFragment extends BaseFragment implements KategoriContract.KategoriView {

    private Context context;

    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private ImagePicker imagePicker;
    private KategoriAdapter adapter;
    private List<Kategori> kategoriList = new ArrayList<>();
    private KategoriPresenter kategoriPresenter;
    private EditText  namaKategori;
    private ImageView imgKategori;
    private String filePath="";
    public KategoriFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kategori, null);
        context = getActivity().getApplicationContext();
        kategoriPresenter = new KategoriPresenter( KategoriUsecase.getInstance(context),this,context);


        findViews(view);
        initViews(view);
        initListeners(view);
        fetchData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        addButton   = view.findViewById(R.id.add_button);
    }

    @Override
    public void initViews(View view) {
        generateList();
    }

    @Override
    public void initListeners(View view) {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void generateList(){
        adapter = new KategoriAdapter(getContext(), kategoriList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void fetchData(){
        kategoriPresenter.loadKategori();
    }

    @Override
    public void listKategori(List<Kategori> response) {
        Log.d("dataNya",response.toString());
        kategoriList.clear();
        kategoriList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addKategori(Kategori response) {
        kategoriPresenter.loadKategori();
    }

    @Override
    public void setPresenter(@NonNull KategoriContract.Presenter presenter) {

    }


    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Kategori Barang");
        final View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.add_kategori, (ViewGroup) getView(), false);
        namaKategori = viewInflated.findViewById(R.id.tv_nama_kategori);
        imgKategori  = viewInflated.findViewById(R.id.imgKategori);
        builder.setView(viewInflated);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (namaKategori.getText().toString().equals("")){
                    Toast.makeText(context,"Nama kategori harus diisi",Toast.LENGTH_SHORT).show();
                    return;
                }

                Kategori kategori = new Kategori();
                kategori.setNamaKategori(namaKategori.getText().toString());
                kategori.setGambarKategori(Common.encodeToBase64(Common.convertImageViewToBitmap(imgKategori), Bitmap.CompressFormat.JPEG, 100));
                kategoriPresenter.addKategori(kategori);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        imgKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        builder.show();
    }

    private void selectImage() {
        imagePicker = new ImagePicker();
        imagePicker.withFragment(this).withCompression(true).start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            imagePicker.addOnCompressListener(new ImageCompressionListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompressed(String filePath) {
                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                    imgKategori.setImageBitmap(selectedImage);
                }
            });
            String filePath = imagePicker.getImageFilePath(data);
            if (filePath != null) {
                Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                imgKategori.setImageBitmap(selectedImage);
            }
        }
    }

}
