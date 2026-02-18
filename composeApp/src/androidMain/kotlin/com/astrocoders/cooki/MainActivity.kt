package com.astrocoders.cooki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.astrocoders.cooki.ui.app.initKoin
import com.astrocoders.cooki.ui.navigation.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        initKoin()
        val root = RootComponent(defaultComponentContext())
        setContent {
            App(root)
        }
    }
}
