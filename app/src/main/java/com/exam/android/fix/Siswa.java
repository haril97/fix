package com.exam.android.fix;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tSiswa") //Membuat tabel baru dengan nama "menu"
public class Siswa implements Serializable {

    //Membuat kolom Nomor sebagai Primary Key
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "nomor_absen")
    protected
    String absen;

    //Membuat kolom Nama
    @ColumnInfo(name = "nama_siswa")
    protected
    String nama;

    //Membuat kolom pesanan
    @ColumnInfo(name = "jurusan")
    protected
    String jurusan;

    //Method untuk mengambil data NIM
    @NonNull
    public String getNomor() {
        return absen;
    }

    //Method untuk memasukan data NIM
    public void setNomor(@NonNull String no) {
        this.absen = absen;
    }

    //Method untuk mengambil data Nama
    public String getNama() {
        return nama;
    }

    //Method untuk memasukan data Nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    //Method untuk mengambil data pesanan
    public String getJurusan() {
        return jurusan;
    }

    //Method untuk memasukan data pesanan
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

}