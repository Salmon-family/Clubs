package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.spacer.HeightSpacer8
import com.devfalah.ui.theme.*

@Composable
fun ClubCard() {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFFAFAFA) // need to add to the colors file
    ) {
        Column {

            Image(
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = "club cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(174.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = "The Chance Community",
                    style = Typography.subtitle2,
                    color = LightSecondaryBlackColor
                )

                Text(
                    text = "10K member . 100K post",
                    style = Typography.caption,
                    color = LightTernaryBlackColor
                )

                HeightSpacer8()

                Text(
                    text = "This club is for the community of The Chance, to make discussions about Android development, an...",
                    style = Typography.caption,
                    color = LightSecondaryBlackColor
                )

                HeightSpacer8()

                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(38.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(100),
                    elevation = ButtonDefaults.elevation(0.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = LightBackgroundColor),
                    border = BorderStroke(1.dp, LightQuaternaryBlackColor)
                ) {

                    Text(
                        text = "Joined Club",
                        style = Typography.subtitle2,
                        color = LightTernaryBlackColor
                    )

                }
            }

        }

    }
}

@Preview
@Composable
private fun Preview() {
    ClubCard()
}