package com.kelas.mugnificent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.kelas.mugnificent.Activity.reservasiFragment
import com.kelas.mugnificent.databinding.FragmentReservasi1Binding

class Reservasi1Fragment : Fragment() {
    private lateinit var binding: FragmentReservasi1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentReservasi1Binding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding.inputTanggal.doAfterTextChanged {
            reservasiFragment.data.tanggal = it.toString()
        }

        binding.inputJam.doAfterTextChanged {
            reservasiFragment.data.jam = it.toString()
        }

        binding.inputTamu.doAfterTextChanged {
            reservasiFragment.data.jumlahTamu = if (it.toString().isNotEmpty()) it.toString().toInt() else 0
        }

        return binding.root
    }
}