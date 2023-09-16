package com.nasrabadiam.shared.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.nasrabadiam.shared.chat.data.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChatPresenter(
    private val chatRepository: Chat,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val presenterScope = CoroutineScope(ioDispatcher + Job())

    private val _answersList = mutableStateListOf<Message>()
    val answersList: SnapshotStateList<Message> = _answersList

    private var chatMessageIdCounter = 0

    fun askQuestion(question: String) {
        presenterScope.launch {
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
}