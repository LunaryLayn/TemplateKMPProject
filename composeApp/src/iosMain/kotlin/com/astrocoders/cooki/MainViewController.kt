package com.astrocoders.cooki

import androidx.compose.ui.window.ComposeUIViewController
import com.astrocoders.cooki.ui.app.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}