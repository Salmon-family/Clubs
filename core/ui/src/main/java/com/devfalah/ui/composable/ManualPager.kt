package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightPrimaryGrayColor
import com.devfalah.ui.theme.PlusJakartaSans
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

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (isRefreshing) {
                        stringResource(id = R.string.friends_privacy)
                    } else {
                        error
                    },
                    textAlign = TextAlign.Center,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Light,
                    color = LightPrimaryGrayColor
                )
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
