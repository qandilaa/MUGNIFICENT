package com.kelas.mugnificent.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.fragments.BookingFragment
import com.kelas.mugnificent.fragments.PemesananFragment

class PagerAdapter(
    fragmentManager: FragmentManager,
    private var data: List<PesananData>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val title = arrayOf("Pemesanan", "Riwayat Pesanan")

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return PemesananFragment.newInstance(data, position)
    }

    override fun getPageTitle(position: Int): CharSequence? = title[position]

    override fun getItemPosition(`object`: Any): Int = POSITION_NONE

    fun updateData(newData: List<PesananData>) {
        data = newData
        notifyDataSetChanged()
    }
}
