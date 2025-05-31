package com.kelas.mugnificent.adapter

import android.content.Context
import android.view.LayoutInflater
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
import com.kelas.mugnificent.databinding.ItemMenuBinding
import com.kelas.mugnificent.databinding.ItemPesananBinding

class BestSellerAdapter : RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {
    private val list = ArrayList<Menu2Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.context, ItemBestsellerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(data: List<Menu2Data>) {
        list.clear()
        list.addAll(data)
    }

    fun clear() {
        list.clear()
    }

    inner class ViewHolder(private val ctx: Context, private val binding: ItemBestsellerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Menu2Data) {
            Glide.with(ctx)
                .load(data.gambar)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgItem)
            binding.txtHarga.text = data.harga

            binding.root.setOnClickListener {
                (ctx as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
            }
        }
    }
}