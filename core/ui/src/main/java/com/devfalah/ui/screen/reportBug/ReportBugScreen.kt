package com.devfalah.ui.screen.reportBug

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.ButtonWithLoading
import com.devfalah.ui.composable.CustomTextField
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.viewmodels.reportBug.ReportBugUiState
import com.devfalah.viewmodels.reportBug.ReportBugViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ReportBugScreen(
    navController: NavController,
    viewModel: ReportBugViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    MenuContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onMessageChange = viewModel::onMessageChange,
        onClickSend = viewModel::onClickSend
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@Composable
fun MenuContent(
    state: ReportBugUiState,
    onClickBack: () -> Unit,
    onMessageChange: (String) -> Unit,
    onClickSend: () -> Unit
) {
    Column {

        AppBar(
            title = stringResource(R.string.report_bugs),
            onBackButton = onClickBack
            )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                CustomTextField(
                    title = stringResource(R.string.bug_message),
                    hint = stringResource(R.string.bug_report_hint),
                    shape = RoundedCornerShape(16.dp),
                    value = state.bugMessage,
                    onValueChange = onMessageChange,
                    maxLines = 7
                )
            }

            item {
                ButtonWithLoading(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.send),
                    onClick = onClickSend,
                    isLoading = false
                )
            }

        }

        LaunchedEffect(key1 = state.isSuccessful) {
            if (state.isSuccessful) onClickBack()
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewReportBug() {
    MenuContent(
        ReportBugUiState(),
        onClickBack = {},
        {}, {}
    )
}