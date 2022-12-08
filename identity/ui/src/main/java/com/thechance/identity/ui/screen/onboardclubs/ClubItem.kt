package com.thechance.identity.ui.screen.onboardclubs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.thechance.identity.ui.R
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.CardTitle
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun ClubItem(
    @DrawableRes iconId: Int,
    tintColor: Color,
    isSelected: Boolean,
    text: String,
    onChecked: (Boolean) -> Unit,
    selectedColor: Color = LightPrimaryBrandColor
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .wrapContentSize()
            .padding(30.dp)
            .border(
                width = 2.dp,
                color = if (isSelected) selectedColor else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = Color(0xFFFAFAFA),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable { }
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if(isSelected) LightPrimaryBrandColor else tintColor,
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = text,
            style = Typography.CardTitle,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(bottom = 30.dp, start = 30.dp, end = 30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubItem(){
    ClubItem(
        iconId = R.drawable.ic_coding,
        tintColor = Color.Black,
        isSelected = true,
        text = "Coding",
        onChecked = {},
    )
}