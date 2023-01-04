package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun RowTitleWithSeeAll(
    modifier: Modifier = Modifier,
    title: String,
    onclickSeeAll: () -> Unit,
    showSeeAll: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .nonRippleEffect { onclickSeeAll() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            fontSize = 18.sp,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.primaryVariant
        )

        if (showSeeAll) {
            Text(
                text = stringResource(id = R.string.see_all),
                fontSize = 12.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSecondary,
            )

            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = null,
                tint = MaterialTheme.colors.secondaryVariant
            )
        }
    }
}


@Preview(showSystemUi = false)
@Composable
fun PreviewRowTitleWithSeeAll() {
    RowTitleWithSeeAll(title = "Club", onclickSeeAll = {}, showSeeAll = true)
}