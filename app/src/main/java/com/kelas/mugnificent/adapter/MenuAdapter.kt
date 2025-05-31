package com.kelas.mugnificent.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kelas.mugnificent.Activity.reservasiFragment
import com.kelas.mugnificent.data.MenuData
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.databinding.ItemMenuBinding
import com.kelas.mugnificent.databinding.ItemPesananBinding
import com.kelas.mugnificent.fragments.Reservasi3Fragment

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private val list = ArrayList<MenuData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.context, ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(data: List<MenuData>) {
        list.clear()
        list.addAll(data)
    }

    fun clear() {
        list.clear()
    }

    inner class ViewHolder(private val ctx: Context, private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MenuData) {
            Glide.with(ctx)
                .load(data.gambar)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgMenu)
            binding.txtNama.text = data.nama
            binding.txtDeskripsi.text = data.deskripsi
            binding.txtHarga.text = "Rp ${data.harga}"
            var isClicked = false
            val filter = reservasiFragment.data.menu.filter { it.id == data.id }

            if (filter.isNotEmpty()) {
                isClicked = true
                binding.btnTambah.text = "Batalkan"
                binding.btnTambah.setBackgroundColor(Color.WHITE)
                binding.btnTambah.setTextColor(Color.BLACK)
                reservasiFragment.data.menu.add(data)
            } else {
                binding.btnTambah.text = "Tambahkan"
                binding.btnTambah.setTextColor(Color.WHITE)
                binding.btnTambah.setBackgroundColor(0xFF955F31.toInt())
                reservasiFragment.data.menu.removeIf { it.id == data.id }
            }

            binding.btnTambah.setOnClickListener {
                isClicked = !isClicked

                if (isClicked) {
                    binding.btnTambah.text = "Batalkan"
                    binding.btnTambah.setBackgroundColor(Color.WHITE)
                    binding.btnTambah.setTextColor(Color.BLACK)
                    reservasiFragment.data.menu.add(data)
                } else {
                    binding.btnTambah.text = "Tambahkan"
                    binding.btnTambah.setTextColor(Color.WHITE)
                    binding.btnTambah.setBackgroundColor(0xFF955F31.toInt())
                    reservasiFragment.data.menu.removeIf { it.id == data.id }
                }
            }
        }
    }
}