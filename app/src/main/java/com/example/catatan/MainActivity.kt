package com.example.catatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        recylerview.setHasFixedSize(true)
        recylerview.layoutManager = LinearLayoutManager(this)

        viewmodel.getCatatans()?.observe(this, Observer {
            recylerview.adapter = Adapter(it, object : Adapter.Listener {
                override fun onClick(catatanEntity: CatatanEntity) {
                    showUpdateDialog(catatanEntity)
                }
            })
        })

        addbtn.setOnClickListener {
            showAddDialog()
        }

    }

    private fun showUpdateDialog(catatanEntity: CatatanEntity) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
        val builder = this.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
        }
        val mDialog = builder?.show()
        with(dialogView) {

            judulinput.setText(catatanEntity.judul)
            deskripsiinput.setText(catatanEntity.deskripsi)
            lokasiinput.setText(catatanEntity.lokasi)
            waktuInput.setText(catatanEntity.waktu)

            hapusBtn.visibility = View.VISIBLE
            savebtn.text = "update"
            savebtn.setOnClickListener {
                val judul = judulinput.text.toString()
                val desc = deskripsiinput.text.toString()
                val waktu = waktuInput.text.toString()
                val lokasi = lokasiinput.text.toString()

                if( judul != "" &&  desc != "" &&  waktu != "" && lokasi != "") {
                    viewmodel.updateCatatan(
                        CatatanEntity(
                            catatanEntity.id, judul, desc, waktu, lokasi
                        )
                    )
                    mDialog?.dismiss()
                    Toast.makeText(this@MainActivity, "Catatan Berhasil Diubah", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Harap Mengisi Semua Kolom", Toast.LENGTH_SHORT).show()
                }
            }
            hapusBtn.setOnClickListener {
                viewmodel.deleteCatatan(catatanEntity)
                mDialog?.dismiss()
                Toast.makeText(this@MainActivity, "Catatan Berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
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