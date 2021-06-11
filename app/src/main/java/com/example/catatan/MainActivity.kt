package com.example.catatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.catatan.User.CatatanEntity
import com.example.catatan.User.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        addbtn.setOnClickListener {
            showAddDialog()
        }

    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
        val builder = this.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
        }
        val mDialog = builder?.show()
        with(dialogView) {
            hapusBtn.visibility = View.GONE
            savebtn.setOnClickListener {
                val judul = judulinput.text.toString()
                val desc = deskripsiinput.text.toString()
                val waktu = waktuInput.text.toString()
                val lokasi = lokasiinput.text.toString()

                if( judul != "" &&  desc != "" &&  waktu != "" && lokasi != "") {
                    viewmodel.insertCatatan(
                        CatatanEntity(
                            0, judul, desc, waktu, lokasi
                        )
                    )
                    mDialog?.dismiss()
                    Toast.makeText(this@MainActivity, "Catatan Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Harap Mengisi Semua Kolom", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}