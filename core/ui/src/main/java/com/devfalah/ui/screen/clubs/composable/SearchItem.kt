package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.*

@Composable
fun SearchItem(
    modifier: Modifier = Modifier,
    onSearchItemClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onSearchItemClick },
        shape = RoundedCornerShape(100),
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_search_outline),
                contentDescription = "search icon",
                tint = LightQuaternaryBlackColor,
                modifier = Modifier.size(24.dp)
            )

            Text(text = stringResource(R.string.search_for_club),
                style = AppTypography.body2,
                color = LightTernaryBlackColor,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SearchItem(onSearchItemClick = {})
}