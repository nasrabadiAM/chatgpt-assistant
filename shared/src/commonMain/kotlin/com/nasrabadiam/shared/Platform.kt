package com.nasrabadiam.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform