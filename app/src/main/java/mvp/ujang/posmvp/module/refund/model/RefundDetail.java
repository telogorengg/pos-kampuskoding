package mvp.ujang.posmvp.module.refund.model;
import com.google.gson.annotations.SerializedName;


public class RefundDetail {

	@SerializedName("kd_transaksi")
	private String kdTransaksi;

	@SerializedName("tgl_transaksi")
	private String tglTransaksi;

	@SerializedName("total_pembayaran")
	private String totalPembayaran;

	@SerializedName("transkasi_oleh")
	private String tanggal;

	@SerializedName("kd_barang")
	private String kdBarang;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("harga_barang")
	private String hargaBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("total_refund")
	private String totalRefund;

	public String getKdTransaksi() {
		return kdTransaksi;
	}

	public void setKdTransaksi(String kdTransaksi) {
		this.kdTransaksi = kdTransaksi;
	}

	public String getTglTransaksi() {
		return tglTransaksi;
	}

	public void setTglTransaksi(String tglTransaksi) {
		this.tglTransaksi = tglTransaksi;
	}

	public String getTotalPembayaran() {
		return totalPembayaran;
	}

	public void setTotalPembayaran(String totalPembayaran) {
		this.totalPembayaran = totalPembayaran;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getKdBarang() {
		return kdBarang;
	}

	public void setKdBarang(String kdBarang) {
		this.kdBarang = kdBarang;
	}

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	public String getHargaBarang() {
		return hargaBarang;
	}

	public void setHargaBarang(String hargaBarang) {
		this.hargaBarang = hargaBarang;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(String totalRefund) {
		this.totalRefund = totalRefund;
	}
}