package com.kelas.mugnificent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelas.mugnificent.adapter.PesananAdapter
import com.kelas.mugnificent.data.PesananData
import com.kelas.mugnificent.databinding.FragmentPesananBinding

class PemesananFragment : Fragment() {
    private var _binding: FragmentPesananBinding? = null
    private val binding get() = _binding!!

    private var data: List<PesananData> = emptyList()
    private var status: Int = 0

    companion object {
        fun newInstance(data: List<PesananData>, position: Int): PemesananFragment {
            val fragment = PemesananFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("data", ArrayList(data))
            bundle.putInt("position", position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelableArrayList("data") ?: emptyList()
            status = it.getInt("position")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPesananBinding.inflate(inflater, container, false)
        val adapter = PesananAdapter()

        val filteredList = when (status) {
            0 -> data.filter { !it.isSuccess }
            1 -> data.filter { it.isSuccess }
            else -> data
        }

        adapter.setList(filteredList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}