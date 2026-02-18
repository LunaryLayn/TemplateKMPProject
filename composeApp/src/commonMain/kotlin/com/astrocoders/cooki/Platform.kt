package com.astrocoders.cooki

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform