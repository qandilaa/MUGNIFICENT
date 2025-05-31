package com.kelas.mugnificent.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.kelas.mugnificent.R
import com.kelas.mugnificent.adapter.OnboardingPagerAdapter
import com.kelas.mugnificent.preferences.AppPreference

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var bar1: View
    private lateinit var bar2: View
    private lateinit var bar3: View
    private lateinit var btnStart: Button

    private val activeColor by lazy { ContextCompat.getColor(this, R.color.brown_prim) }
    private val inactiveColor by lazy { ContextCompat.getColor(this, R.color.green_bg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        bar1 = findViewById(R.id.bar1)
        bar2 = findViewById(R.id.bar2)
        bar3 = findViewById(R.id.bar3)
        btnStart = findViewById(R.id.btnStart)

        viewPager.adapter = OnboardingPagerAdapter(this)
        updateProgressBar(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateProgressBar(position)

                btnStart.visibility = if (position == 2) View.VISIBLE else View.GONE
            }
        })

        btnStart.setOnClickListener {
            AppPreference.setBool("is_introduced", true)
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun updateProgressBar(position: Int) {
        bar1.setBackgroundColor(if (position == 0) activeColor else inactiveColor)
        bar2.setBackgroundColor(if (position == 1) activeColor else inactiveColor)
        bar3.setBackgroundColor(if (position == 2) activeColor else inactiveColor)
    }
}