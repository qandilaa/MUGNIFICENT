package com.kelas.mugnificent.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuData(
    var id: Int = 0,
    var nama: String = "",
    var kategori: String = "",
    var deskripsi: String = "",
    var harga: Int = 0,
    var gambar: String = ""
): Parcelable
