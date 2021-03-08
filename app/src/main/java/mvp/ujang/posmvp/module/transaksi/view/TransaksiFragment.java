package mvp.ujang.posmvp.module.transaksi.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.adapter.TransaksiAdapter;
import mvp.ujang.posmvp.base.BaseFragment;
import mvp.ujang.posmvp.module.transaksi.TransaksiContract;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;
import mvp.ujang.posmvp.module.transaksi.presenter.TransaksiPresenter;
import mvp.ujang.posmvp.module.transaksidetail.view.TransaksiDetailActivity;
import mvp.ujang.posmvp.usecase.transaksi.TransaksiUsecase;
import mvp.ujang.posmvp.utils.RecyclerItemClickListener;

public class TransaksiFragment extends BaseFragment implements TransaksiContract.TransaksiView {

    private RecyclerView recyclerView;
    private TransaksiAdapter adapter;
    //Context Component
    private Context context;

    private TransaksiPresenter transaksiPresenter;
    private ArrayList<Transaksi> transaksiList = new ArrayList<>();
    private ArrayList<Transaksi> newTransaksiList = new ArrayList<>();
    public TransaksiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_struk, null);
        context = getActivity().getApplicationContext();
        transaksiPresenter = new TransaksiPresenter(TransaksiUsecase.getInstance(context),
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
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public void initViews(View view) {
        generateList();
    }

    @Override
    public void initListeners(View view) {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,recyclerView,
                new RecyclerItemClickListener.OnItemClickListener(){

                    @Override
                    public void onItemClick(View view, int position) {
                        if (!newTransaksiList.get(position).isStatus()){
                            Intent i = new Intent(context, TransaksiDetailActivity.class);
                            i.putExtra("kdTransaksi", newTransaksiList.get(position).getKdTransaksi());
                            i.putExtra("tglTransaksi", newTransaksiList.get(position).getTanggal());
                            i.putExtra("totalTransaksi", newTransaksiList.get(position).getTotalPembayaran());
                            i.putExtra("tunai", newTransaksiList.get(position).getTunai());
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                    }
                }));
    }

    public void generateList(){
        adapter = new TransaksiAdapter(getContext(), newTransaksiList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void listTransaksi(List<Transaksi> response) {
        transaksiList.clear();
        newTransaksiList.clear();
        transaksiList.addAll(response);

        for (int i = 0; i< transaksiList.size(); i++){
            Transaksi item = new Transaksi();
            item.setTanggal(transaksiList.get(i).getTanggal());
            item.setStatus(true);

            if (i == 0)
                newTransaksiList.add(item);
            else
                if (!transaksiList.get(i).getGroupingDate().equals(transaksiList.get(i-1).getGroupingDate()))
                    newTransaksiList.add(item);


            newTransaksiList.add(transaksiList.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull TransaksiContract.Presenter presenter) {

    }


    public void fetchData(){
        transaksiPresenter.loadTransaksi();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }
}

