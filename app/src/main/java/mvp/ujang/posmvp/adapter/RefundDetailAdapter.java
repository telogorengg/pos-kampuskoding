package mvp.ujang.posmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import mvp.ujang.posmvp.R;
import mvp.ujang.posmvp.module.refund.model.RefundDetail;
import mvp.ujang.posmvp.utils.Common;

public class RefundDetailAdapter extends RecyclerView.Adapter<RefundDetailAdapter.MyViewHolder>{

    private Context mContext;
    private List<RefundDetail> transaksiList;
    private SelectedRefund selectedRefund;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView     tvTotalBayar;
        public TextView     namaBarang;
        public TextView     jumlah;
        public CheckBox     checkboxItem;
        public MyViewHolder(View view) {
            super(view);
            tvTotalBayar    = view.findViewById(R.id.total);
            namaBarang      = view.findViewById(R.id.nama_barang);
            jumlah          = view.findViewById(R.id.jumlah);
            checkboxItem    = view.findViewById(R.id.checkboxItem);
        }
    }

    public RefundDetailAdapter(Context mContext, List<RefundDetail> transaksiList) {
        this.mContext = mContext;
        this.transaksiList = transaksiList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_refund_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        RefundDetail struk = transaksiList.get(position);
        holder.tvTotalBayar.setText(Common.convertToRupiah(String.valueOf(Integer.parseInt(struk.getJumlah())*Integer.parseInt(struk.getHargaBarang()))));
        holder.namaBarang.setText(struk.getNamaBarang());
        holder.jumlah.setText(struk.getJumlah()+" x "+struk.getHargaBarang());

        holder.checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    selectedRefund.onSelectedItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }

    public void setSelectedRefund(SelectedRefund selectedRefund) {
        this.selectedRefund = selectedRefund;
    }

    public interface SelectedRefund {
        public void onSelectedItem(int position);
    }
}