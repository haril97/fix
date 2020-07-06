package com.exam.android.fix;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SiswaDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSiswa(Siswa siswa);

    @Query("SELECT * FROM tSiswa")
    Siswa[] readDataSiswa();

    @Update
    int UpdateSiswa(Siswa siswa);

    @Delete
    void deleteSiswa(Siswa siswa);

    @Query("SELECT * FROM tSiswa WHERE nomor_absen = :nomor LIMIT 1")
    Siswa selectDetailMenu(String nomor);
}
