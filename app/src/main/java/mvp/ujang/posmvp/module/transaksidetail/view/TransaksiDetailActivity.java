package mvp.ujang.posmvp.module.transaksidetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.TransaksiDetailAdapter;
import mvp.ujang.posmvp.base.BaseActivity;
import mvp.ujang.posmvp.module.refund.view.RefundDetailActivity;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;
import mvp.ujang.posmvp.module.transaksidetail.TransaksiDetailContract;
import mvp.ujang.posmvp.module.transaksidetail.model.TransksiDetail;
import mvp.ujang.posmvp.module.transaksidetail.presenter.TransaksiDetailPresenter;
import mvp.ujang.posmvp.usecase.transaksidetail.TransaksiDetailUsecase;
import mvp.ujang.posmvp.utils.Common;

public class TransaksiDetailActivity extends BaseActivity implements TransaksiDetailContract.TransaksiView {

    private RecyclerView recyclerView;
    private TextView tanggal;
    private TextView kdTransaksi;
    private TextView total;
    private TextView tunai;
    private TextView kembalian;
    private TransaksiDetailAdapter adapter;
    //Context Component
    private Context context;

    private TransaksiDetailPresenter transaksiDetailPresenter;
    private ArrayList<TransksiDetail> strukList = new ArrayList<>();
    private ArrayList<Transaksi> newTransaksiList = new ArrayList<>();
    private String transaksi  = "";
    private String tglTransaksi = "";
    private String totalTransaksi = "";
    private String uangTunai = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk_detail);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            transaksi       = extras.getString("kdTransaksi");
            tglTransaksi    = extras.getString("tglTransaksi");
            totalTransaksi  = extras.getString("totalTransaksi");
            uangTunai       = extras.getString("tunai");
        }

        context = this;
        transaksiDetailPresenter = new TransaksiDetailPresenter(TransaksiDetailUsecase.getInstance(context),
                this,context);

        findViews();
        initViews();
        initListeners();
        fetchData();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
        tanggal      = findViewById(R.id.tanggal);
        kdTransaksi  = findViewById(R.id.kd_transaksi);
        total        = findViewById(R.id.total);
        tunai        = findViewById(R.id.tunai);
        kembalian    = findViewById(R.id.kembalian);
    }

    @Override
    public void initViews() {
        getSupportActionBar().setTitle("#"+transaksi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        generateList();

        tanggal.setText(Common.convertDateLong(tglTransaksi));
        kdTransaksi.setText("#"+transaksi);
        total.setText(Common.convertToRupiah(totalTransaksi));
        tunai.setText(Common.convertToRupiah(uangTunai));
        kembalian.setText(Common.convertToRupiah(String.valueOf(Integer.parseInt(uangTunai)-Integer.parseInt(totalTransaksi))));
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

            case R.id.refund:
                Intent i = new Intent(context, RefundDetailActivity.class);
                i.putExtra("kdTransaksi",transaksi);
                i.putExtra("tglTransaksi",tglTransaksi);
                i.putExtra("totalTransaksi",totalTransaksi);
                i.putExtra("tunai",uangTunai);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void generateList(){
        adapter = new TransaksiDetailAdapter(context, strukList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void fetchData(){
        TransksiDetail transksiDetail = new TransksiDetail();
        transksiDetail.setKdTransaksi(transaksi);
        transaksiDetailPresenter.loadTransaksi(transksiDetail);
    }

    @Override
    public void listTransaksi(List<TransksiDetail> response) {
        strukList.clear();
        strukList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull TransaksiDetailContract.Presenter presenter) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refund, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
