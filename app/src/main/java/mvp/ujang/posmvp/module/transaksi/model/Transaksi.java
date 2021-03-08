package mvp.ujang.posmvp.module.transaksi.model;
import com.google.gson.annotations.SerializedName;


public class Transaksi {

	@SerializedName("kd_transaksi")
	private String kdTransaksi;

	@SerializedName("tgl_transaksi")
	private String tglTransaksi;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("grouping_date")
	private String groupingDate;

	@SerializedName("total_pembayaran")
	private String totalPembayaran;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("tunai")
	private String tunai;

	@SerializedName("status")
	private boolean status;

	public void setKdTransaksi(String kdTransaksi){
		this.kdTransaksi = kdTransaksi;
	}

	public String getKdTransaksi(){
		return kdTransaksi;
	}

	public void setTglTransaksi(String tglTransaksi){
		this.tglTransaksi = tglTransaksi;
	}

	public String getTglTransaksi(){
		return tglTransaksi;
	}

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
	}

	public void setGroupingDate(String groupingDate){
		this.groupingDate = groupingDate;
	}

	public String getGroupingDate(){
		return groupingDate;
	}

	public void setTotalPembayaran(String totalPembayaran){
		this.totalPembayaran = totalPembayaran;
	}

	public String getTotalPembayaran(){
		return totalPembayaran;
	}

	public String getTunai() {
		return tunai;
	}

	public void setTunai(String tunai) {
		this.tunai = tunai;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
 	public String toString(){
		return 
			"Transaksi{" +
			"kd_transaksi = '" + kdTransaksi + '\'' + 
			",tgl_transaksi = '" + tglTransaksi + '\'' + 
			",waktu = '" + waktu + '\'' + 
			",grouping_date = '" + groupingDate + '\'' + 
			",total_pembayaran = '" + totalPembayaran + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			"}";
		}
}