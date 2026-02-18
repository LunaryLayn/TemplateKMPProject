package com.astrocoders.cooki.ui.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cookirecipekeeper.composeapp.generated.resources.Res
import cookirecipekeeper.composeapp.generated.resources.ic_arrow_back

import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeScreen(
    component: HomeComponent
) {
    val state by component.state.collectAsState()

    HomeContent(
        state = state,
        action = component::reduce
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    state: HomeState,
    action: (HomeIntent) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            action(HomeIntent.OnBackClicked)
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Button(onClick = {action(HomeIntent.NavigateToDetail("123"))}) {
                Text("Hola")
            }
        }
    }
}
