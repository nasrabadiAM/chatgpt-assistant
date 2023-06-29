package com.nasrabadiam.shared.chat.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.nasrabadiam.shared.BuildConfig
import com.nasrabadiam.shared.chat.ChatPresenter
import com.nasrabadiam.shared.chat.Message
import com.nasrabadiam.shared.chat.data.ChatRepository
import com.nasrabadiam.shared.chat.ui.designsystem.theme.BottomSheetShape
import com.nasrabadiam.shared.chat.ui.designsystem.theme.ChatGptTheme
import com.nasrabadiam.shared.chat.ui.designsystem.theme.space
import com.nasrabadiam.shared.chat.ui.designsystem.theme.transparent

@Composable
fun AssistantApp(
    modifier: Modifier = Modifier,
    onExit: () -> Unit = {}
) {
    val chatPresenter = remember {
        ChatPresenter(ChatRepository.getInstance(BuildConfig.API_KEY))
    }

    ChatGptTheme {
        Chat(
            modifier = modifier,
            askQuestionCallback = chatPresenter::askQuestion,
            onExit = onExit,
            chatHistory = { chatPresenter.answersList }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Chat(
    modifier: Modifier = Modifier,
    askQuestionCallback: (question: String) -> Unit,
    onExit: () -> Unit,
    chatHistory: () -> List<Message>
) {
    val textInput = rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier
        .background(MaterialTheme.colorScheme.transparent)
        .clickable { onExit.invoke() }
    ) {
        Box(Modifier.weight(1f))
        Column(
            Modifier.background(
                shape = BottomSheetShape,
                color = MaterialTheme.colorScheme.background
            ).padding(top = MaterialTheme.space.large)
        ) {
            LazyColumn {
                itemsIndexed(
                    key = { index, item ->
                        item.id + index
                    },
                    items = chatHistory(),
                    itemContent = { _, chat ->
                        Text(
                            text = chat.message,
                            modifier = Modifier.padding(MaterialTheme.space.small)
                        )
                    }
                )
            }
            val textFieldPadding = MaterialTheme.space.xSmall
            Row(
                modifier = Modifier
                    .padding(
                        start = textFieldPadding,
                        end = textFieldPadding,
                        top = textFieldPadding,
                        bottom = MaterialTheme.space.medium
                    )
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(MaterialTheme.space.medium)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Ask your question...") },
                    value = textInput.value,
                    maxLines = 5,
                    onValueChange = {
                        textInput.value = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.transparent
                    ),
                    shape = MaterialTheme.shapes.medium,
                    trailingIcon = {
                        SendIcon(
                            textInput = textInput,
                            askQuestionCallback = askQuestionCallback
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RowScope.SendIcon(
    modifier: Modifier = Modifier,
    textInput: MutableState<String>,
    askQuestionCallback: (question: String) -> Unit
) {
    AnimatedVisibility(
        textInput.value.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Icon(
            imageVector = Icons.Rounded.Send,
            modifier = modifier
                .clip(CircleShape)
                .clickable {
                    askQuestionCallback.invoke(textInput.value)
                    textInput.value = ""
                }
                .padding(MaterialTheme.space.small),
            contentDescription = "Send",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}