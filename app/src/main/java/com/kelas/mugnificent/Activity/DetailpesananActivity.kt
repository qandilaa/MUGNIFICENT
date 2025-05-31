package com.kelas.mugnificent.Activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kelas.mugnificent.MainActivity
import com.kelas.mugnificent.data.MenuData
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.databinding.ActivityDetailpesananBinding

@Suppress("DEPRECATION")
class DetailpesananActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailpesananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailpesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getParcelableExtra<PesananData>("EXTRA_DATA")!!
        binding.txtTanggal.text = "${data.tanggal} - ${data.jam}"
        binding.valueNama.text = MainActivity.user.nama
        binding.valueMeja.text = "No. ${data.noMeja}"
        binding.valueNominal.text = "Rp ${getHarga(data.menu)}"
        binding.valueTransaksi.text = FirebaseAuth.getInstance().currentUser?.uid
        binding.valueDetail.text = getMenu(data.menu)
        binding.valueTotal.text = "Rp ${getHarga(data.menu) + 5000}"

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun getHarga(data: List<MenuData>): Int {
        var harga = 0

        for (i in data.indices) {
            harga += data[i].harga
        }

        return harga
    }

    private fun getMenu(data: List<MenuData>): String {
        var detail = ""

        for (i in data.indices) {
            detail += "${data[i].nama} ${data[i].deskripsi}\n"
            detail += "1 x Rp ${data[i].harga}\n\n"
        }

        return detail
    }
}