package com.thechance.identity.ui.model

import com.thechance.identity.ui.R

data class Club(
    val name: String = "Coding",
    val icon: Int = R.drawable.ic_coding
)

fun getClubs(): List<Club>{
    val clubs = mutableListOf<Club>()
    for (i in 0..8){
        clubs.add(Club())
    }
    return clubs
}
