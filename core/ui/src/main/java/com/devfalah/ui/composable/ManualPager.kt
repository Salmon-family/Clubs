package com.devfalah.ui.composable

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.devfalah.ui.theme.LightPrimaryBrandColor
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
    content: @Composable () -> Unit
) {
    loadMore(scrollState, onRefresh = onRefresh, items = items)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { onRefresh(Constants.SWIPE_UP) },
        indicatorAlignment = Alignment.BottomCenter,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.Transparent,
                contentColor = LightPrimaryBrandColor
            )
        },
    ) {
        content()
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
