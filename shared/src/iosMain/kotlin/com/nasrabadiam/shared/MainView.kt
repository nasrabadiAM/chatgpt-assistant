package com.nasrabadiam.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.nasrabadiam.shared.chat.ui.AssistantApp
import platform.UIKit.UIViewController

fun MainView(onExit: () -> Unit): UIViewController = ComposeUIViewController {
    AssistantApp(onExit = onExit)
}