package com.nasrabadiam.shared.doubles

import com.nasrabadiam.shared.chat.data.AiDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAiDataSource : AiDataSource {

    override suspend fun askQuestionFlow(question: String): Flow<String> {
        return flow {
            emit("Hi,")
            emit(" I'm ")
            emit("doing ")
            emit("good,")
            emit(" How ")
            emit("can ")
            emit("i ")
            emit("help ")
            emit("you?")
        }
    }
}