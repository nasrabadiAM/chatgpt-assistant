package com.nasrabadiam.shared.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.nasrabadiam.shared.DispatcherProvider
import com.nasrabadiam.shared.chat.data.Chat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ChatPresenter(
    lifecycle: Lifecycle,
    private val chatRepository: Chat,
    private val dispatcherProvider: DispatcherProvider,
) : InstanceKeeper.Instance {

    private val presenterScope = CoroutineScope(SupervisorJob() + dispatcherProvider.main)

    private val _answersList = mutableStateListOf<Message>()
    val answersList: SnapshotStateList<Message> = _answersList

    private var chatMessageIdCounter = 0

    fun askQuestion(question: String) {
        presenterScope.launch(dispatcherProvider.io) {
            val trimmedQuestion = question.trimEnd()
            _answersList.add(Message(chatMessageIdCounter++, trimmedQuestion))
            val answerCounter = chatMessageIdCounter++
            var answer = ""
            chatRepository.askQuestionFlow(trimmedQuestion).collect {
                answer += it
                val itemIndex = _answersList.indexOfLast { item -> item.id == answerCounter }
                if (itemIndex >= 0) {
                    _answersList[itemIndex] = _answersList[itemIndex].copy(message = answer)
                } else {
                    _answersList.add(Message(answerCounter, answer))
                }
            }
        }
    }

    override fun onDestroy() {
        presenterScope.cancel()
    }
}