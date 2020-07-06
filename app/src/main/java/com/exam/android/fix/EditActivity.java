package com.exam.android.fix;


import androidx.room.Room;

public class ReadDataMenu extends AppCompatActivity {

    //Deklarasi Variable
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Siswa> daftarSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data_siswa);
        getSupportActionBar().setTitle("Daftar Siswa");

        //Inisialisasi ArrayList
        daftarSiswa = new ArrayList<>();

        //Inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbSiswa").allowMainThreadQueries().build();

        /*
         * Mengambil data Menu dari Database
         * lalu memasukannya ke kedalam ArrayList (daftarMenu)
         */
        daftarSiswa.addAll(Arrays.asList(database.siswaDAO().readDataSiswa()));

        recyclerView = findViewById(R.id.dataItem);

        //Agar ukuran RecyclerView tidak berubah
        recyclerView.setHasFixedSize(true);

        //Menentukan bagaimana item pada RecyclerView akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Mamasang adapter pada RecyclerView
        adapter = new RecyclerMenuAdapter(daftarSiswa, ReadDataSiswa.this);
        recyclerView.setAdapter(adapter);
    }
}
