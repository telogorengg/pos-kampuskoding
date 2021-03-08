package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.keranjang.KeranjangContract;
import mvp.ujang.posmvp.module.keranjang.model.Keranjang;
import mvp.ujang.posmvp.utils.Common;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.MyViewHolder> {

    private Context mContext;
    private List<Keranjang> keranjangList;
    private KeranjangContract.KeranjangView view;
    private OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public TextView tvHarga;
        public TextView tvQty;
        public Button   btnMinQty;
        public Button   btnAddQty;
        public Button   btnPesan;
        public ImageView imgKeranjang;


        public MyViewHolder(View view) {
            super(view);
            tvNama          = view.findViewById(R.id.tv_nama_produk);
            tvHarga         = view.findViewById(R.id.tv_harga_produk);
            imgKeranjang    = view.findViewById(R.id.img_produk);
            btnPesan        = view.findViewById(R.id.btn_pesan);
            btnMinQty       = view.findViewById(R.id.btn_min_qty);
            btnAddQty       = view.findViewById(R.id.btn_add_qty);
            tvQty           = view.findViewById(R.id.tv_qty);
        }
    }

    public KeranjangAdapter(Context mContext, List<Keranjang> keranjangList, KeranjangContract.KeranjangView view) {
        this.mContext   = mContext;
        this.keranjangList = keranjangList;
        this.view       = view;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_keranjang, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Keranjang keranjang = keranjangList.get(position);
        holder.tvNama.setText(keranjang.getNamaBarang());
        holder.tvHarga.setText(Common.convertToRupiah(String.valueOf((Integer.parseInt(keranjang.getHargaJualBarang())*Integer.parseInt(keranjang.getJumlah())))));
        holder.tvQty.setText(keranjang.getJumlah());
        holder.btnMinQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.tvQty.getText().toString().equals("1"))
                    holder.tvQty.setText(String.valueOf(Integer.parseInt(holder.tvQty.getText().toString())-1));

                onItemClickListener.onItemClick(v,position,holder.tvQty.getText().toString());
            }
        });

        holder.btnAddQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(holder.tvQty.getText().toString()) < Integer.parseInt(keranjang.getStokBarang()))//!holder.tvQty.getText().toString().equals("1"))
                    holder.tvQty.setText(String.valueOf(Integer.parseInt(holder.tvQty.getText().toString())+1));

                onItemClickListener.onItemClick(v,position,holder.tvQty.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return keranjangList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position,String qty);
    }
}