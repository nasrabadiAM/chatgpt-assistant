package com.nasrabadiam.shared

import androidx.compose.runtime.Composable
import com.nasrabadiam.shared.chat.ui.AssistantHome
import com.nasrabadiam.shared.chat.ui.HomeComponent

@Composable
fun MainView(component: HomeComponent) {
    AssistantHome(component)
}