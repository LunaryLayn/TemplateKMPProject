package com.astrocoders.cooki

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.astrocoders.cooki.ui.features.detail.DetailScreen
import com.astrocoders.cooki.ui.features.home.HomeScreen
import com.astrocoders.cooki.ui.navigation.RootComponent
import org.jetbrains.compose.resources.painterResource

@Composable
fun App(root: RootComponent) {

    MaterialTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {

            Children(stack = root.childStack) { child ->

                when (val instance = child.instance) {

                    is RootComponent.Child.Home ->
                        HomeScreen(instance.component)

                    is RootComponent.Child.Detail ->
                        DetailScreen(instance.component)
                }
            }
        }
    }
}