package mvp.ujang.posmvp.module.dashboard.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.base.BaseActivity;
import mvp.ujang.posmvp.module.dashboard.DashboardContract;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.module.dashboard.presenter.DashboardPresenter;
import mvp.ujang.posmvp.usecase.dashboard.DashboardDetailUsecase;
import mvp.ujang.posmvp.usecase.refund.RefundDetailUsecase;

public class DashboardActivity extends BaseActivity implements DashboardContract.DashboardView{

    private Context context;
    private DashboardPresenter dashboardPresenter;


    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard_detail);
        dashboardPresenter = new DashboardPresenter(DashboardDetailUsecase.getInstance(context),
                this,context);

        findViews();
        initViews();
        initListeners();
        fetchData();
    }

    @Override
    public void findViews() {

    }

    @Override
    public void initViews() {
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        generateList();
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
    }

    public void fetchData(){
        chart = (BarChart) findViewById(R.id.chart1);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();

        Bardataset = new BarDataSet(BARENTRY, "Projects");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);
    }

    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(2f, 0));
        BARENTRY.add(new BarEntry(4f, 1));
        BARENTRY.add(new BarEntry(6f, 2));
        BARENTRY.add(new BarEntry(8f, 3));
        BARENTRY.add(new BarEntry(7f, 4));
        BARENTRY.add(new BarEntry(3f, 5));

    }

    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("January");
        BarEntryLabels.add("February");
        BarEntryLabels.add("March");
        BarEntryLabels.add("April");
        BarEntryLabels.add("May");
        BarEntryLabels.add("June");

    }

    @Override
    public void setPresenter(@NonNull DashboardContract.Presenter presenter) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refund, menu);
        return true;
    }


    @Override
    public void listInfoPenjualan(List<DashboardDetail> response) {

    }

    @Override
    public void listInfoGross(List<DashboardDetail> response) {

    }

    @Override
    public void listInfoBarang(List<DashboardDetail> response) {

    }
}
