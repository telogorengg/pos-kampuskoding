package mvp.ujang.posmvp.module.penjualan.model;

import com.google.gson.annotations.SerializedName;

public class Penjualan {

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("harga_jual_barang")
	private String hargaJualBarang;

	@SerializedName("harga_barang")
	private String hargaBarang;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("gambar_barang")
	private String gambarBarang;

	@SerializedName("kd_barang")
	private String kdBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("deskripsi_barang")
	private String deskripsiBarang;

	@SerializedName("stok_barang")
	private String stokBarang;

	@SerializedName("jumlah")
	private String jumlah;


	public Penjualan(){}

	public Penjualan(String idKategori, String hargaJualBarang, String satuan, String gambarBarang, String kdBarang, String namaBarang, String deskripsiBarang, String stokBarang,String jumlah) {
		this.idKategori = idKategori;
		this.hargaJualBarang = hargaJualBarang;
		this.satuan = satuan;
		this.gambarBarang = gambarBarang;
		this.kdBarang = kdBarang;
		this.namaBarang = namaBarang;
		this.deskripsiBarang = deskripsiBarang;
		this.stokBarang = stokBarang;
		this.jumlah		= jumlah;
	}

	public void setIdKategori(String idKategori){
		this.idKategori = idKategori;
	}

	public String getIdKategori(){
		return idKategori;
	}

	public void setHargaJualBarang(String hargaJualBarang){
		this.hargaJualBarang = hargaJualBarang;
	}

	public String getHargaJualBarang(){
		return hargaJualBarang;
	}

	public void setSatuan(String satuan){
		this.satuan = satuan;
	}

	public String getSatuan(){
		return satuan;
	}

	public void setGambarBarang(String gambarBarang){
		this.gambarBarang = gambarBarang;
	}

	public String getGambarBarang(){
		return gambarBarang;
	}

	public void setKdBarang(String kdBarang){
		this.kdBarang = kdBarang;
	}

	public String getKdBarang(){
		return kdBarang;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public void setDeskripsiBarang(String deskripsiBarang){
		this.deskripsiBarang = deskripsiBarang;
	}

	public String getDeskripsiBarang(){
		return deskripsiBarang;
	}

	public void setStokBarang(String stokBarang){
		this.stokBarang = stokBarang;
	}

	public String getStokBarang(){
		return stokBarang;
	}

	public String getHargaBarang() {
		return hargaBarang;
	}

	public void setHargaBarang(String hargaBarang) {
		this.hargaBarang = hargaBarang;
	}

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	@Override
 	public String toString(){
		return 
			"Barang{" +
			"id_kategori = '" + idKategori + '\'' + 
			",harga_jual_barang = '" + hargaJualBarang + '\'' + 
			",satuan = '" + satuan + '\'' + 
			",gambar_barang = '" + gambarBarang + '\'' + 
			",kd_barang = '" + kdBarang + '\'' + 
			",nama_barang = '" + namaBarang + '\'' + 
			",deskripsi_barang = '" + deskripsiBarang + '\'' + 
			",stok_barang = '" + stokBarang + '\'' + 
			"}";
		}
}