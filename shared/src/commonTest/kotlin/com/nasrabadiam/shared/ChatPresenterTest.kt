package com.nasrabadiam.shared

import com.nasrabadiam.shared.chat.ChatPresenter
import com.nasrabadiam.shared.doubles.FakeChatRepository
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class ChatPresenterTest {

    private val chatPresenter = ChatPresenter(FakeChatRepository(), UnconfinedTestDispatcher())

    private val question = "Hello ChatGpt, how are you doing?"

    @Test
    fun should_emit_question_when_question_was_asked() = runTest {

        chatPresenter.askQuestion(question)

        val first = chatPresenter.answersList.first()
        assertEquals(question, first.message)
    }

    @Test
    fun should_emit_answer_when_question_was_asked() = runTest {

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

        chatPresenter.askQuestion(question)

        val items = chatPresenter.answersList.toList()

        assertEquals(expectedValue.joinToString(separator = ""), items.last().message)
        assertTrue(message = "Item size should 2, but it is:${items.size}") {
            items.size == 2
        }
    }
}