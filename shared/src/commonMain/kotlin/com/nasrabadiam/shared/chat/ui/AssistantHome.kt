package com.nasrabadiam.shared.chat.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nasrabadiam.shared.chat.Message
import com.nasrabadiam.shared.designsystem.space
import com.nasrabadiam.shared.designsystem.theme.AssistantLocaleTheme
import com.nasrabadiam.shared.designsystem.theme.Typography
import com.nasrabadiam.shared.designsystem.theme.transparent
import com.nasrabadiam.shared.utils.localeAware
import com.nasrabadiam.strings.appStrings

@Composable
fun AssistantHome(
    component: HomeComponent,
    onExitClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chatPresenter = remember {
        component.presenter
    }
    AssistantLocaleTheme {
        Chat(
            modifier = modifier,
            askQuestionCallback = chatPresenter::askQuestion,
            chatHistory = { chatPresenter.answersList },
            onExitClicked = onExitClicked
        )
    }
}

@Composable
private fun Chat(
    modifier: Modifier,
    askQuestionCallback: (question: String) -> Unit,
    onExitClicked: () -> Unit,
    chatHistory: () -> List<Message>
) {
    Column(modifier = modifier) {
        Box(
            Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.transparent)
                        .clickable(onClick = onExitClicked)
                )
                ChatSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 160.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(MaterialTheme.space.medium),
                    chatHistory = chatHistory
                )
            }
        }
        InputBar(askQuestionCallback)
    }
}

@Composable
private fun ChatSection(modifier: Modifier, chatHistory: () -> List<Message>) {

    val scrollState = rememberLazyListState()
    LaunchedEffect(key1 = chatHistory().size) {
        scrollState.animateScrollToItem(chatHistory().size)
    }

    LazyColumn(
        modifier = modifier,
        state = scrollState
    ) {
        itemsIndexed(
            key = { index, item ->
                item.id + index
            },
            items = chatHistory(),
            itemContent = { _, chat ->
                chat.Widget()
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputBar(
    askQuestionCallback: (question: String) -> Unit
) {
    var textInput by rememberSaveable { mutableStateOf("") }
    Row(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(
                horizontal = MaterialTheme.space.medium,
                vertical = MaterialTheme.space.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            placeholder = { Text(appStrings.askYourQuestion) },
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
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        askQuestionCallback.invoke(textInput)
                        textInput = ""
                    }
                    .padding(MaterialTheme.space.medium)
                    .localeAware(),
                contentDescription = appStrings.contentDescription.send,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(
            Modifier.padding(bottom = MaterialTheme.space.large)
        )
    }
}