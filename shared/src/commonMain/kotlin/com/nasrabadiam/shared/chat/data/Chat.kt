package com.nasrabadiam.shared.chat.data

import kotlinx.coroutines.flow.Flow

interface Chat {

    suspend fun askQuestionFlow(question: String): Flow<String>
}