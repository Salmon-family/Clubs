package com.devfalah.ui.screen.menu

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.accountSettings.ACCOUNT_SETTINGS_SCREEN
import com.devfalah.ui.screen.friendrequest.FRIEND_REQUEST_SCREEN
import com.devfalah.ui.screen.menu.composable.*
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.screen.savedPosts.SAVED_SCREEN
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.menu.MenuViewModel
import com.devfalah.viewmodels.menu.UserUiState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val context = LocalContext.current
    MenuContent(
        navController = navController,
        state = state,
        onClickProfile = { navController.navigateToProfile(state.userId) },
        onClickSavedPosts = { navController.navigate(route = SAVED_SCREEN) },
        onClickAccountSettings = { navController.navigate(route = ACCOUNT_SETTINGS_SCREEN) },
        onClickFriendsRequests = { navController.navigate(route = FRIEND_REQUEST_SCREEN) },
        onClickTheme = {},
        onClickLanguage = {},
        onClickReportBug = {},
        onClickLogOut = viewModel::onClickLogOut,
        onChangeLanguage = viewModel::onChangeLanguage
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
            darkIcons = false
        )
    }
    LaunchedEffect(key1 = state.logout) {
        if (state.logout) {
            (context as Activity).finish()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuContent(
    navController: NavController,
    state: UserUiState,
    onClickProfile: () -> Unit,
    onClickSavedPosts: () -> Unit,
    onClickAccountSettings: () -> Unit,
    onClickFriendsRequests: () -> Unit,
    onClickTheme: () -> Unit,
    onClickLanguage: () -> Unit,
    onClickReportBug: () -> Unit,
    onClickLogOut: () -> Unit,
    onChangeLanguage: (Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded}
    )

    val scope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        scope.launch { sheetState.hide() }
    }

    var switch by remember {
        mutableStateOf(true)
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { LanguageBottomSheet(onChangeLanguage = onChangeLanguage) },
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ){
        Column {

            AppBar(
                title = stringResource(id = R.string.menu),
                navHostController = navController,
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .nonRippleEffect { onClickLogOut() }
                    )
                })

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
                    PreferencesSection(
                        onClickTheme = {},
                        onClickLanguage = {
                            scope.launch {
                                if(sheetState.isVisible){
                                    sheetState.hide()
                                }else{
                                    sheetState.show()
                                }
                            }
                        }
                    )
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
                            color = LightTernaryBlackColor,
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
        val pInfo: PackageInfo =
            context.packageManager.getPackageInfo(context.packageName, 0)
        pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        ""
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMenu() {
    MenuContent(rememberNavController(), UserUiState(), {}, {}, {}, {}, {}, {}, {}, {}, {})
}