package com.kelas.mugnificent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.kelas.mugnificent.Activity.HomeActivity
import com.kelas.mugnificent.Activity.favoritFragment
import com.kelas.mugnificent.Activity.reservasiFragment
import com.kelas.mugnificent.data.UserData
import com.kelas.mugnificent.databinding.ActivityMainBinding
import com.kelas.mugnificent.fragments.BookingFragment
import com.kelas.mugnificent.fragments.ProfilFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var currentFragment: Fragment? = null

    companion object {
        var user = UserData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        user = intent.getParcelableExtra("EXTRA_USER")!!
        setContentView(binding.root)
        setFragment(HomeActivity())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    setFragment(HomeActivity())
                    true
                }

                R.id.nav_profile -> {
                    setFragment(favoritFragment())
                    true
                }

                R.id.nav_settings -> {
                    setFragment(reservasiFragment())
                    true
                }

                R.id.booking -> {
                    setFragment(BookingFragment())
                    true
                }

                R.id.profil -> {
                    setFragment(ProfilFragment())
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setFragment(fragment: Fragment) {
        currentFragment = fragment
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}