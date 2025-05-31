package com.kelas.mugnificent.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kelas.mugnificent.Activity.OnboardingFragment
import com.kelas.mugnificent.Activity.OnboardingFragment1
import com.kelas.mugnificent.Activity.OnboardingFragment2

class OnboardingPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment()
            1 -> OnboardingFragment1()
            2 -> OnboardingFragment2()
            else -> OnboardingFragment()
        }
    }
}
