package com.exam.android.fix;

import android.app.Activity;

import androidx.room.Room;

public class RecyclerSiswaAdapter extends RecyclerView.Adapter<RecyclerSiswaAdapter.ViewHolder> {

    //Deklarasi Variable
    private ArrayList<Siswa> daftarSiswa;
    private AppDatabase appDatabase;
    private Context context;

    public RecyclerMenuAdapter(ArrayList<Siswa> daftarSiswa, Context context) {

        //Inisialisasi data yang akan digunakan
        this.daftarSiswa = daftarSiswa;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbSiswa").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //Deklarasi View yang akan digunakan
        private TextView Nomor, Nama;
        private CardView item;


        ViewHolder(View itemView) {
            super(itemView);
            Nomor = itemView.findViewById(R.id.nomor);
            Nama = itemView.findViewById(R.id.nama);
            item = itemView.findViewById(R.id.cvMain);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi Layout Item untuk RecyclerView
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Deklarasi Variable untuk mendapatkan data dari Database melalui Array
        final String getNomor = daftarSiswa.get(position).getNomor();
        final String getNama = daftarSiswa.get(position).getNama();

        //Menampilkan data berdasarkan posisi Item dari RecyclerView
        holder.Nomor.setText(getNomor);
        holder.Nama.setText(getNama);

        //Detail
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Siswa siswa = appDatabase.siswaDAO()
                        .selectDetailMenu(daftarSiswa.get(position).getNomor());
                context.startActivity(new Intent(context, DetailDataActivity.class).putExtra("detail", siswa));
            }
        });

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence[] menuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext())
                        .setTitle("Pilih Aksi")
                        .setItems(menuPilihan, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                    /*
                                     Menjalankan Perintah Edit Data
                                     Menggunakan Bundle untuk mengambil data yang akan Diedit
                                     */
                                        onEditData(position, context);
                                        break;

                                    case 1:
                                    /*
                                     Menjalankan Perintah Delete Data
                                     Akan dibahas pada Tutorial selanjutnya
                                     */
                                        onDeleteData(position);
                                        break;
                                }
                            }
                        });
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }

    //Menghapus Data dari Room Database yang dipilih oleh user
    private void onDeleteData(int position){
        appDatabase.siswaDAO().deleteMenu(daftarSiswa.get(position));
        daftarSiswa.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarSiswa.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }

    //Mengirim Data yang akan diedit dari ArrayList berdasarkan posisi item pada RecyclerView
    private void onEditData(int position, Context context){
        context.startActivity(new Intent(context, EditActivity.class).putExtra("data", daftarSiswa.get(position)));
        ((Activity)context).finish();
    }
    @Override
    public int getItemCount() {
        //Menghitung data / ukuran dari Array
        return daftarSiswa.size();
    }
