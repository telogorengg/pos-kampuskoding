package mvp.ujang.posmvp.module.keranjang.model;

import com.google.gson.annotations.SerializedName;

public class Keranjang{

	@SerializedName("id_keranjang")
	private String idKeranjang;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("kd_barang")
	private String kdBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("harga_jual_barang")
	private String hargaJualBarang;

	@SerializedName("gambar_barang")
	private String gambarBarang;

	@SerializedName("stok_barang")
	private String stokBarang;

	public Keranjang(){}

	public Keranjang(String idKeranjang, String jumlah, String kdBarang, String namaBarang, String hargaJualBarang, String gambarBarang, String stokBarang) {
		this.idKeranjang = idKeranjang;
		this.jumlah = jumlah;
		this.kdBarang = kdBarang;
		this.namaBarang = namaBarang;
		this.hargaJualBarang = hargaJualBarang;
		this.gambarBarang = gambarBarang;
		this.stokBarang = stokBarang;
	}

//	public Keranjang(String idKeranjang, String jumlah, String kdBarang, String namaBarang, String hargaJualBarang, String gambarBarang) {
//		this.idKeranjang = idKeranjang;
//		this.jumlah = jumlah;
//		this.kdBarang = kdBarang;
//		this.namaBarang = namaBarang;
//		this.hargaJualBarang = hargaJualBarang;
//		this.gambarBarang = gambarBarang;
//	}

	public String getIdKeranjang() {
		return idKeranjang;
	}

	public void setIdKeranjang(String idKeranjang) {
		this.idKeranjang = idKeranjang;
	}

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	public String getKdBarang() {
		return kdBarang;
	}

	public void setKdBarang(String kdBarang) {
		this.kdBarang = kdBarang;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getHargaJualBarang() {
		return hargaJualBarang;
	}

	public void setHargaJualBarang(String hargaJualBarang) {
		this.hargaJualBarang = hargaJualBarang;
	}

	public String getGambarBarang() {
		return gambarBarang;
	}

	public void setGambarBarang(String gambarBarang) {
		this.gambarBarang = gambarBarang;
	}

	public String getStokBarang() {
		return stokBarang;
	}

	public void setStokBarang(String stokBarang) {
		this.stokBarang = stokBarang;
	}

	@Override
	public String toString() {
		return "Keranjang{" +
				"idKeranjang='" + idKeranjang + '\'' +
				", jumlah='" + jumlah + '\'' +
				", kdBarang='" + kdBarang + '\'' +
				", namaBarang='" + namaBarang + '\'' +
				", hargaJualBarang='" + hargaJualBarang + '\'' +
				", gambarBarang='" + gambarBarang + '\'' +
				", stokBarang='" + stokBarang + '\'' +
				'}';
	}
}