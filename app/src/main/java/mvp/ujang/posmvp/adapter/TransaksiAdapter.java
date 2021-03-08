package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.transaksi.model.Transaksi;
import mvp.ujang.posmvp.utils.Common;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.MyViewHolder> {

    private Context mContext;
    private List<Transaksi> transaksiList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView     tvTotalBayar;
        public TextView     tvWaktu;
        public TextView     tvKdTransaksi;
        public TextView     header;
        public RelativeLayout body;
        public MyViewHolder(View view) {
            super(view);
            tvTotalBayar    = view.findViewById(R.id.tv_total);
            tvWaktu         = view.findViewById(R.id.tv_waktu);
            tvKdTransaksi   = view.findViewById(R.id.tv_kdTransaksi);
            header          = view.findViewById(R.id.header);
            body            = view.findViewById(R.id.body);
        }
    }

    public TransaksiAdapter(Context mContext, List<Transaksi> transaksiList) {
        this.mContext = mContext;
        this.transaksiList = transaksiList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_struk, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Transaksi transaksi = transaksiList.get(position);
        if (transaksi.isStatus()){
            holder.header.setVisibility(View.VISIBLE);
            holder.body.setVisibility(View.GONE);
        }else{
            holder.header.setVisibility(View.GONE);
            holder.body.setVisibility(View.VISIBLE);
            holder.tvTotalBayar.setText(Common.convertToRupiah(transaksi.getTotalPembayaran()));
            holder.tvWaktu.setText(transaksi.getWaktu());
            holder.tvKdTransaksi.setText("#"+ transaksi.getKdTransaksi());
        }
        holder.header.setText(Common.convertDateLong(transaksi.getTanggal()));

    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }
}