package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.thechance.identity.ui.screen.onBoardingDataList
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightTernaryBlackColor
import com.thechance.identity.ui.theme.Typography
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerSlider() {
    val pageState = rememberPagerState(
        pageCount = onBoardingDataList.size,
        initialPage = 0
    )

    LaunchedEffect(pageState.currentPage) {
        delay(2000)
        var newPosition = pageState.currentPage + 1
        if (newPosition > onBoardingDataList.lastIndex) newPosition = 0
        pageState.animateScrollToPage(newPosition)
    }

    HorizontalPager(
        state = pageState
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            val onBoardingData = onBoardingDataList[page]
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = onBoardingData.image),
                contentDescription = null
            )
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                OnBoardingText(
                    onBoardingData = onBoardingData.title ?: "",
                    style = Typography.h1,
                    color = LightPrimaryBlackColor
                )
                OnBoardingText(
                    onBoardingData = onBoardingData.description,
                    style = Typography.body1,
                    color = LightTernaryBlackColor
                )
            }
        }

    }

}