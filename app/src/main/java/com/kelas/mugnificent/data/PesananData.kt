package com.kelas.mugnificent.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PesananData(
    var tanggal: String = "",
    var jam: String = "",
    var jumlahTamu: Int = 0,
    var noMeja: Int = 0,
    var menu: MutableList<MenuData> = mutableListOf(),
    var isSuccess: Boolean = false
): Parcelable