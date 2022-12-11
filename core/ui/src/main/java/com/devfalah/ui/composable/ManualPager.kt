package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.viewmodels.Constants
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun ManualPager(
    swipeRefreshState: SwipeRefreshState,
    onRefresh: (Int) -> Unit,
    items: List<PostUIState>,
    scrollState: LazyListState,
    isRefreshing: Boolean,
    error: String,
    content: LazyListScope.() -> Unit
) {
    loadMore(scrollState, onRefresh = onRefresh, items = items)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { onRefresh(Constants.SWIPE_UP) },
        indicatorAlignment = Alignment.Center,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                scale = true,
                backgroundColor = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small,
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .background(LightBackgroundColor)
                .fillMaxSize(),
            state = scrollState,
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            content()

            val message = if (isRefreshing) {
                "Loading.."
            } else {
                error
            }

            item {
                Text(text = message)
            }
        }
    }
}

@Composable
private fun loadMore(
    scrollState: LazyListState,
    items: List<PostUIState>,
    onRefresh: (Int) -> Unit
) {
    val comparedItemIndex = if (items.size > 5) {
        items.size.minus(5)
    } else {
        items.lastIndex
    }

    if (!scrollState.isScrollingUp() && !scrollState.isScrollInProgress
        && comparedItemIndex > 0 && scrollState.firstVisibleItemIndex < items.lastIndex
        && (items[scrollState.firstVisibleItemIndex].postId != items[comparedItemIndex].postId)
    ) {
        onRefresh(Constants.SWIPE_DOWN)
    }
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}
