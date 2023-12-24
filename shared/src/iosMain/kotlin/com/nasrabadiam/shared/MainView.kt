package com.nasrabadiam.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import com.nasrabadiam.shared.chat.ui.AssistantHome
import com.nasrabadiam.shared.chat.ui.HomeComponent
import platform.posix.exit

@Composable
fun MainView(component: HomeComponent) = ComposeUIViewController {
    AssistantHome(component = component, onExitClicked = { exit(0) })
}