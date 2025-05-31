package com.kelas.mugnificent.Activity

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kelas.mugnificent.MainActivity
import com.kelas.mugnificent.R
import com.kelas.mugnificent.adapter.FavoriteAdapter
import com.kelas.mugnificent.adapter.MenuAdapter
import com.kelas.mugnificent.data.BannerData
import com.kelas.mugnificent.data.Menu2Data
import com.kelas.mugnificent.databinding.ActivityFavoritFragmentBinding
import com.kelas.mugnificent.databinding.ActivityHomeBinding

class favoritFragment : Fragment() {
    private lateinit var binding: ActivityFavoritFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val adapter = FavoriteAdapter()
        binding.rvFavorit.layoutManager = LinearLayoutManager(context)
        binding.rvFavorit.adapter = adapter
        val database = FirebaseDatabase.getInstance()
        val favorit = database.getReference("favorit")

        binding.btnMakanan.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
        }

        binding.btnMinuman.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
        }

        binding.btnCemilan.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
        }

        favorit.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.progress.visibility = View.GONE
                val favorits = mutableListOf<Menu2Data>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Menu2Data::class.java)
                    item?.let { favorits.add(it) }
                }

                if (favorits.isNotEmpty()) {
                    adapter.setList(favorits)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progress.visibility = View.GONE
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}