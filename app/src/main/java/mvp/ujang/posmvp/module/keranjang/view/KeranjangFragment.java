package mvp.ujang.posmvp.module.keranjang.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.KeranjangAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.module.keranjang.KeranjangContract;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.module.keranjang.presenter.KeranjangPresenter;

public class KeranjangFragment extends BaseFragment implements KeranjangContract.KeranjangView{

    //Context Component
    private Context context;

    private KeranjangPresenter keranjangPresenter;
    private List<Keranjang> keranjangList = new ArrayList<>();
    private List<Kategori> kategoriList = new ArrayList<>();
    private List<String> itemsKategori = new ArrayList<>();
    private KeranjangAdapter adapter;
    private ArrayAdapter kategoriAdpater;


    public KeranjangFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keranjang, null);
        context = getActivity().getApplicationContext();
//        keranjangPresenter  = new KeranjangPresenter(KeranjangUsecase.getInstance(context), this,context);

        fetchData();
        findViews(view);
        initViews(view);
        initListeners(view);
        return view;
    }

    public static KeranjangFragment newInstance(int tabSelected) {
        KeranjangFragment fragment = new KeranjangFragment();
        Bundle args = new Bundle();
        args.putInt("position", tabSelected);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void findViews(View view) {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void initListeners(View view) {

    }

    public void fetchData(){
        keranjangPresenter.loadKeranjang();
        keranjangPresenter.loadKategori();
    }

    @Override
    public void listKeranjang(List<Keranjang> response) {

    }

    @Override
    public void listKategori(List<Kategori> response) {

    }

    @Override
    public void setPresenter(@NonNull KeranjangContract.Presenter presenter) {

    }
}
