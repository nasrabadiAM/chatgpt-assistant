package com.nasrabadiam.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nasrabadiam.shared.chat.ui.AssistantApp

@Composable
fun MainView(modifier: Modifier = Modifier, onExit: () -> Unit) {
    AssistantApp(modifier, onExit)
}