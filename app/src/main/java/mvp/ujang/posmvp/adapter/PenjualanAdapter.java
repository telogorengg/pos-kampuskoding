package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.penjualan.PenjualanContract;
import mvp.ujang.posmvp.module.penjualan.model.Penjualan;
import mvp.ujang.posmvp.utils.Common;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.MyViewHolder> {

    private Context mContext;
    private List<Penjualan> produkList;
    private PenjualanContract.PenjualanView view;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public ImageView imgProduk;
        public TextView tvHarga;
        public Button btnPesan;
        public LinearLayout onSelected;
        public TextView edit;
        public TextView qty;
        public MyViewHolder(View view) {
            super(view);
            tvNama = view.findViewById(R.id.tv_nama_produk);
            tvHarga = view.findViewById(R.id.tv_harga_produk);
            imgProduk = view.findViewById(R.id.img_produk);
            btnPesan = view.findViewById(R.id.btn_pesan);
            onSelected = view.findViewById(R.id.onSelected);
            edit = view.findViewById(R.id.edit);
            qty = view.findViewById(R.id.qty);
        }
    }

    public PenjualanAdapter(Context mContext, List<Penjualan> produkList, PenjualanContract.PenjualanView view) {
        this.mContext   = mContext;
        this.produkList = produkList;
        this.view       = view;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_penjualan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Penjualan produk = produkList.get(position);
        holder.tvNama.setText(produk.getNamaBarang());
        holder.tvHarga.setText(Common.convertToRupiah(produk.getHargaJualBarang()));
        holder.qty.setText(produk.getJumlah());
        if (produk.getJumlah().equals("0")){
            holder.onSelected.setVisibility(View.GONE);
            holder.btnPesan.setVisibility(View.VISIBLE);
            holder.btnPesan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.detailProduk(produk);
                }
            });
        }else{
            holder.btnPesan.setVisibility(View.GONE);
            holder.onSelected.setVisibility(View.VISIBLE);
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.detailProduk(produk);
                }
            });
        }

        Glide.with(mContext).load(Common.convertToByte(produk.getGambarBarang()))
                            .asBitmap()
                            .into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        return produkList.size();
    }
}