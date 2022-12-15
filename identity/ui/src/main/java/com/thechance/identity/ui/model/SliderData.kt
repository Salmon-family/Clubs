package com.thechance.identity.ui.model

import com.thechance.identity.ui.R

data class SliderData(
    val image: Int,
    val title: String?,
    val description: String,
)

val sliderDataList = listOf(
    SliderData(
        image = R.drawable.pc_first_man,
        title = "Meet\n" +
                "new people",
        description = "Meet new people close to your interests and have fun"
    ),
    SliderData(
        image = R.drawable.pc_second_man,
        title = "Keep track \nof the latest posts",
        description = "Keep track of the latest and most enthusiastic" +
                " publications in your clubs"
    ),
    SliderData(
        image = R.drawable.pc_third_man,
        title = "Connect \nwith your friends",
        description = "Connect with your friends and share all the" +
                " stories and fun"
    )
)
