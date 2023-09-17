package com.nasrabadiam.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.nasrabadiam.shared.chat.ui.AssistantHome
import platform.UIKit.UIViewController

fun MainView(): UIViewController = ComposeUIViewController {
    AssistantHome()
}