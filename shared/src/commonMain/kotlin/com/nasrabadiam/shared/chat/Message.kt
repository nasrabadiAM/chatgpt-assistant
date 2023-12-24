package com.nasrabadiam.shared.chat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nasrabadiam.shared.designsystem.space

data class Message(
    val id: Int,
    val message: String
) {
    @Composable
    fun Widget() {
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = message, modifier = Modifier.padding(
                MaterialTheme.space.medium
            )
        )
    }
}