package com.nasrabadiam.chatgptassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.nasrabadiam.shared.MainView
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainView(
                Modifier
                    .systemBarsPadding()
                    .imePadding()
                    .displayCutoutPadding(),
                ::finishApp
            )
        }
    }

    private fun finishApp() {
        finish()
        exitProcess(0)
    }
}