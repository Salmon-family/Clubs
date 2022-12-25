package com.devfalah.viewmodels.myClubs

import com.devfalah.entities.ClubSpecial
import com.devfalah.viewmodels.R

class Clubs {
    fun getClubs() = listOf(
        ClubSpecial(
            67,
            name = "Coding",
            outLineIcon = R.drawable.ic_coding,
            fillLineIcon = R.drawable.ic_fill_coding
        ),

        ClubSpecial(
            68,
            name = "Gaming",
            outLineIcon = R.drawable.ic_gamepad,
            fillLineIcon = R.drawable.ic_fill_gamepad
        ),

        ClubSpecial(
            69,
            name = "Astronomy",
            outLineIcon = R.drawable.ic_telescope,
            fillLineIcon = R.drawable.ic_fill_telescope
        ),

        ClubSpecial(
            70,
            name = "Science",
            outLineIcon = R.drawable.ic_atom,
            fillLineIcon = R.drawable.ic_fill_atom
        ),

        ClubSpecial(
            71,
            name = "Sport",
            outLineIcon = R.drawable.ic_ball,
            fillLineIcon = R.drawable.ic_fill_ball
        ),

        ClubSpecial(
            72,
            name = "Business",
            outLineIcon = R.drawable.ic_diagram,
            fillLineIcon = R.drawable.ic_fill_diagram
        ),

        ClubSpecial(
            73,
            name = "Cook",
            outLineIcon = R.drawable.ic_hat,
            fillLineIcon = R.drawable.ic_fill_hat
        ),

        ClubSpecial(
            74,
            name = "Design",
            outLineIcon = R.drawable.ic_palette,
            fillLineIcon = R.drawable.ic_fill_palette
        ),

        ClubSpecial(
            75,
            name = "Art",
            outLineIcon = R.drawable.ic_art,
            fillLineIcon = R.drawable.ic_fill_art
        ),

        ClubSpecial(
            76,
            name = "Movies",
            outLineIcon = R.drawable.ic_movie,
            fillLineIcon = R.drawable.ic_fill_movie
        ),

        ClubSpecial(
            77,
            name = "Reading",
            outLineIcon = R.drawable.ic_notebook,
            fillLineIcon = R.drawable.ic_fill_notebook
        ),

        ClubSpecial(
            78,
            name = "Camping",
            outLineIcon = R.drawable.ic_fire,
            fillLineIcon = R.drawable.ic_fill_fire
        )
    )
}
