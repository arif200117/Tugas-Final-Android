package com.example.catatan.User

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CatatanDao {

    @Query("SELECT * FROM tabel_catatan")
    fun getCatatans(): LiveData<List<CatatanEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCatatan(catatanEntity: CatatanEntity)

    @Update
    fun updatecatatan(catatanEntity: CatatanEntity)

    @Delete
    fun deleteCatatan(catatanEntity: CatatanEntity)

}