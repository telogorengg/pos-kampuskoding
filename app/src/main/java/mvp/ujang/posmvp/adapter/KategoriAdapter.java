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
import mvp.ujang.posmvp.module.kategori.model.Kategori;
import mvp.ujang.posmvp.utils.Common;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder> {

    private Context mContext;
    private List<Kategori> kategoriList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public ImageView imgProduk;
        public TextView jumlahProduk;

        public MyViewHolder(View view) {
            super(view);
            tvNama = view.findViewById(R.id.tv_nama_kategori);
            jumlahProduk = view.findViewById(R.id.tv_jumlah);
            imgProduk = view.findViewById(R.id.img_produk);
        }
    }

    public KategoriAdapter(Context mContext, List<Kategori> kategoriList) {
        this.mContext = mContext;
        this.kategoriList = kategoriList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_kategori, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Kategori kategori = kategoriList.get(position);
        holder.tvNama.setText(kategori.getNamaKategori());
        holder.jumlahProduk.setText(kategori.getTotalItem()+" ("+kategori.getTotalBarang()+" Pcs )");

        if (!(kategori.getGambarKategori()==null))
            Glide.with(mContext).load(Common.convertToByte(kategori.getGambarKategori()))
                    .asBitmap()
                    .into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        return kategoriList.size();
    }
}