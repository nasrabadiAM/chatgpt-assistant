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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.nasrabadiam.shared.BuildConfig
import com.nasrabadiam.shared.chat.ChatPresenter
import com.nasrabadiam.shared.chat.Message
import com.nasrabadiam.shared.chat.data.ChatRepository
import com.nasrabadiam.shared.designsystem.space
import com.nasrabadiam.shared.designsystem.theme.ChatGptAssistantTheme
import com.nasrabadiam.shared.designsystem.theme.Typography
import com.nasrabadiam.shared.designsystem.theme.transparent

@Composable
fun AssistantHome() {
    val chatPresenter = remember {
        ChatPresenter(ChatRepository.getInstance(BuildConfig.API_KEY))
    }

    ChatGptAssistantTheme {
        Chat(
            askQuestionCallback = chatPresenter::askQuestion,
            chatHistory = { chatPresenter.answersList }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Chat(
    askQuestionCallback: (question: String) -> Unit,
    chatHistory: () -> List<Message>
) {
    var textInput by rememberSaveable { mutableStateOf("") }

    Column {
        Spacer(Modifier.background(MaterialTheme.colorScheme.transparent).weight(1f))
        LazyColumn {
            itemsIndexed(
                key = { index, item ->
                    item.id + index
                },
                items = chatHistory(),
                itemContent = { _, chat ->
                    Text(
                        text = chat.message, modifier = Modifier.padding(
                            MaterialTheme.space.medium
                        )
                    )
                }
            )
        }
        Row(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.space.medium,
                    vertical = MaterialTheme.space.small
                )
                .background(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.shapes.large
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ask your question...") },
                value = textInput,
                maxLines = 5,
                textStyle = Typography.labelMedium,
                onValueChange = {
                    textInput = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onBackground,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.transparent,
                    focusedIndicatorColor = MaterialTheme.colorScheme.transparent,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.transparent,
                    disabledIndicatorColor = MaterialTheme.colorScheme.transparent
                )
            )
            AnimatedVisibility(textInput.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
                Icon(
                    imageVector = Icons.Rounded.Send,
                    modifier = Modifier.clip(CircleShape).clickable {
                        askQuestionCallback.invoke(textInput)
                        textInput = ""
                    }.padding(MaterialTheme.space.medium),
                    contentDescription = "Send",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(Modifier.padding(bottom = MaterialTheme.space.large))
    }
}