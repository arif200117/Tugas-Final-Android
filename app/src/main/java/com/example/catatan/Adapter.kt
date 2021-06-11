package com.example.catatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catatan.User.CatatanEntity
import kotlinx.android.synthetic.main.item_catatan.view.*

class Adapter(private val list: List<CatatanEntity>, private val listener: Adapter.Listener): RecyclerView.Adapter<Adapter.Holder>() {

    interface Listener {
        fun onClick(catatanEntity: CatatanEntity)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(catatanEntity: CatatanEntity, listener: Listener) {
            with(itemView) {
                judul.text = catatanEntity.judul
                deskripsi.text = catatanEntity.deskripsi
                waktu.text = catatanEntity.waktu
                lokasi.text = catatanEntity.lokasi
                itemView.setOnClickListener {
                    listener.onClick(catatanEntity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catatan, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Adapter.Holder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}