package com.nasrabadiam.shared.chat.ui.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

val ButtonShapes = Shapes(
    large = RoundedCornerShape(18.dp),
)

val BottomSheetShape = RoundedCornerShape(
    topStart = 28.dp,
    topEnd = 28.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp
)

val LocalButtonShapes = staticCompositionLocalOf { ButtonShapes }

val MaterialTheme.buttonShapes: Shapes
    @Composable
    @ReadOnlyComposable
    get() = LocalButtonShapes.current