package com.nasrabadiam.shared.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nasrabadiam.shared.designsystem.utils.colorPack

val ColorScheme.transparent: Color
    @Composable
    get() = colorPack(md_theme_light_transparent, md_theme_dark_transparent)