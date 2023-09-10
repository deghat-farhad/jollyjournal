package com.farhad.jollyjournal.com.farhad.jollyjournal.utils

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import java.io.Serializable

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}