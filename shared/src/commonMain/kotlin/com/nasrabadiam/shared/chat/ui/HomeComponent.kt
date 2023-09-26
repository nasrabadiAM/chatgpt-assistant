package com.nasrabadiam.shared.chat.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nasrabadiam.shared.DispatcherProvider
import com.nasrabadiam.shared.chat.ChatPresenter
import com.nasrabadiam.shared.chat.data.Chat

class HomeComponent(
    componentContext: ComponentContext,
    chatRepository: Chat,
    dispatcherProvider: DispatcherProvider
) : ComponentContext by componentContext {

    internal val presenter = instanceKeeper.getOrCreate {
        ChatPresenter(
            lifecycle = lifecycle,
            chatRepository = chatRepository,
            dispatcherProvider = dispatcherProvider
        )
    }
}
