package com.eno.userss.daftarmahasiswa.restapi;

import com.eno.userss.daftarmahasiswa.model.ResponseGetMahasiswa;
import com.eno.userss.daftarmahasiswa.model.ResponseInsert;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("getMahasiswa")
    Call<ResponseGetMahasiswa> lihat ();

    @FormUrlEncoded
    @POST("insertMahasiswa")
    Call <ResponseInsert> tambah(@Field("nim") String nim,
                                 @Field("name") String nama,
                                 @Field("majors") String jurusan,
                                 @Field("jk")String jenis_kelamin,
                                 @Field("mail")String mail);
    @FormUrlEncoded
    @POST("updateMahasiswa")
    Call <ResponseInsert> update (@Field("id") String id,
                                  @Field("nim") String nim,
                                  @Field("name") String nama,
                                  @Field("majors") String jurusan,
                                  @Field("jk")String jenis_kelamin,
                                  @Field("mail")String mail);

    @FormUrlEncoded
    @POST("deleteMahasiswa")
    Call<ResponseInsert> hapus (@Field("id") String id);

}

