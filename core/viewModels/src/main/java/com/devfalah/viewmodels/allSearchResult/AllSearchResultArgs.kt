package com.devfalah.viewmodels.allSearchResult

import androidx.lifecycle.SavedStateHandle


class AllSearchResultArgs(savedStateHandle: SavedStateHandle) {

    val title: String = checkNotNull(savedStateHandle[Screen_Title_ARG]).toString()
    val keyword: String = checkNotNull(savedStateHandle[Search_Keyword]).toString()
    val type: Int = checkNotNull(savedStateHandle[Type_Search_ARG]).toString().toInt()

    companion object {
        const val Screen_Title_ARG = "ScreenTitle"
        const val Type_Search_ARG = "SEARCH_TYPE"
        const val Search_Keyword = "Search_Keyword"
    }
}