package com.exam.android.fix;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Siswa.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //Untuk mengakses Database menggunakan method abstract
    public abstract SiswaDao siswaDao();
}