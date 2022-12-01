package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.thechance.identity.ui.model.sliderDataList
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.theme.*
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerSlider() {
    val pageState = rememberPagerState(
        pageCount = sliderDataList.size,
        initialPage = 0
    )

    LaunchedEffect(pageState.currentPage) {
        delay(2000)
        var newPosition = pageState.currentPage + 1
        if (newPosition > sliderDataList.lastIndex) newPosition = 0
        pageState.animateScrollToPage(newPosition)
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ) {
        HorizontalPager(
            state = pageState,
            verticalAlignment = Alignment.Bottom
        ) { page ->
            Column{
                val sliderData = sliderDataList[page]
                OnBoardingImage(sliderData = sliderData)
                TextComposable(
                    text = sliderData.title ?: "",
                    style = Typography.h1,
                    color = LightPrimaryBlackColor,
                    Modifier.padding(start = 24.dp)
                )
                SpacerVertical16()
                TextComposable(
                    text = sliderData.description,
                    style = Typography.body1,
                    color = LightTernaryBlackColor,
                    Modifier.padding(start = 24.dp)
                )

            }
        }
        SpacerVertical16()
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(24.dp),
            activeColor = LightPrimaryBrandColor,
            inactiveColor = LightSecondaryBrandColor
        )
    }

}