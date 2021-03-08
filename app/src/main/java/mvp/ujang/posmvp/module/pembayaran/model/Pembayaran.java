package mvp.ujang.posmvp.module.pembayaran.model;
import com.google.gson.annotations.SerializedName;

public class Pembayaran{

	@SerializedName("kd_transaksi")
	private String kdTransaksi;

	@SerializedName("total_pembayaran")
	private String totalPembayaran;

	@SerializedName("tgl_transaksi")
	private String tglTransaksi;

	@SerializedName("transaksi_oleh")
	private String transaksiOleh;

	@SerializedName("tunai")
	private String tunai;


	public Pembayaran(){}

	public String getKdTransaksi() {
		return kdTransaksi;
	}

	public void setKdTransaksi(String kdTransaksi) {
		this.kdTransaksi = kdTransaksi;
	}

	public String getTotalPembayaran() {
		return totalPembayaran;
	}

	public void setTotalPembayaran(String totalPembayaran) {
		this.totalPembayaran = totalPembayaran;
	}

	public String getTglTransaksi() {
		return tglTransaksi;
	}

	public void setTglTransaksi(String tglTransaksi) {
		this.tglTransaksi = tglTransaksi;
	}

	public String getTransaksiOleh() {
		return transaksiOleh;
	}

	public void setTransaksiOleh(String transaksiOleh) {
		this.transaksiOleh = transaksiOleh;
	}


	public String getTunai() {
		return tunai;
	}

	public void setTunai(String tunai) {
		this.tunai = tunai;
	}

	@Override
	public String toString() {
		return "Pembayaran{" +
				"kdTransaksi='" + kdTransaksi + '\'' +
				", totalPembayaran='" + totalPembayaran + '\'' +
				", tglTransaksi='" + tglTransaksi + '\'' +
				", transaksiOleh='" + transaksiOleh + '\'' +
				'}';
	}
}