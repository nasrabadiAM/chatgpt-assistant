package com.nasrabadiam.shared.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class Space {

    val tiny: Dp = 2.dp
    val small: Dp = 4.dp
    val medium: Dp = 8.dp
    val large: Dp = 16.dp
    val xLarge: Dp = 32.dp
    val xxLarge: Dp = 64.dp
}

val LocalSpace = staticCompositionLocalOf { Space() }

val MaterialTheme.space: Space
    @Composable
    @ReadOnlyComposable
    get() = LocalSpace.current