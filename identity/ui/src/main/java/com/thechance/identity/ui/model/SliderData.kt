package com.thechance.identity.ui.model

import com.thechance.identity.ui.R

data class SliderData(
    val image: Int,
    val title: String?,
    val description: String,
)

val sliderDataList = listOf(
    SliderData(
        image = R.drawable.pc_man_one,
        title = "",
        description = "Meet new people close to your interests and have fun"
    ),
    SliderData(
        image = R.drawable.pc_man_two,
        title = "Keep track of the latest posts",
        description = "Keep track of the latest and most enthusiastic" +
                " publications in your clubs"
    ),
    SliderData(
        image = R.drawable.pc_man_three,
        title = "Connect" +
                "with your friends",
        description = "Meet new people close to your interests and have fun"
    )
)
