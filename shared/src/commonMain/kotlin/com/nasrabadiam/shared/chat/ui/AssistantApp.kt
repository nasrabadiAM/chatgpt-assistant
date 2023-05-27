package com.nasrabadiam.shared.chat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nasrabadiam.shared.chat.ChatPresenter
import com.nasrabadiam.shared.chat.Message
import com.nasrabadiam.shared.chat.data.ChatRepository

@Composable
fun AssistantApp() {
    val chatPresenter = remember {
        ChatPresenter(ChatRepository.getInstance("open ai token"))
    }

    Chat(
        askQuestionCallback = chatPresenter::askQuestion,
        chatHistory = { chatPresenter.answersList }
    )
}

@Composable
private fun Chat(
    askQuestionCallback: (question: String) -> Unit,
    chatHistory: () -> List<Message>
) {
    var textInput by rememberSaveable { mutableStateOf("") }

    Column {
        Spacer(Modifier.weight(1f))
        LazyColumn {
            itemsIndexed(
                key = { index, item ->
                    item.id + index
                },
                items = chatHistory(),
                itemContent = { _, chat ->
                    Text(text = chat.message, modifier = Modifier.padding(8.dp))
                }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                modifier = Modifier.padding(8.dp).weight(1f),
                placeholder = { Text("Ask your question...") },
                value = textInput,
                onValueChange = {
                    textInput = it
                })
            Button(onClick = {
                askQuestionCallback.invoke(textInput)
                textInput = ""
            }, Modifier.padding(8.dp).wrapContentSize(Alignment.Center)) {
                Text("send")
            }
        }
    }
}