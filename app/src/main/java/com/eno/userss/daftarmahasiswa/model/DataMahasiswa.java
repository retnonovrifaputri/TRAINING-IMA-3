package com.eno.userss.daftarmahasiswa.model;


import com.google.gson.annotations.SerializedName;


public class DataMahasiswa{

	@SerializedName("mahasiswa_jurusan")
	private String mahasiswaJurusan;

	@SerializedName("mahasiswa_nama")
	private String mahasiswaNama;

	@SerializedName("mahasiswa_id")
	private String mahasiswaId;

	@SerializedName("mahasiswa_nim")
	private String mahasiswaNim;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("email")
	private String email;

	public void setMahasiswaJurusan(String mahasiswaJurusan){
		this.mahasiswaJurusan = mahasiswaJurusan;
	}

	public String getMahasiswaJurusan(){
		return mahasiswaJurusan;
	}

	public void setMahasiswaNama(String mahasiswaNama){
		this.mahasiswaNama = mahasiswaNama;
	}

	public String getMahasiswaNama(){
		return mahasiswaNama;
	}

	public void setMahasiswaId(String mahasiswaId){
		this.mahasiswaId = mahasiswaId;
	}

	public String getMahasiswaId(){
		return mahasiswaId;
	}

	public void setMahasiswaNim(String mahasiswaNim){
		this.mahasiswaNim = mahasiswaNim;
	}

	public String getMahasiswaNim(){
		return mahasiswaNim;
	}

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"DatanyaItem{" + 
			"mahasiswa_jurusan = '" + mahasiswaJurusan + '\'' + 
			",mahasiswa_nama = '" + mahasiswaNama + '\'' + 
			",mahasiswa_id = '" + mahasiswaId + '\'' + 
			",mahasiswa_nim = '" + mahasiswaNim + '\'' + 
			",jenis_kelamin = '" + jenisKelamin + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}