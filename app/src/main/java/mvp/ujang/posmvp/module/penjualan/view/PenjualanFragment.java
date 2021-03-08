package mvp.ujang.posmvp.module.penjualan.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.PenjualanAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.penjualan.PenjualanContract;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;
import mvp.ujang.posmvp.module.penjualan.presenter.PenjualanPresenter;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.module.keranjang.view.KeranjangActivity;
import mvp.ujang.posmvp.usecase.kategori.KategoriUsecase;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.usecase.penjualan.PenjualanUsecase;
import mvp.ujang.posmvp.utils.Common;

public class PenjualanFragment extends BaseFragment implements PenjualanContract.PenjualanView {

    //UI Component
    private RecyclerView recyclerView;
    private ImageView imgProduk;
    private TextView tvNamaProduk;
    private TextView tvDeskripsiProduk;
    private TextView tvStokProduk;
    private TextView tvHargaProduk;
    private TextView tvQty;
    private Button btnAddQty;
    private Button btnMinQty;
    private Button btnSelesai;
    private Spinner spinner;
    private SearchView searchView;
    private LinearLayout cartDetail;
    private TextView totalProduk;
    private TextView totalPembayaran;
    private Button buttonCheckout;
    //Context Component
    private Context context;

    private PenjualanPresenter penjualanPresenter;
    private PenjualanAdapter adapter;
    private ArrayAdapter kategoriAdpater;
    private List<Penjualan> produkList = new ArrayList<>();
    private List<Kategori> kategoriList = new ArrayList<>();
    private List<String> itemsKategori = new ArrayList<>();

    private String TAG = PenjualanFragment.class.getSimpleName();




    public PenjualanFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penjualan, null);
        context = getActivity().getApplicationContext();
        penjualanPresenter  = new PenjualanPresenter(PenjualanUsecase.getInstance(context),
                                                    KategoriUsecase.getInstance(context),
                                                    KeranjangUsecase.getInstance(context),
                                              this,context);

        findViews(view);
        initViews(view);
        initListeners(view);
        showCartDetail();
        fetchData();
        return view;
    }

    public static PenjualanFragment newInstance(int tabSelected) {
        PenjualanFragment fragment = new PenjualanFragment();
        Bundle args = new Bundle();
        args.putInt("position", tabSelected);
        fragment.setArguments(args);
        return fragment;
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
        cartDetail   = view.findViewById(R.id.cartDetail);
        totalProduk  = view.findViewById(R.id.totalProduk);
        totalPembayaran = view.findViewById(R.id.totalPembayaran);
        buttonCheckout= view.findViewById(R.id.buttonCheckout);
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
                Penjualan param = new Penjualan();
                if (spinner.getSelectedItemPosition() > 0)
                    param.setIdKategori(kategoriList.get(spinner.getSelectedItemPosition()-1).getIdKategori());
                else
                    param.setIdKategori("0");
                param.setNamaBarang(searchView.getQuery().toString());
                penjualanPresenter.searchPenjualan(param);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, KeranjangActivity.class);
                startActivity(i);
            }
        });
    }


    public void generateList(){
        adapter = new PenjualanAdapter(getContext(), produkList,this);
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
        penjualanPresenter.loadPenjualan();
        penjualanPresenter.loadKategori();
    }

    @Override
    public void listProduk(List<Penjualan> response) {
        produkList.clear();
        produkList.addAll(response);
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
    public void listKeranjang(List<Keranjang> response) {

        if(response.size() > 0){
            int total = 0;
            int totalItem = 0;
            for (int i = 0;i< response.size();i++){
                total +=  (Integer.parseInt(response.get(i).getHargaJualBarang())*Integer.parseInt(response.get(i).getJumlah()));
                totalItem += Integer.parseInt(response.get(i).getJumlah());
            }

            totalProduk.setText(response.size()+"Barang ("+totalItem+" Item)");
            totalPembayaran.setText(Common.convertToRupiah(String.valueOf(total)));
            cartDetail.setVisibility(View.VISIBLE);
        } else {
            cartDetail.setVisibility(View.GONE);
        }

    }

    @Override
    public void detailProduk(final Penjualan produk) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        tvQty             = view.findViewById(R.id.tv_qty);
        btnAddQty         = view.findViewById(R.id.btn_add_qty);
        btnMinQty         = view.findViewById(R.id.btn_min_qty);
        btnSelesai        = view.findViewById(R.id.btn_selesai);
        imgProduk         = view.findViewById(R.id.img_produk);
        tvNamaProduk      = view.findViewById(R.id.tv_nama_produk);
        tvStokProduk      = view.findViewById(R.id.tv_stok_produk);
        tvHargaProduk     = view.findViewById(R.id.tv_harga_produk);
        tvDeskripsiProduk = view.findViewById(R.id.tv_deskripsi_produk);

        tvNamaProduk.setText(produk.getNamaBarang());
        tvStokProduk.setText(produk.getStokBarang());
        tvHargaProduk.setText(Common.convertToRupiah(produk.getHargaJualBarang()));
        tvDeskripsiProduk.setText(produk.getDeskripsiBarang());
        Glide.with(context)
                .load(Common.convertToByte(produk.getGambarBarang()))
                .asBitmap()
                .into(imgProduk);

        tvQty.setText(produk.getJumlah());

        if (Integer.parseInt(produk.getStokBarang()) < 1){
            btnAddQty.setEnabled(false);
            btnMinQty.setEnabled(false);
            tvQty.setText("0");
        }

        btnAddQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tvQty.getText().toString()) < Integer.parseInt(produk.getStokBarang()))
                    tvQty.setText(String.valueOf(Integer.parseInt(tvQty.getText().toString())+1));
            }
        });

        btnMinQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tvQty.getText().toString().equals("1"))
                    tvQty.setText(String.valueOf(Integer.parseInt(tvQty.getText().toString())-1));
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvQty.getText().toString()) > 0){
                    Keranjang keranjang = new Keranjang();
                    keranjang.setKdBarang(produk.getKdBarang());
                    keranjang.setJumlah(tvQty.getText().toString());
                    penjualanPresenter.addKeranjang(keranjang);
                    penjualanPresenter.loadPenjualan();
                    showCartDetail();
                }
                dialog.hide();
            }
        });

        dialog.setContentView(view);
        dialog.show();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem search = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
    }

    private void search(final SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Penjualan param = new Penjualan();
                if (spinner.getSelectedItemPosition() > 0)
                    param.setIdKategori(kategoriList.get(spinner.getSelectedItemPosition()-1).getIdKategori());
                else
                    param.setIdKategori("0");
                param.setNamaBarang(query);
                penjualanPresenter.searchPenjualan(param);
                adapter.notifyDataSetChanged();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @Override
    public void setPresenter(@NonNull PenjualanContract.Presenter presenter) {

    }

    public void showCartDetail(){
        cartDetail.setVisibility(View.GONE);
        penjualanPresenter.loadKeranjang();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
        showCartDetail();
    }


}
