package com.nasrabadiam.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings

public val appStrings: AppStrings
    @Composable
    @ReadOnlyComposable
    get() = LocalStrings.current

public val Strings: Map<LanguageTag, AppStrings> = mapOf(
    "en" to EnAppStrings,
)

public val LocalStrings: ProvidableCompositionLocal<AppStrings> = compositionLocalOf {
    EnAppStrings
}

@Composable
public fun rememberStrings(
    languageTag: LanguageTag = "en"
): Lyricist<AppStrings> = rememberStrings(Strings, languageTag)

@Composable
public fun ProvideStrings(
    lyricist: Lyricist<AppStrings> = rememberStrings(),
    content: @Composable () -> Unit
) {
    ProvideStrings(lyricist, LocalStrings, content)
}