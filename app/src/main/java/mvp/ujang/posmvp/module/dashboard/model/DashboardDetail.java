package mvp.ujang.posmvp.module.dashboard.model;


import com.google.gson.annotations.SerializedName;

public class DashboardDetail{

	@SerializedName("total_item")
	private String totalItem;

	@SerializedName("net_sales")
	private String netSales;

	@SerializedName("gross_sales")
	private String grossSales;

	@SerializedName("total_refund")
	private String totalRefund;

	@SerializedName("gross_profit")
	private String grossProfit;

	@SerializedName("total_sales")
	private String totalSales;

	@SerializedName("refunds")
	private String refunds;

	@SerializedName("bulan")
	private String bulan;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("total")
	private String total;

	@SerializedName("total_harga")
	private String totalHarga;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("mode")
	private String mode;

	@SerializedName("tahun")
	private String tahun;


	public void setTotalItem(String totalItem){
		this.totalItem = totalItem;
	}

	public String getTotalItem(){
		return totalItem;
	}

	public void setNetSales(String netSales){
		this.netSales = netSales;
	}

	public String getNetSales(){
		return netSales;
	}

	public void setGrossSales(String grossSales){
		this.grossSales = grossSales;
	}

	public String getGrossSales(){
		return grossSales;
	}

	public void setTotalRefund(String totalRefund){
		this.totalRefund = totalRefund;
	}

	public String getTotalRefund(){
		return totalRefund;
	}

	public void setGrossProfit(String grossProfit){
		this.grossProfit = grossProfit;
	}

	public String getGrossProfit(){
		return grossProfit;
	}

	public void setTotalSales(String totalSales){
		this.totalSales = totalSales;
	}

	public String getTotalSales(){
		return totalSales;
	}

	public void setRefunds(String refunds){
		this.refunds = refunds;
	}

	public String getRefunds(){
		return refunds;
	}

	public String getBulan() {
		return bulan;
	}

	public void setBulan(String bulan) {
		this.bulan = bulan;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotalHarga() {
		return totalHarga;
	}

	public void setTotalHarga(String totalHarga) {
		this.totalHarga = totalHarga;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTahun() {
		return tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	@Override
	public String toString() {
		return "DashboardDetail{" +
				"totalItem='" + totalItem + '\'' +
				", netSales='" + netSales + '\'' +
				", grossSales='" + grossSales + '\'' +
				", totalRefund='" + totalRefund + '\'' +
				", grossProfit='" + grossProfit + '\'' +
				", totalSales='" + totalSales + '\'' +
				", refunds='" + refunds + '\'' +
				", bulan='" + bulan + '\'' +
				", namaBarang='" + namaBarang + '\'' +
				", total='" + total + '\'' +
				", totalHarga='" + totalHarga + '\'' +
				", tanggal='" + tanggal + '\'' +
				", mode='" + mode + '\'' +
				", tahun='" + tahun + '\'' +
				'}';
	}
}