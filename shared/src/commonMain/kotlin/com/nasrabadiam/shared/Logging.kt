package com.nasrabadiam.shared

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initializeLogging() {
    Napier.base(DebugAntilog())
}