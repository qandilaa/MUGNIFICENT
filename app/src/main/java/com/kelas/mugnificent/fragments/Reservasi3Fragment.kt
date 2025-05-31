package com.kelas.mugnificent.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kelas.mugnificent.R
import com.kelas.mugnificent.adapter.MenuAdapter
import com.kelas.mugnificent.data.BannerData
import com.kelas.mugnificent.data.MenuData
import com.kelas.mugnificent.databinding.FragmentReservasi3Binding

class Reservasi3Fragment : Fragment() {
    private lateinit var binding: FragmentReservasi3Binding
    private lateinit var list: List<MenuData>
    private lateinit var adapter: MenuAdapter
    private lateinit var default: Drawable

    companion object {
        var pesanan = mutableListOf<MenuData>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentReservasi3Binding.inflate(layoutInflater)
        adapter = MenuAdapter()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val database = FirebaseDatabase.getInstance()
        val menu = database.getReference("menu")
        binding.categoryFilter.visibility = View.GONE

        menu.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val menus = mutableListOf<MenuData>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(MenuData::class.java)
                    item?.let { menus.add(it) }
                }

                if (menus.isNotEmpty()) {
                    list = menus
                    binding.categoryFilter.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE
                    val filter = list.filter { it.kategori == "Makanan" }
                    adapter.setList(filter)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })

        default = binding.btnMakanan.background
        binding.btnMakanan.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
        binding.recyclerMenu.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMenu.adapter = adapter

        binding.btnMakanan.setOnClickListener {
            setFilter("Makanan")
            binding.btnMakanan.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
        }

        binding.btnMinuman.setOnClickListener {
            setFilter("Minuman")
            binding.btnMinuman.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
        }

        binding.btnCemilan.setOnClickListener {
            setFilter("Cemilan")
            binding.btnCemilan.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
        }

        return binding.root
    }

    private fun setFilter(kategori: String) {
        val filter = list.filter { it.kategori == kategori }
        adapter.setList(filter)
        adapter.notifyDataSetChanged()
        binding.btnMakanan.background = default
        binding.btnMinuman.background = default
        binding.btnCemilan.background = default
    }
}