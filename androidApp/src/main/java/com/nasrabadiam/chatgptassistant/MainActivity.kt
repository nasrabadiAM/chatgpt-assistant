package com.nasrabadiam.chatgptassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.nasrabadiam.shared.DefaultDispatchersProvider
import com.nasrabadiam.shared.MainView
import com.nasrabadiam.shared.chat.data.ChatRepository
import com.nasrabadiam.shared.chat.ui.HomeComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val component = HomeComponent(
            componentContext = defaultComponentContext(),
            chatRepository = ChatRepository.getInstance(),
            dispatcherProvider = DefaultDispatchersProvider()
        )

        setContent {
            MainView(component, ::onExitClicked)
        }
    }

    private fun onExitClicked() {
        finish()
    }
}