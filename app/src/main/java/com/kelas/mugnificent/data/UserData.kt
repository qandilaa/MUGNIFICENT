package com.kelas.mugnificent.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    var nama: String = "",
    var username: String = "",
    var email: String = "",
    var noHp: String = ""
): Parcelable