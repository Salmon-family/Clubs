package com.devfalah.ui.profile.profileSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.theme.*

@Composable
fun FriendOptionsSection(
    modifier: Modifier= Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(36.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        FriendSectionButton(
            modifier = Modifier.weight(1f),
            onCLick = {},
            buttonColors = ButtonDefaults.textButtonColors(backgroundColor = LightPrimaryBrandColor),
            text = stringResource(R.string.add_friend),
            textColor = WhiteColor
        )

        FriendSectionButton(
            modifier = Modifier.weight(1f),
            onCLick = {},
            buttonColors = ButtonDefaults.textButtonColors(backgroundColor = LightCardBackgroundColor),
            text = stringResource(R.string.message),
            textColor = LightPrimaryBlackColor
        )

        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null,
            Modifier.size(36.dp)
        )
    }
}

@Composable
fun FriendSectionButton(
    modifier: Modifier = Modifier,
    onCLick: () -> Unit,
    buttonColors: ButtonColors,
    text: String,
    textColor: Color
) {
    TextButton(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)),
        onClick = onCLick,
        colors = buttonColors,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontFamily = PlusJakartaSans,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun PreviewFriendOptions() {
    FriendOptionsSection()
}