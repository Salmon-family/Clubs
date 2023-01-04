package com.devfalah.ui.screen.menu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.accountSettings.ACCOUNT_SETTINGS_SCREEN
import com.devfalah.ui.screen.friendrequest.FRIEND_REQUEST_SCREEN
import com.devfalah.ui.screen.menu.composable.*
import com.devfalah.ui.screen.menu.composable.language.LanguageBottomSheet
import com.devfalah.ui.screen.menu.composable.theme.ThemeBottomSheet
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.screen.reportBug.ROUTE_REPORT_BUG
import com.devfalah.ui.screen.savedPosts.SAVED_SCREEN
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.util.Language
import com.devfalah.viewmodels.menu.MenuViewModel
import com.devfalah.viewmodels.menu.UserUiState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun MenuScreen(
    navController: NavController, viewModel: MenuViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val context = LocalContext.current
    MenuContent(
        state = state,
        onClickProfile = { navController.navigateToProfile(state.id) },
        onClickSavedPosts = { navController.navigate(route = SAVED_SCREEN) },
        onClickAccountSettings = { navController.navigate(route = ACCOUNT_SETTINGS_SCREEN) },
        onClickFriendsRequests = { navController.navigate(route = FRIEND_REQUEST_SCREEN) },
        onClickReportBug = { navController.navigate(ROUTE_REPORT_BUG) },
        onClickLogOut = viewModel::onClickLogOut,
        onChangeLanguage = viewModel::onChangeLanguage,
        onChangeTheme = viewModel::onChangeTheme
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
    LaunchedEffect(key1 = state.logout) {
        if (state.logout) {
            try {
                val intent = Intent(
                    context, Class.forName("com.thechance.identity.ui.main.AuthenticationActivity")
                )
                (context as Activity).startActivity(intent)
                context.finish()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    LaunchedEffect(key1 = state.language.value) {
        Language().updateResources(context = context, language = state.language.value)
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuContent(
    state: UserUiState,
    onClickProfile: () -> Unit,
    onClickSavedPosts: () -> Unit,
    onClickAccountSettings: () -> Unit,
    onClickFriendsRequests: () -> Unit,
    onClickReportBug: () -> Unit,
    onChangeLanguage: (Int) -> Unit,
    onChangeTheme: (Int) -> Unit,
    onClickLogOut: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    var switch by remember {
        mutableStateOf(Preferences.THEME)
    }

    LaunchedEffect(key1 = state.language) {
        scope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            if (switch == Preferences.LANGUAGE) {
                LanguageBottomSheet(
                    onChangeLanguage = onChangeLanguage,
                    language = state.language.value,
                )
            } else {
                ThemeBottomSheet(onChangeTheme = onChangeTheme)
            }
        },
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetBackgroundColor = MaterialTheme.colors.background
    ) {
        Column {

            AppBar(
                title = stringResource(id = R.string.menu),
                showBackButton = false,
                actions = {
                    IconButton(onClick = onClickLogOut) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_logout),
                            contentDescription = "",
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    TopSection(
                        state = state,
                        onClickProfile = onClickProfile,
                        onClickSavedThreads = onClickSavedPosts
                    )
                }

                item {
                    AccountSection(
                        onClickAccountSettings = onClickAccountSettings,
                        onClickFriendsRequests = onClickFriendsRequests,
                    )
                }

                item {
                    PreferencesSection(onClickTheme = {
                        scope.launch {
                            switch = Preferences.THEME
                            setSheetVisibility(sheetState)
                        }
                    }, onClickLanguage = {
                        scope.launch {
                            switch = Preferences.LANGUAGE
                            setSheetVisibility(sheetState)
                        }
                    })
                }

                item {
                    MenuItem(
                        text = stringResource(R.string.report_bugs),
                        painter = painterResource(id = R.drawable.ic_menu_bug),
                        onClickItem = onClickReportBug
                    )
                }

                item {
                    Text(
                        text = "${stringResource(id = R.string.version)}  ${getVersion(context = LocalContext.current)}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.secondaryVariant,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }

}

private fun getVersion(context: Context): String {
    return try {
        val pInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        ""
    }
}

@OptIn(ExperimentalMaterialApi::class)
private suspend fun setSheetVisibility(
    sheetState: ModalBottomSheetState
) {
    if (sheetState.isVisible) {
        sheetState.hide()
    } else {
        sheetState.show()
    }
}