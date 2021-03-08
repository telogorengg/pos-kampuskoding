package mvp.ujang.posmvp.module.refund.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.RefundDetailAdapter;
import mvp.ujang.posmvp.base.BaseActivity;
import mvp.ujang.posmvp.module.refund.RefundContract;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;
import mvp.ujang.posmvp.module.refund.presenter.RefundDetailPresenter;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;
import mvp.ujang.posmvp.usecase.refund.RefundDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class RefundDetailActivity extends BaseActivity implements RefundContract.StrukView,RefundDetailAdapter.SelectedRefund {

    private RecyclerView recyclerView;
    private LinearLayout refundLayout;
    private RefundDetailAdapter adapter;
    private TextView jumlahBarang;
    private TextView totalRefund;
    //Context Component
    private Context context;

    private RefundDetailPresenter strukDetailPresenter;
    private ArrayList<RefundDetail> strukList = new ArrayList<>();
    private ArrayList<Transaksi> newTransaksiList = new ArrayList<>();
    private String transaksi  = "";
    private String tglTransaksi = "";
    private String totalTransaksi = "";
    private String uangTunai = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_detail);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            transaksi       = extras.getString("kdTransaksi");
            tglTransaksi    = extras.getString("tglTransaksi");
            totalTransaksi  = extras.getString("totalTransaksi");
            uangTunai       = extras.getString("tunai");
        }

        context = this;
        strukDetailPresenter  = new RefundDetailPresenter(RefundDetailUsecase.getInstance(context),
                this,context);

        findViews();
        initViews();
        initListeners();
        fetchData();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initViews() {
        getSupportActionBar().setTitle("Refund");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        generateList();

        refundLayout = findViewById(R.id.refundLayout);
        totalRefund = findViewById(R.id.totalRefund);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    public void generateList(){
        adapter = new RefundDetailAdapter(context, strukList);
        adapter.setSelectedRefund(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void fetchData(){
        RefundDetail strukDetail = new RefundDetail();
        strukDetail.setKdTransaksi(transaksi);
        strukDetailPresenter.loadStruk(strukDetail);
        strukDetailPresenter.loadTotalRefund(strukDetail);
    }

    @Override
    public void listStruk(List<RefundDetail> response) {
        strukList.clear();
        strukList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void totalRefund(List<RefundDetail> response) {
        totalRefund.setText(Common.convertToRupiah(response.get(0).getTotalRefund()));
    }

    @Override
    public void setPresenter(@NonNull RefundContract.Presenter presenter) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refund, menu);
        return true;
    }


    public void showDialogChange(int position){
        final RefundDetail refundDetail = strukList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(refundDetail.getNamaBarang());
        final View viewInflated = LayoutInflater.from(context).inflate(R.layout.refund_produk, refundLayout, false);


        builder.setView(viewInflated);
        jumlahBarang = viewInflated.findViewById(R.id.tv_qty);
        Button btnAddQty = viewInflated.findViewById(R.id.btn_add_qty);
        Button btnMinQty = viewInflated.findViewById(R.id.btn_min_qty);

        btnAddQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(jumlahBarang.getText().toString()) < Integer.parseInt(refundDetail.getJumlah()))
                    jumlahBarang.setText(String.valueOf(Integer.parseInt(jumlahBarang.getText().toString())+1));
            }
        });

        btnMinQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(jumlahBarang.getText().toString()) > 1)
                    jumlahBarang.setText(String.valueOf(Integer.parseInt(jumlahBarang.getText().toString())-1));
            }
        });

        jumlahBarang.setText(refundDetail.getJumlah());
        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RefundDetail data = new RefundDetail();
                data.setKdTransaksi(refundDetail.getKdTransaksi());
                data.setKdBarang(refundDetail.getKdBarang());
                data.setJumlah(jumlahBarang.getText().toString());
                data.setHargaBarang(refundDetail.getHargaBarang());
                strukDetailPresenter.refundBarang(data);
                dialog.dismiss();
                fetchData();
            }
        });


        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void onSelectedItem(int position) {
        showDialogChange(position);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
