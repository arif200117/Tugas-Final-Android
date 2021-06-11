package com.example.catatan.User

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_catatan")
data class CatatanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var judul: String,
    var deskripsi: String,
    var waktu: String,
    var lokasi: String
)