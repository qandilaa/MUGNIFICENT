package com.kelas.mugnificent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kelas.mugnificent.adapter.PagerAdapter
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.databinding.ActivityMybookingBinding

class BookingFragment : Fragment() {
    private lateinit var binding: ActivityMybookingBinding
    private var data: MutableList<PesananData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMybookingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding.tabLayout.visibility = View.GONE
        val database = FirebaseDatabase.getInstance()
        val pesanan = database.getReference("pesanan").child(FirebaseAuth.getInstance().currentUser?.uid!!)
        val adapter = PagerAdapter(childFragmentManager, data)
        binding.pager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.pager)
        binding.tvEmpty.visibility = View.GONE

        pesanan.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val item = dataSnapshot.getValue(PesananData::class.java)

                    item?.let {
                        data.add(it)
                    }
                }

                adapter.updateData(data)
                binding.tvEmpty.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
                binding.tabLayout.visibility = View.VISIBLE
                binding.progress.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progress.visibility = View.GONE
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        return binding.root
    }
}