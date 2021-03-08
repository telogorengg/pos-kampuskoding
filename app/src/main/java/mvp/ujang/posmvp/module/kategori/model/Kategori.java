package mvp.ujang.posmvp.module.kategori.model;


import com.google.gson.annotations.SerializedName;

public class Kategori{

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("nama_kategori")
	private String namaKategori;

	@SerializedName("gambar_kategori")
	private String gambarKategori;

	@SerializedName("total_item")
	private String totalItem;

	@SerializedName("total_barang")
	private String totalBarang;

	public Kategori(){}

	public Kategori(String idKategori, String namaKategori, String gambarKategori, String totalItem, String totalBarang) {
		this.idKategori = idKategori;
		this.namaKategori = namaKategori;
		this.gambarKategori = gambarKategori;
		this.totalItem = totalItem;
		this.totalBarang = totalBarang;
	}

	public String getIdKategori() {
		return idKategori;
	}

	public void setIdKategori(String idKategori) {
		this.idKategori = idKategori;
	}

	public String getNamaKategori() {
		return namaKategori;
	}

	public void setNamaKategori(String namaKategori) {
		this.namaKategori = namaKategori;
	}

	public String getGambarKategori() {
		return gambarKategori;
	}

	public void setGambarKategori(String gambarKategori) {
		this.gambarKategori = gambarKategori;
	}

	public String getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(String totalItem) {
		this.totalItem = totalItem;
	}

	public String getTotalBarang() {
		return totalBarang;
	}

	public void setTotalBarang(String totalBarang) {
		this.totalBarang = totalBarang;
	}
}