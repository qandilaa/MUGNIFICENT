package com.kelas.mugnificent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kelas.mugnificent.MainActivity
import com.kelas.mugnificent.R
import com.kelas.mugnificent.data.Menu2Data
import com.kelas.mugnificent.data.MenuData
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.databinding.ItemBestsellerBinding
import com.kelas.mugnificent.databinding.ItemFavoritBinding
import com.kelas.mugnificent.databinding.ItemMenuBinding
import com.kelas.mugnificent.databinding.ItemPesananBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private val list = ArrayList<Menu2Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.context, ItemFavoritBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val index1 = position * 2
        val index2 = index1 + 1
        holder.bind(list.getOrNull(index1), list.getOrNull(index2))
    }

    override fun getItemCount(): Int {
        return (list.size + 1) / 2
    }

    fun setList(data: List<Menu2Data>) {
        list.clear()
        list.addAll(data)
    }

    fun clear() {
        list.clear()
    }

    inner class ViewHolder(private val ctx: Context, private val binding: ItemFavoritBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data1: Menu2Data?, data2: Menu2Data?) {
            data1?.let {
                Glide.with(ctx).load(it.gambar).into(binding.imgMenu)
                binding.txtHarga.text = it.harga
            }

            if (data2 != null) {
                binding.frame2.visibility = View.VISIBLE
                Glide.with(ctx).load(data2.gambar).into(binding.imgMenu2)
                binding.txtHarga2.text = data2.harga
            } else {
                binding.frame2.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                (ctx as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
            }
        }
    }
}