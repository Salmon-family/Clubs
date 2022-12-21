package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.WhiteColor

@Composable
fun SegmentControlsWithIcon(
    modifier: Modifier = Modifier,
    items: List<String>,
    icons: List<Painter>,
    onItemSelection: (selectedItemIndex: Int) -> Unit,
) {
    val index = remember { mutableStateOf(0) }
    Row(
        modifier = modifier
            .wrapContentWidth()
            .padding(horizontal = 8.dp)
            .background(
                MaterialTheme.colors.surface,
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(
                    color = if (index.value == 0) LightPrimaryBrandColor else MaterialTheme.colors.surface
                )
                .clickable {
                    index.value = 0
                    onItemSelection(index.value)
                }) {

            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = items.first(),
                    color = if (index.value == 0) WhiteColor else MaterialTheme.colors.primaryVariant,
                    style = AppTypography.body2
                )

                Icon(
                    painter = icons.first(),
                    contentDescription = null,
                    tint = if (index.value == 0) WhiteColor else MaterialTheme.colors.primaryVariant
                )
            }
        }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(
                    color = if (index.value == 1) LightPrimaryBrandColor else MaterialTheme.colors.surface
                )
                .clickable {
                    index.value = 1
                    onItemSelection(index.value)
                }) {

            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = items.last(),
                    color = if (index.value == 1) WhiteColor else MaterialTheme.colors.primaryVariant,
                    style = AppTypography.body2,
                )

                Icon(
                    painter = icons.last(),
                    contentDescription = null,
                    tint = if (index.value == 1) WhiteColor else MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewSegmentControlsWithIcon() {
    SegmentControlsWithIcon(
        items = listOf("public", "private"),
        icons = listOf(
            painterResource(id = R.drawable.ic_menu_language),
            painterResource(id = R.drawable.ic_clubs_filled),
        ),
        onItemSelection = {}
    )
}