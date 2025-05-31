package com.kelas.mugnificent.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kelas.mugnificent.R
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.data.UserData
import com.kelas.mugnificent.databinding.ActivityReservasiFragmentBinding
import com.kelas.mugnificent.fragments.Reservasi1Fragment
import com.kelas.mugnificent.fragments.Reservasi2Fragment
import com.kelas.mugnificent.fragments.Reservasi3Fragment

class reservasiFragment : Fragment() {
    private lateinit var binding: ActivityReservasiFragmentBinding
    private lateinit var currentFragment: Fragment

    companion object {
        var data = PesananData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservasiFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding.labelBooking.visibility = View.GONE
        setFragment(Reservasi1Fragment(), 1)
        binding.btnBatal.visibility = View.GONE
        data = PesananData()

        binding.btnLanjut.setOnClickListener {
            if (currentFragment is Reservasi1Fragment) {
                if (data.tanggal.isNotEmpty() && data.jam.isNotEmpty() && data.jumlahTamu != 0) {
                    setFragment(Reservasi2Fragment(binding), 2)
                    binding.btnLanjut.isEnabled = false
                    binding.btnBatal.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Input tidak boleh kosong", Toast.LENGTH_SHORT)
                        .show()
                }
            } else if (currentFragment is Reservasi2Fragment) {
                setFragment(Reservasi3Fragment(), 3)
                binding.btnBatal.visibility = View.GONE
                binding.labelBooking.visibility = View.VISIBLE
                binding.btnLanjut.text = "Simpan"
            } else if (currentFragment is Reservasi3Fragment) {
                if (data.menu.isNotEmpty()) {
                    val database = FirebaseDatabase.getInstance()
                    val pesanan = database.getReference("pesanan")

                    pesanan.child(FirebaseAuth.getInstance().currentUser?.uid!!).push().setValue(data)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Pesanan berhasil", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(requireContext(), "Menu tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setFragment(fragment: Fragment, num: Int) {
        currentFragment = fragment
        childFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()

        binding.tvStep1.background = requireContext().getDrawable(R.drawable.circle_brown_light)
        binding.tvStep2.background = requireContext().getDrawable(R.drawable.circle_brown_light)
        binding.tvStep3.background = requireContext().getDrawable(R.drawable.circle_brown_light)

        when (num) {
            1 -> binding.tvStep1.background = requireContext().getDrawable(R.drawable.circle_brown)
            2 -> binding.tvStep2.background = requireContext().getDrawable(R.drawable.circle_brown)
            3 -> binding.tvStep3.background = requireContext().getDrawable(R.drawable.circle_brown)
        }
    }
}