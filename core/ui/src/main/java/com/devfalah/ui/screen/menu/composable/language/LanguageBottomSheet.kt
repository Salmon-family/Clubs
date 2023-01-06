package com.devfalah.ui.screen.menu.composable.language

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.devfalah.ui.R
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.Title
import com.devfalah.ui.theme.WhiteColor

@Composable
fun LanguageBottomSheet(
    onChangeLanguage: (Int) -> Unit,
    language: String,
) {
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(
                (configuration.screenHeightDp * 0.25).dp,
                (configuration.screenHeightDp * 0.75).dp
            )
            .wrapContentHeight(unbounded = true)
    ) {
        Text(
            style = Title.h1,
            text = stringResource(id = R.string.app_language),
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)
        )

        SegmentOfLanguage(
            language = language,
            items = listOf(
                stringResource(id = R.string.english),
                stringResource(id = R.string.arabic)
            ),
            onItemSelection = onChangeLanguage,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}


@Composable
private fun SegmentOfLanguage(
    language: String,
    items: List<String>,
    onItemSelection: (selectedItemIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val index = remember { mutableStateOf(0) }
    val context = LocalContext.current as Activity

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .wrapContentSize()
            .background(MaterialTheme.colors.surface, shape = RoundedCornerShape(100.dp))
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .weight(1f)
            .wrapContentHeight()
            .clip(
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = if (index.value == 0) LightPrimaryBrandColor else MaterialTheme.colors.surface
            )
            .clickable {
                index.value = 0
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
                onItemSelection(index.value)
                context.finish()
                context.startActivity(context.intent)
            }) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = items.first(),
                color = if (index.value == 0) WhiteColor else MaterialTheme.colors.primaryVariant,
                style = AppTypography.body2
            )
        }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(100.dp))
                .background(
                    color = if (index.value == 1) LightPrimaryBrandColor else MaterialTheme.colors.surface
                )
                .clickable {
                    index.value = 1
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ar"))
                    onItemSelection(index.value)

                    context.finish()
                    context.startActivity(context.intent)
                }) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = items.last(),
                color = if (index.value == 1) WhiteColor else MaterialTheme.colors.primaryVariant,
                style = AppTypography.body2
            )
        }
    }

    LaunchedEffect(key1 = language) {
        index.value = if (language == "en") 0 else 1
    }
}