package com.kelas.mugnificent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kelas.mugnificent.MainActivity
import com.kelas.mugnificent.databinding.ProfilBinding

class ProfilFragment : Fragment() {
    private lateinit var binding: ProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfilBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding.txtNamaDisplay.text = MainActivity.user.username
        binding.txtNamaLengkap.text = MainActivity.user.nama
        binding.txtEmail.text = MainActivity.user.email
        binding.txtHP.text = MainActivity.user.noHp
        return binding.root
    }
}