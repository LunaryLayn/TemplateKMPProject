package com.astrocoders.cooki.ui.features.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cookirecipekeeper.composeapp.generated.resources.Res
import cookirecipekeeper.composeapp.generated.resources.ic_arrow_back
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource


@Composable
fun DetailScreen(
    component: DetailComponent
) {
    val state by component.state.collectAsState()

    DetailContent(
        state = state,
        action = component::reduce
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailContent(
    state: DetailState,
    action: (DetailIntent) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            action(DetailIntent.OnBackClicked)
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
            Text(state.id)

        }
    }
}
