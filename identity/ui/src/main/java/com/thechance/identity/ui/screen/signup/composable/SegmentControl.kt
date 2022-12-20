package com.thechance.identity.ui.screen.signup.composable

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.theme.*

@Composable
fun SegmentControls(
    onChangeGander: (String) -> Unit
) {
    val index = remember { mutableStateOf(0) }
    val genderItems = listOf(
        stringResource(id = R.string.male),
        stringResource(id = R.string.female)
    )

    Row(
        modifier = Modifier
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
                onChangeGander(genderItems[index.value])
            }) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = genderItems.first(),
                color = if (index.value == 0) WhiteColor else MaterialTheme.colors.primaryVariant,
                style = Typography.body2
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
                    onChangeGander(genderItems[index.value])
                }) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = genderItems.last(),
                color = if (index.value == 1) WhiteColor else MaterialTheme.colors.primaryVariant,
                style = Typography.body2
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SegmentPreview() {
    SegmentControls(onChangeGander = {})
}