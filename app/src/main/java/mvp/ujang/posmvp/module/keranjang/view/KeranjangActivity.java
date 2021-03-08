package mvp.ujang.posmvp.module.keranjang.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.KeranjangAdapter;
import mvp.ujang.posmvp.base.BaseActivity;
import mvp.ujang.posmvp.module.pembayaran.view.PembayaranActivity;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.keranjang.KeranjangContract;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.module.keranjang.presenter.KeranjangPresenter;
import mvp.ujang.posmvp.usecase.keranjang.KeranjangUsecase;
import mvp.ujang.posmvp.utils.Common;

public class KeranjangActivity extends BaseActivity implements KeranjangContract.KeranjangView,KeranjangAdapter.OnItemClickListener{

    //Context Component
    private RecyclerView recyclerView;
    private TextView subtotal;
    private TextView total;
    private Button buttonProcess;
    private Context context;

    private KeranjangPresenter keranjangPresenter;
    private List<Keranjang> keranjangList = new ArrayList<>();
    private List<Kategori> kategoriList = new ArrayList<>();
    private List<String> itemsKategori = new ArrayList<>();
    private KeranjangAdapter adapter;
    private ArrayAdapter kategoriAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        context = this;
        keranjangPresenter = new KeranjangPresenter(KeranjangUsecase.getInstance(context),this,context);
        findViews();
        initViews();
        initListeners();
        fetchData();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
        subtotal     = findViewById(R.id.subtotal);
        total        = findViewById(R.id.total);
        buttonProcess= findViewById(R.id.buttonProcess);
    }

    @Override
    public void initViews() {
        // toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        generateList();
    }

    @Override
    public void initListeners() {
        buttonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    Toast.makeText(context,"Tidak ada barang dalam keranjang",Toast.LENGTH_SHORT).show();
                    return;
                }

                messageBox("Yakin Akan Pesan ?");
            }
        });
    }

    @Override
    public void listKeranjang(List<Keranjang> response) {
        keranjangList.clear();
        keranjangList.addAll(response);
        adapter.notifyDataSetChanged();
        totalHarga();
    }

    public void totalHarga(){
        int sum = 0;
        for (int i = 0;i<keranjangList.size();i++)
            sum += (Integer.parseInt(keranjangList.get(i).getJumlah())*Integer.parseInt(keranjangList.get(i).getHargaJualBarang()));

        subtotal.setText(String.valueOf(sum));
        total.setText(Common.convertToRupiah(String.valueOf(sum)));
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
    public void setPresenter(@NonNull KeranjangContract.Presenter presenter) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void fetchData(){
        keranjangPresenter.loadKeranjang();
        keranjangPresenter.loadKategori();
    }

    public void generateList(){
        adapter = new KeranjangAdapter(context, keranjangList,this);
        adapter.setOnItemClickListener(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position, String qty) {
        keranjangList.get(position).setJumlah(qty);
        adapter.notifyItemChanged(position);
        keranjangPresenter.editKeranjang(keranjangList.get(position));
        totalHarga();
    }


    private void messageBox(String message ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle( "" )
                .setMessage(message)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialoginterface, int i) {
                    dialoginterface.cancel();
                }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        Intent intent = new Intent(context, PembayaranActivity.class);
                        startActivity(intent);
                    }

                }).show();
    }

    private boolean validate(){
        if (keranjangList.size() == 0)
            return true;

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
