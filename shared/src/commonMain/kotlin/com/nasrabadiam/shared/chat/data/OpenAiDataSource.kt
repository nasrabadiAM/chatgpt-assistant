package com.nasrabadiam.shared.chat.data

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@OptIn(BetaOpenAI::class)
class OpenAiDataSource(
    private val openAI: OpenAI,
    private val modelIdName: String = "gpt-3.5-turbo"
) : AiDataSource {

    override suspend fun askQuestionFlow(question: String): Flow<String> {
        return flow {
            ask(question).collect {
                it.choices.forEach { choice ->
                    emit(choice.delta?.content.orEmpty())
                }
            }
        }
    }

    private fun ask(question: String, role: ChatRole = ChatRole.User): Flow<ChatCompletionChunk> {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId(modelIdName),
            messages = listOf(
                ChatMessage(
                    role = role,
                    content = question
                )
            )
        )
        return openAI.chatCompletions(chatCompletionRequest)
    }
}
