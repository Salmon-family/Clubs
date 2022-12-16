package com.thechance.local

import com.thechance.identity.entities.Club

class Clubs {
    fun getClubs() = listOf(
        Club(
            401,
            "Coding",
            outLineIcon = R.drawable.ic_coding,
            fillLineIcon = R.drawable.ic_fill_coding ,
        ),

        Club(
            402,
            "Gaming",
            outLineIcon = R.drawable.ic_gamepad,
            fillLineIcon = R.drawable.ic_fill_gamepad,
        ),

        Club(
            403,
            "Astronomy",
            outLineIcon = R.drawable.ic_telescope,
            fillLineIcon = R.drawable.ic_fill_telescope,
        ),

        Club(
            404,
            "Science",
            outLineIcon = R.drawable.ic_atom,
            fillLineIcon = R.drawable.ic_fill_atom,
        ),

        Club(
            405,
            "Sport",
            outLineIcon = R.drawable.ic_ball,
            fillLineIcon = R.drawable.ic_fill_ball,
        ),

        Club(
            406,
            "Business",
            outLineIcon = R.drawable.ic_diagram,
            fillLineIcon = R.drawable.ic_fill_diagram,
        ),

        Club(
            407,
            "Cook",
            outLineIcon = R.drawable.ic_hat,
            fillLineIcon = R.drawable.ic_fill_hat,
        ),

        Club(
            408,
            "Design",
            outLineIcon = R.drawable.ic_palette,
            fillLineIcon = R.drawable.ic_fill_palette,
        ),

        Club(
            409,
            "Art",
            outLineIcon = R.drawable.ic_art,
            fillLineIcon = R.drawable.ic_fill_art,
        ),

        Club(
            410,
            "Movies",
            outLineIcon = R.drawable.ic_movie,
            fillLineIcon = R.drawable.ic_fill_movie,
        ),

        Club(
            412,
            "Reading",
            outLineIcon = R.drawable.ic_notebook,
            fillLineIcon = R.drawable.ic_fill_notebook,
        ),

        Club(
            413,
            "Camping",
            outLineIcon = R.drawable.ic_fire,
            fillLineIcon = R.drawable.ic_fill_fire,
        ),
    )
}