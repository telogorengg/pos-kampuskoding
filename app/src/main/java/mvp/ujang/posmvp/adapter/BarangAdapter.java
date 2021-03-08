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
import mvp.ujang.posmvp.module.barang.model.Barang;
import mvp.ujang.posmvp.utils.Common;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    private Context mContext;
    private List<Barang> barangList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public ImageView imgProduk;
        public TextView tvHarga;

        public MyViewHolder(View view) {
            super(view);
            tvNama = view.findViewById(R.id.tv_nama_produk);
            tvHarga = view.findViewById(R.id.tv_harga_produk);
            imgProduk = view.findViewById(R.id.img_produk);
        }
    }

    public BarangAdapter(Context mContext, List<Barang> barangList) {
        this.mContext = mContext;
        this.barangList = barangList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_produk, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Barang barang = barangList.get(position);
        holder.tvNama.setText(barang.getNamaBarang());
        holder.tvHarga.setText(barang.getHargaJualBarang());

        Glide.with(mContext).load(Common.convertToByte(barang.getGambarBarang()))
                .asBitmap()
                .into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }
}