package com.nasrabadiam.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.nasrabadiam.shared.chat.ui.AssistantHome
import com.nasrabadiam.shared.chat.ui.HomeComponent

fun MainView(component: HomeComponent) = ComposeUIViewController {
    AssistantHome(component)
}