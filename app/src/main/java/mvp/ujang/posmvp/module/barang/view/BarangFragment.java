package mvp.ujang.posmvp.module.barang.view;

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
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.BarangAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.barang.ProdukContract;
import mvp.ujang.posmvp.module.barang.presenter.BarangPresenter;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.barang.BarangUsecase;
import mvp.ujang.posmvp.utils.Common;

public class BarangFragment extends BaseFragment implements ProdukContract.BarangView {

    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private BarangAdapter adapter;
    private Spinner spinner;
    private ImagePicker imagePicker;

    private EditText namaBarang;
    private EditText deskripsiBarang;
    private EditText hargaBeli;
    private EditText hargaJualBarang;
    private EditText hargaBeliBarang;
    private EditText jumlah;
    private EditText satuan;
    private ImageView imgBarang;
    private Spinner  spinnerKategori;
    private SearchView searchView;
    //Context Component
    private Context context;

    private BarangPresenter barangPresenter;
    private ArrayAdapter    kategoriAdpater;
    private List<Barang> barangList = new ArrayList<>();
    private List<Kategori>  kategoriList = new ArrayList<>();
    private List<String>    itemsKategori = new ArrayList<>();

    public BarangFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produk, null);
        context = getActivity().getApplicationContext();
        barangPresenter = new BarangPresenter(BarangUsecase.getInstance(context),
                KategoriUsecase.getInstance(context),
                this,context);

        findViews(view);
        initViews(view);
        initListeners(view);
        fetchData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        spinner      = view.findViewById(R.id.kategori_spinner);
        addButton    = view.findViewById(R.id.add_button);

    }

    @Override
    public void initViews(View view) {
        generateList();
        generateKategori();
    }

    @Override
    public void initListeners(View view) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Barang param = new Barang();
                if (spinner.getSelectedItemPosition() > 0)
                    param.setIdKategori(kategoriList.get(spinner.getSelectedItemPosition()-1).getIdKategori());
                else
                    param.setIdKategori("0");

                param.setNamaBarang("");
                barangPresenter.searchBarang(param);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void generateList(){
        adapter = new BarangAdapter(getContext(), barangList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void generateKategori(){
        kategoriAdpater = new ArrayAdapter(context, R.layout.spinner_main, itemsKategori);
        spinner.setAdapter(kategoriAdpater);
    }


    public void fetchData(){
        barangPresenter.loadBarang();
        barangPresenter.loadKategori();
    }

    @Override
    public void listBarang(List<Barang> response) {
        barangList.clear();
        barangList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void listKategori(List<Kategori> response) {
        kategoriList.clear();
        itemsKategori.clear();
        kategoriList.addAll(response);
        itemsKategori.add("KATEGORI");
        for(Kategori item : kategoriList)
            itemsKategori.add(item.getNamaKategori().toUpperCase());

        kategoriAdpater.notifyDataSetChanged();
    }

    @Override
    public void addBarang(Barang response) {
        barangPresenter.loadBarang();
    }

    @Override
    public void detailBarang(Barang response) {

    }

    @Override
    public void setPresenter(@NonNull ProdukContract.Presenter presenter) {

    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Barang");
        final View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.add_produk, (ViewGroup) getView(), false);

        namaBarang      = viewInflated.findViewById(R.id.nama_barang);
        deskripsiBarang = viewInflated.findViewById(R.id.deskripsi_barang);
        hargaJualBarang = viewInflated.findViewById(R.id.harga_jual_barang);
        jumlah          = viewInflated.findViewById(R.id.jumlah);
        satuan          = viewInflated.findViewById(R.id.satuan);
        imgBarang       = viewInflated.findViewById(R.id.imgBarang);
        spinnerKategori = viewInflated.findViewById(R.id.spinner);
        hargaBeliBarang = viewInflated.findViewById(R.id.harga_beli_barang);
        builder.setView(viewInflated);
        spinnerKategori.setAdapter(kategoriAdpater);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Barang barang = new Barang();
                barang.setKdBarang("BRNG_"+ Common.getTextDateTime());
                barang.setNamaBarang(namaBarang.getText().toString());
                barang.setDeskripsiBarang(deskripsiBarang.getText().toString());
                barang.setHargaJualBarang(hargaJualBarang.getText().toString());
                barang.setStokBarang(jumlah.getText().toString());
                barang.setSatuan(satuan.getText().toString());
                barang.setIdKategori(kategoriList.get(spinnerKategori.getSelectedItemPosition()-1).getIdKategori());
                barang.setGambarBarang(Common.encodeToBase64(Common.convertImageViewToBitmap(imgBarang), Bitmap.CompressFormat.JPEG, 100));
                barang.setHargaBeliBarang(hargaBeliBarang.getText().toString());
                barangPresenter.addBarang(barang);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        imgBarang.setOnClickListener(new View.OnClickListener() {
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
                    imgBarang.setImageBitmap(selectedImage);
                }
            });
            String filePath = imagePicker.getImageFilePath(data);
            if (filePath != null) {
                Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                imgBarang.setImageBitmap(selectedImage);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem search = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Barang param = new Barang();
                if (spinner.getSelectedItemPosition() > 0)
                    param.setIdKategori(kategoriList.get(spinner.getSelectedItemPosition()-1).getIdKategori());
                else
                    param.setIdKategori("0");
                param.setNamaBarang(query);
                barangPresenter.searchBarang(param);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
    }
}
