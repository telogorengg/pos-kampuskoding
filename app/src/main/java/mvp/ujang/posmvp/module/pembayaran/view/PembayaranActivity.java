package mvp.ujang.posmvp.module.pembayaran.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.base.BaseActivity;
import mvp.ujang.posmvp.module.pembayaran.PembayaranContract;
import mvp.ujang.posmvp.module.pembayaran.model.Pembayaran;
import mvp.ujang.posmvp.module.pembayaran.presenter.PembayaranPresenter;
import mvp.ujang.posmvp.usecase.pembayaran.PembayaranUsecase;
import mvp.ujang.posmvp.utils.Common;

public class PembayaranActivity extends BaseActivity implements PembayaranContract.PembayaranView{

    //UI Component
    private TextView totalPembayaran;
    private TextView totalBayar;
    private TextView totalKembalian;
    private EditText bayar;
    private Button buttonProcess;
    private Button buttonNext;
    private RelativeLayout pembayaranAwal;
    private RelativeLayout pembayaranAkhir;
    private String total;
    //Context Component
    private Context context;

    private PembayaranPresenter pembayaranPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        context = this;
        pembayaranPresenter = new PembayaranPresenter(PembayaranUsecase.getInstance(context),this,context);
        findViews();
        initViews();
        initListeners();
        fetchData();
    }


    public void fetchData(){
        pembayaranPresenter.totalPembayaran();
    }

    @Override
    public void findViews() {
        totalPembayaran = findViewById(R.id.totalPembayaran);
        totalBayar      = findViewById(R.id.totalBayar);
        bayar           = findViewById(R.id.bayar);
        totalKembalian  = findViewById(R.id.totalKembalian);
        buttonProcess   = findViewById(R.id.buttonProcess);
        pembayaranAwal  = findViewById(R.id.pembayaranAwal);
        pembayaranAkhir = findViewById(R.id.pembayaranAkhir);
        buttonNext      = findViewById(R.id.buttonNext);
    }

    @Override
    public void initViews() {
        // toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void initListeners() {
        buttonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    Toast.makeText(context,"Uang tunai tidak sesuai",Toast.LENGTH_SHORT).show();
                    return;
                }

                messageBox("Jumlah uang sudah benar ?");
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    @Override
    public void totalPembayaran(List<Pembayaran> response) {
        total = response.get(0).getTotalPembayaran();
        totalPembayaran.setText(Common.convertToRupiah(response.get(0).getTotalPembayaran()));
    }

    @Override
    public void setPresenter(@NonNull PembayaranContract.Presenter presenter) {

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
                        addPembayaran();
                        pembayaranAwal.setVisibility(View.GONE);
                        pembayaranAkhir.setVisibility(View.VISIBLE);
                    }

                }).show();
    }


    private void addPembayaran(){
        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setTotalPembayaran(total);
        pembayaran.setTglTransaksi(Common.getDateTime());
        pembayaran.setKdTransaksi("INV-"+Common.getTextDateTime());
        pembayaran.setTunai(bayar.getText().toString());
        pembayaranPresenter.addPembayaran(pembayaran);
        totalBayar.setText(totalPembayaran.getText().toString());
        totalKembalian.setText(Common.convertToRupiah(String.valueOf(Integer.parseInt(bayar.getText().toString())- Integer.parseInt(total))));
    }

    private boolean validate(){
        if (bayar.getText().toString().equals(""))
            return true;
        if (Integer.parseInt(bayar.getText().toString()) < Integer.parseInt(total))
            return true;

        return false;
    }
}
