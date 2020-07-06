package com.exam.android.fix;

import androidx.room.Room;

public class SiswaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Nomor, Nama, Jurusan;
    private Button btnSave, btnLihat;

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Nomor = findViewById(R.id.editnomor);
        Nama = findViewById(R.id.editnama);
        Jurusan = findViewById(R.id.editjurusan);

        btnSave = findViewById(R.id.save);
        btnSave.setOnClickListener(this);
        btnLihat = findViewById(R.id.lihat);
        btnLihat.setOnClickListener(this);

        //Inisialisasi dan memanggil Room Database
        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbSiswa") //Nama File Database yang akan disimpan
                .build();
    }

    //Menjalankan method Insert Data
    @SuppressLint("StaticFieldLeak")
    private void insertData(final Siswa siswa) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.siswaDAO().insertSiswa(siswa);
            }

            @Override
            protected void onPostExecute(Long status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(SiswaActivity.this, "Status Row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void onClick(View vi) {
        switch (vi.getId()) {
            case R.id.save:

                //Mengecek Data Nomor dan Nama
                if (Nomor.getText().toString().isEmpty() || Nama.getText().toString().isEmpty()) {
                    Toast.makeText(SiswaActivity.this, "Nomor atau Nama TIdak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    //Membuat Instance/Objek Dari Class Entity menu
                    Siswa data = new Siswa();

                    //Memasukan data yang diinputkan user pada database
                    data.setNomor(Nomor.getText().toString());
                    data.setNama(Nama.getText().toString());
                    data.setJurusan(Jurusan.getText().toString());
                    insertData(data);

                    //Mengembalikan EditText menjadi seperti semula\
                    Nomor.setText("");
                    Nama.setText("");
                    Jurusan.setText("");
                }
                break;

            case R.id.lihat:
                startActivity(new Intent(SiswaActivity.this, ReadDataSiswa.class));
                break;
        }
    }

}

