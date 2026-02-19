package com.astrocoders.cooki

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.astrocoders.cooki.ui.app.initKoin
import com.astrocoders.cooki.ui.navigation.RootComponent

fun MainViewController() = ComposeUIViewController {
    initKoin()
    val lifecycle = LifecycleRegistry()
    val componentContext = DefaultComponentContext(lifecycle)

    val root = RootComponent(componentContext)

    App(root)
}