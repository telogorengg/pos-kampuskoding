package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.dashboard.model.DashboardDetail;
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.utils.Common;

public class DashboardBarangAdapter extends RecyclerView.Adapter<DashboardBarangAdapter.MyViewHolder> {

    private Context mContext;
    private List<DashboardDetail> barangList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaBarang;
        public TextView jumlah;
        public TextView nilai;

        public MyViewHolder(View view) {
            super(view);
            namaBarang = view.findViewById(R.id.namaBarang);
            jumlah = view.findViewById(R.id.jumlah);
            nilai = view.findViewById(R.id.nilai);
        }
    }

    public DashboardBarangAdapter(Context mContext, List<DashboardDetail> barangList) {
        this.mContext = mContext;
        this.barangList = barangList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_dashboard_barang, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        DashboardDetail dashboardDetail = barangList.get(position);
        holder.namaBarang.setText(dashboardDetail.getNamaBarang());
        holder.jumlah.setText(dashboardDetail.getTotal()+" (Pcs)");
        holder.nilai.setText(Common.convertToRupiah(dashboardDetail.getTotalHarga()));
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }
}