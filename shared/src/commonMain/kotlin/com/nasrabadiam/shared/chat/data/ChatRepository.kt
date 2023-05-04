package com.nasrabadiam.shared.chat.data

import kotlinx.coroutines.flow.Flow

class ChatRepository(private val aiDataSource: AiDataSource) : Chat {

    override suspend fun askQuestionFlow(question: String): Flow<String> {
        if (question.isEmpty()) {
            throw IllegalStateException("you should ask something, your input must not be empty.")
        }
        return aiDataSource.askQuestionFlow(question)
    }
}
