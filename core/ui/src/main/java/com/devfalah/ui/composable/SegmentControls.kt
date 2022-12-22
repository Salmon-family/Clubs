package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.WhiteColor


@Composable
fun SegmentControls(
    items: List<String>,
    onItemSelection: (selectedItemIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val index = remember { mutableStateOf(0) }
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
                onItemSelection(index.value)
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
                    onItemSelection(index.value)
                }) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = items.last(),
                color = if (index.value == 1) WhiteColor else MaterialTheme.colors.primaryVariant,
                style = AppTypography.body2
            )
        }
    }
}