package com.nasrabadiam.shared.chat.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nasrabadiam.shared.BuildConfig
import com.nasrabadiam.shared.chat.ChatPresenter
import com.nasrabadiam.shared.chat.Message
import com.nasrabadiam.shared.chat.data.ChatRepository
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun AssistantApp() {
    val chatPresenter = remember {
        ChatPresenter(ChatRepository.getInstance(BuildConfig.API_KEY))
    }

    Chat(
        askQuestionCallback = chatPresenter::askQuestion,
        chatHistory = { chatPresenter.answersList }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Chat(
    askQuestionCallback: (question: String) -> Unit,
    chatHistory: () -> List<Message>
) {
    var textInput by rememberSaveable { mutableStateOf("") }

    Column {
        Spacer(Modifier.background(Color.Transparent).weight(1f))
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
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .background(
                    Color.LightGray.copy(alpha = 0.5f),
                    RoundedCornerShape(16.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ask your question...") },
                value = textInput,
                maxLines = 5,
                onValueChange = {
                    textInput = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            AnimatedVisibility(textInput.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
                Icon(
                    imageVector = Icons.Rounded.Send,
                    modifier = Modifier.clip(CircleShape).clickable {
                        askQuestionCallback.invoke(textInput)
                        textInput = ""
                    }.padding(8.dp),
                    contentDescription = "Send",
                    tint = Color.Black
                )
            }
        }
    }
}