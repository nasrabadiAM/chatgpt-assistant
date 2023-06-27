package com.nasrabadiam.shared.chat.data

import kotlinx.coroutines.flow.Flow

interface AiDataSource {

    suspend fun askQuestionFlow(question: String): Flow<String>
}