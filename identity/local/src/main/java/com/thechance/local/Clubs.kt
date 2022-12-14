package com.thechance.local

import com.thechance.identity.entities.Club

class Clubs {
    fun getClubs() = listOf<Club>(
        Club(
            401,
            "Coding",
            R.drawable.ic_coding
        ),

        Club(
            402,
            "Gaming",
            R.drawable.ic_gamepad
        ),

        Club(
            403,
            "Astronomy",
            R.drawable.ic_telescope
        ),

        Club(
            404,
            "Science",
            R.drawable.ic_atom
        ),

        Club(
            405,
            "Sport",
            R.drawable.ic_ball
        ),

        Club(
            406,
            "Business",
            R.drawable.ic_diagram
        ),

        Club(
            407,
            "Cook",
            R.drawable.ic_hat
        ),

        Club(
            408,
            "Design",
            R.drawable.ic_palette
        ),

        Club(
            409,
            "Art",
            R.drawable.ic_art
        ),

        Club(
            410,
            "Movies",
            R.drawable.ic_movie
        ),

        Club(
            412,
            "Reading",
            R.drawable.ic_notebook
        ),

        Club(
            413,
            "Camping",
            R.drawable.ic_fire
        ),
    )
}