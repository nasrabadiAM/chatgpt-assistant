package com.nasrabadiam.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

typealias Locale = String

internal object LocaleManager {

    enum class Locales {
        EN,
        FA;
    }

    var currentLocale: Locale by mutableStateOf(Locales.EN.name.lowercase())
        private set

    fun updateLocale(locale: Locales) {
        currentLocale = locale.name.lowercase()
    }
}

fun getLocale(): Locale {
    return LocaleManager.currentLocale
}