package com.nasrabadiam.shared

import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nasrabadiam.shared.chat.ui.AssistantHome
import com.nasrabadiam.shared.chat.ui.HomeComponent

@Composable
fun MainView(
    component: HomeComponent,
    onExitClicked: () -> Unit
) {
    AssistantHome(
        component = component,
        onExitClicked = onExitClicked,
        modifier = Modifier
            .systemBarsPadding()
            .imePadding()
            .displayCutoutPadding()
    )
}