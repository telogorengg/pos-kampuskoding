package mvp.ujang.posmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mvp.ujang.posmvp.helper.PermissionHelper;
import mvp.ujang.posmvp.module.dashboard.view.DashboardFragment;
import mvp.ujang.posmvp.module.penjualan.view.PenjualanFragment;
import mvp.ujang.posmvp.base.BaseActivity;
import mvp.ujang.posmvp.module.kategori.view.KategoriFragment;
import mvp.ujang.posmvp.module.keranjang.view.KeranjangActivity;
import mvp.ujang.posmvp.module.barang.view.BarangFragment;
import mvp.ujang.posmvp.module.transaksi.view.TransaksiFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private PermissionHelper permissionHelper;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionHelper = new PermissionHelper(this);
        findViews();
        initViews();
        initListeners();
        checkAndRequestPermissions();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        setDrawer();
    }

    @Override
    public void initListeners() {
    }

    private void setDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_penjualan);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_keranjang) {
            i = new Intent(this, KeranjangActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_penjualan:
                getSupportActionBar().setTitle("Barang");
                fragment = new PenjualanFragment();
                break;
            case R.id.nav_struk_penjualan:
                getSupportActionBar().setTitle("Transaksi Penjualan");
                fragment = new TransaksiFragment();
                break;
            case R.id.nav_produk_kategori:
                getSupportActionBar().setTitle("Kategori");
                fragment = new KategoriFragment();
                break;
            case R.id.nav_produk_item:
                getSupportActionBar().setTitle("Barang");
                fragment = new BarangFragment();
                break;
            case R.id.nav_laporan_harian:
                getSupportActionBar().setTitle("Laporan");
                fragment = new DashboardFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private boolean checkAndRequestPermissions() {
        permissionHelper.permissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone() {

            }
        });

        permissionHelper.checkAndRequestPermissions();

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequestCallBack(requestCode, permissions, grantResults);
    }





}
