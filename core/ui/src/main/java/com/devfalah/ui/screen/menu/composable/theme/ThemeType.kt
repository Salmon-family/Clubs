package com.devfalah.ui.screen.menu.composable.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.*


@Composable
fun ThemeType(
    onChangeTheme: (Int) -> Unit
) {
    val index = remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .padding(end = 8.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color.White)
                .border(
                    shape = RoundedCornerShape(16.dp),
                    width = 2.dp,
                    color = if (index.value == 0) LightPrimaryBrandColor else Color.Transparent
                )
                .clickable {
                    index.value = 0
                    onChangeTheme(index.value)
                }
        ) {
            Text(
                style = Title.h1,
                text = stringResource(id = R.string.light),
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LightBackgroundColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.7f)
                        .height(24.dp)
                        .padding(start = 8.dp, top = 12.dp)
                        .background(
                            color = LightSecondaryBrandColor,
                            shape = RoundedCornerShape(20.dp)
                        )
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                        .background(
                            color = LightSecondaryBrandColor,
                            shape = RoundedCornerShape(20.dp)
                        )
                )
            }
        }



        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .padding(start = 8.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = DarkBackground)
                .border(
                    shape = RoundedCornerShape(16.dp),
                    width = 2.dp,
                    color = if (index.value == 1) LightPrimaryBrandColor else Color.Transparent
                )
                .clickable {
                    index.value = 1
                    onChangeTheme(index.value)
                }
        ) {
            Text(
                style = Title.h1,
                text = stringResource(id = R.string.dark),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DarkPrimaryGrayColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.7f)
                        .height(24.dp)
                        .padding(start = 8.dp, top = 12.dp)
                        .background(
                            color = DarkSecondaryGrayColor,
                            shape = RoundedCornerShape(20.dp)
                        ),
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                        .background(
                            color = DarkSecondaryGrayColor,
                            shape = RoundedCornerShape(20.dp)
                        )
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewThemeType() {
    ThemeType(onChangeTheme = {})
}