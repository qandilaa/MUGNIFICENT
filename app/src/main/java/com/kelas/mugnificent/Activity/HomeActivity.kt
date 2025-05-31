package com.kelas.mugnificent.Activity

import android.os.Bundle
import android.util.Log
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
import com.kelas.mugnificent.adapter.BestSellerAdapter
import com.kelas.mugnificent.adapter.FavoriteAdapter
import com.kelas.mugnificent.data.BannerData
import com.kelas.mugnificent.data.Menu2Data
import com.kelas.mugnificent.data.MenuData
import com.kelas.mugnificent.databinding.ActivityHomeBinding

class HomeActivity : Fragment() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val database = FirebaseDatabase.getInstance()
        val bestSeller = database.getReference("best_seller")
        val banner = database.getReference("Banner")
        val recommend = database.getReference("recommend")
        val bestSellerAdapter = BestSellerAdapter()
        val recommendedAdapter = FavoriteAdapter()
        binding.scrollView.visibility = View.GONE
        binding.recyclerBestSeller.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerBestSeller.adapter = bestSellerAdapter
        binding.recyclerRecommend.layoutManager = LinearLayoutManager(context)
        binding.recyclerRecommend.adapter = recommendedAdapter

        binding.btnMakanan.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
        }

        binding.btnMinuman.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
        }

        binding.btnCemilan.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.nav_settings
        }

        binding.ivProfile.setOnClickListener {
            (activity as? MainActivity)?.binding?.bottomNavigation?.selectedItemId = R.id.profil
        }

        bestSeller.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val bestSellers = mutableListOf<Menu2Data>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Menu2Data::class.java)
                    item?.let { bestSellers.add(it) }
                }

                if (bestSellers.isNotEmpty()) {
                    bestSellerAdapter.setList(bestSellers)
                    bestSellerAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })

        recommend.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recommends = mutableListOf<Menu2Data>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Menu2Data::class.java)
                    item?.let { recommends.add(it) }
                }

                if (recommends.isNotEmpty()) {
                    recommendedAdapter.setList(recommends)
                    recommendedAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })

        banner.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.progress.visibility = View.GONE
                binding.scrollView.visibility = View.VISIBLE
                val banners = mutableListOf<BannerData>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(BannerData::class.java)
                    item?.let { banners.add(it) }
                }

                if (banners.isNotEmpty()) {
                    if (context != null) {
                        Glide.with(context!!)
                            .load(banners[0].url)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(binding.imgPromo)
                    }
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