package com.thechance.identity.ui.screen

import com.thechance.identity.ui.R

data class OnBoardingUIState(
    val image: Int,
    val title: String?,
    val description: String,
)

val onBoardingDataList = listOf(
    OnBoardingUIState(
        image = R.drawable.pc_man_one,
        title = "",
        description = "Meet new people close to your interests and have fun"
    ),
    OnBoardingUIState(
        image = R.drawable.pc_man_two,
        title = "Keep track of the latest posts",
        description = "Keep track of the latest and most enthusiastic" +
                " publications in your clubs"
    ),
    OnBoardingUIState(
        image = R.drawable.pc_man_three,
        title = "Connect\n" +
                "with your friends",
        description = "Meet new people close to your interests and have fun"
    )
)
