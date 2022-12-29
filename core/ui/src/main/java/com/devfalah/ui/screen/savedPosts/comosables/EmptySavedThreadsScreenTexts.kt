package com.devfalah.ui.screen.savedPosts.comosables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer4
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun EmptySavedThreadsScreenTexts(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.nothing_to_show),
            style = TextStyle(
                fontFamily = PlusJakartaSans,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        )

        HeightSpacer4()

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.saved_threads_text),
            style = TextStyle(
                fontFamily = PlusJakartaSans,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}