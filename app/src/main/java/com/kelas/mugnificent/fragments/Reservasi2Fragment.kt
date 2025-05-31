package com.kelas.mugnificent.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kelas.mugnificent.Activity.reservasiFragment
import com.kelas.mugnificent.R
import com.kelas.mugnificent.databinding.ActivityReservasi2FragmentBinding
import com.kelas.mugnificent.databinding.ActivityReservasiFragmentBinding
import com.kelas.mugnificent.databinding.FragmentReservasi1Binding

class Reservasi2Fragment(private val parent: ActivityReservasiFragmentBinding) : Fragment() {
    private lateinit var binding: ActivityReservasi2FragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservasi2FragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.meja1.setOnClickListener {
            setMeja(1)
        }

        binding.meja2.setOnClickListener {
            setMeja(2)
        }

        binding.meja3.setOnClickListener {
            setMeja(3)
        }

        binding.meja4.setOnClickListener {
            setMeja(4)
        }

        binding.meja5.setOnClickListener {
            setMeja(5)
        }

        binding.meja6.setOnClickListener {
            setMeja(6)
        }

        binding.meja7.setOnClickListener {
            setMeja(7)
        }

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setMeja(no: Int) {
        reservasiFragment.data.noMeja = no
        parent.btnLanjut.isEnabled = true
        binding.meja1.background = context?.getDrawable(R.drawable.rounded_button_transperent1)
        binding.meja2.background = context?.getDrawable(R.drawable.rounded_button_transperent1)
        binding.meja3.background = context?.getDrawable(R.drawable.rounded_button_transperent1)
        binding.meja4.background = context?.getDrawable(R.drawable.rounded_button_transperent1)
        binding.meja5.background = context?.getDrawable(R.drawable.rounded_button_transperent1)
        binding.meja6.background = context?.getDrawable(R.drawable.rounded_button_transperent1)
        binding.meja7.background = context?.getDrawable(R.drawable.rounded_button_transperent1)

        when (no) {
            1 -> binding.meja1.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
            2 -> binding.meja2.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
            3 -> binding.meja3.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
            4 -> binding.meja4.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
            5 -> binding.meja5.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
            6 -> binding.meja6.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
            7 -> binding.meja7.background = context?.getDrawable(R.drawable.rounded_button_transperent2)
        }
    }
}