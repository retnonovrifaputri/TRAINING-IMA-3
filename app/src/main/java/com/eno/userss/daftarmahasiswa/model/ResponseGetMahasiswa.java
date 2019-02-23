package com.eno.userss.daftarmahasiswa.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseGetMahasiswa{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("datanya")
	private List<DataMahasiswa> datanya;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDatanya(List<DataMahasiswa> datanya){
		this.datanya = datanya;
	}

	public List<DataMahasiswa> getDatanya(){
		return datanya;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetMahasiswa{" + 
			"pesan = '" + pesan + '\'' + 
			",datanya = '" + datanya + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}