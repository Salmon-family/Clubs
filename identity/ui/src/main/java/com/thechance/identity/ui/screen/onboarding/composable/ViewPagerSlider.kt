package com.thechance.identity.ui.screen.onboarding.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
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
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.thechance.identity.ui.model.SliderData
import com.thechance.identity.ui.model.sliderDataList
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBrandColor
import com.thechance.identity.ui.theme.Typography
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerSlider(
    modifier: Modifier = Modifier
) {
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()

    ) {

        HorizontalPager(
            state = pageState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column {
                val sliderData = sliderDataList[page]

                OnBoardingImage(
                    sliderData = sliderData,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Text(
                    text = sliderData.title ?: "",
                    style = Typography.h1,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                SpacerVertical16()
                Text(
                    text = sliderData.description,
                    style = Typography.body1,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

            }
        }

        SpacerVertical16()
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 24.dp),
            activeColor = LightPrimaryBrandColor,
            inactiveColor = LightSecondaryBrandColor
        )

    }
}


@Composable
private fun OnBoardingImage(
    sliderData: SliderData,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        contentScale = ContentScale.Fit,
        painter = painterResource(id = sliderData.image),
        contentDescription = null
    )
}

@Preview
@Composable
private fun Preview() {
    ViewPagerSlider()
}
