package com.eno.userss.daftarmahasiswa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.eno.userss.daftarmahasiswa.adapter.MahasiswaAdapter;
import com.eno.userss.daftarmahasiswa.model.DataMahasiswa;
import com.eno.userss.daftarmahasiswa.restapi.ConfigRetrofit;
import com.eno.userss.daftarmahasiswa.model.ResponseGetMahasiswa;
import com.eno.userss.daftarmahasiswa.model.ResponseInsert;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerMahasiswa)
    RecyclerView recyclerMahasiswa;

    EditText edtNim, edtNama, edtEmail;
    Spinner spinnerJurusan;
    RadioGroup  rgJekel;
    RadioButton rbPria, rbWanita;
    String selectedJurusan, selectedJekel;

    String[] jurusan = {
            "Teknik Komputer", "Keperawatan", "Akuntansi", "Teknik Mesin"
    };

    @Override
        public void onCreate(Bundle savedInstanceState) {

            final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                    "Loading", false);
//TODO 1 -- get data dari service
            ConfigRetrofit.service.lihat().enqueue(new Callback<ResponseGetMahasiswa>() {
                @Override
                public void onResponse(Call<ResponseGetMahasiswa> call, Response<ResponseGetMahasiswa> response) {
                    dialog.dismiss();
//TODO 2 -- ambil datanya
                    int status = response.body().getStatus();

                    if (status == 1) {
//TODO 4 -- panggil kelas datamahasiswa dan mahasiswaadapter
                        List<DataMahasiswa> dataMhs = response.body().getDatanya();
                        MahasiswaAdapter adapter = new MahasiswaAdapter(dataMhs);
                        recyclerMahasiswa.setAdapter(adapter);
                        recyclerMahasiswa.setLayoutManager(new
                                LinearLayoutManager(MainActivity.this));
                    }
                }
                //TODO 3 -- tampil toast ketika tidak terhubung ke server

                @Override
                public void onFailure(Call<ResponseGetMahasiswa> call, Throwable t) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "Tidak ada koneksi",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialogInflater();
                
            }
        });
    }

    private void displayDialogInflater() {
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.dialog_input, null);
//inisialisasi
        edtNim = (EditText) alertlayout.findViewById(R.id.edtNim);
        edtNama = (EditText) alertlayout.findViewById(R.id.edtNama);
        edtEmail = (EditText) alertlayout.findViewById(R.id.edtEmail);
        spinnerJurusan = (Spinner) alertlayout.findViewById(R.id.spinner1);
        rgJekel = alertlayout.findViewById(R.id.rbjekel);
        rbPria = (RadioButton) alertlayout.findViewById(R.id.rblaki);
        rbWanita = (RadioButton) alertlayout.findViewById(R.id.rbperempuan);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jurusan);
        spinnerJurusan.setAdapter(adapter);

        spinnerJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedJurusan = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertlayout);
        alert.setCancelable(false);
        alert.setTitle("Input data");
        alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                String paramNim = edtNim.getText().toString();
                String paramNama = edtNama.getText().toString();
                String paramEmail = edtEmail.getText().toString();

                if(rbPria.isChecked()) {
                    selectedJekel = rbPria.getText().toString();
                }else if (rbWanita.isChecked()){
                    selectedJekel = rbWanita.getText().toString();
                }

                ConfigRetrofit.service.tambah(paramNim, paramNama, selectedJurusan,
                         selectedJekel, paramEmail).enqueue(new Callback<ResponseInsert>() {

                    @Override
                    public void onResponse(Call<ResponseInsert> call,
                                           Response<ResponseInsert> response) {
                        String pesan = response.body().getPesan();
                        int status = response.body().getStatus();
                        if (status == 1) {
                            Toast.makeText(MainActivity.this, pesan,
                                    Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                            recreate();
                        } else {
                            Toast.makeText(MainActivity.this, pesan,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseInsert> call, Throwable t) {
                        Log.d("Server Error", t.getMessage());
                    }
                });
            }
//aksi dari button positive
                });
        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //TODO -- fungsi tombol back
    @Override
    public void onBackPressed() {
//super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


            }




