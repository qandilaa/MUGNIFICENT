package com.kelas.mugnificent.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelas.mugnificent.Activity.DetailpesananActivity
import com.kelas.mugnificent.MainActivity
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.databinding.ItemPesananBinding

class PesananAdapter : RecyclerView.Adapter<PesananAdapter.ViewHolder>() {
    private val list = ArrayList<PesananData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.context, ItemPesananBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(data: List<PesananData>) {
        list.clear()
        list.addAll(data)
    }

    fun clear() {
        list.clear()
    }

    inner class ViewHolder(private val ctx: Context, private val binding: ItemPesananBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PesananData) {
            binding.bookingDate.text = data.tanggal
            binding.bookingTime.text = data.jam
            binding.bookingDesc.text = "Pemesanan meja no. ${data.noMeja} beserta menu makanan atas nama ${MainActivity.user.nama} berhasil"

            binding.root.setOnClickListener {
                val intent = Intent(ctx, DetailpesananActivity::class.java)
                intent.putExtra("EXTRA_DATA", data)
                ctx.startActivity(intent)
            }
        }
    }
}