package com.nasrabadiam.shared

import com.nasrabadiam.shared.chat.data.ChatRepository
import com.nasrabadiam.shared.doubles.FakeAiDataSource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ChatRepositoryTest {

    private val chatRepo = ChatRepository(FakeAiDataSource())

    @Test
    fun send_chat_with_valid_data_and_receive_result_successfully_in_sequence() = runTest {
        val expectedValue = listOf(
            "Hi,",
            " I'm ",
            "doing ",
            "good,",
            " How ",
            "can ",
            "i ",
            "help ",
            "you?"
        )

        val resultFlow = chatRepo.askQuestionFlow("Hello ChatGpt, how are you doing?")

        assertEquals(expectedValue, resultFlow.toList())
    }

    @Test
    fun send_chat_with_empty_data_and_should_throw_Illegal_state_exception() = runTest {
        assertFailsWith<IllegalStateException>(
            message = "exception didn't catch. something went wrong."
        ) {
            chatRepo.askQuestionFlow("")
        }
    }
}