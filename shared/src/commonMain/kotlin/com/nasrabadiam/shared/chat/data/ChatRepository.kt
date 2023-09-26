package com.nasrabadiam.shared.chat.data

import com.aallam.openai.client.OpenAI
import com.nasrabadiam.shared.BuildConfig
import kotlinx.coroutines.flow.Flow

class ChatRepository(private val aiDataSource: AiDataSource) : Chat {

    override suspend fun askQuestionFlow(question: String): Flow<String> {
        if (question.isEmpty()) {
            throw IllegalStateException("you should ask something, your input must not be empty.")
        }
        return aiDataSource.askQuestionFlow(question)
    }

    // TODO: this should be removed after adding di
    companion object {

        fun getInstance(): ChatRepository {
            return ChatRepository(OpenAiDataSource(OpenAI(BuildConfig.API_KEY)))
        }
    }
}
