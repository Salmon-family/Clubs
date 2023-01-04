package com.devfalah.viewmodels.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.language.UpdateLanguageUseCase
import com.devfalah.usecases.user.DeleteUserUseCase
import com.devfalah.usecases.user.GetMyAccountProfileDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    val deleteUser: DeleteUserUseCase,
    val myAccountProfileDetails: GetMyAccountProfileDetailsUseCase,
    private val updateLanguage: UpdateLanguageUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onGetLanguage()
        getUserPhotoUrl()
    }

    private fun getUserPhotoUrl() {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            try {
                val user = myAccountProfileDetails()
                _uiState.update { it.copy(profilePhotoUrl = user.profileUrl, id = user.id) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    fun onClickLogOut() {
        viewModelScope.launch {
            deleteUser()
            _uiState.update { it.copy(logout = true) }
        }
    }

    fun onChangeLanguage(selectedItemIndex: Int) {
        viewModelScope.launch {
            val language = if (selectedItemIndex == 0) {
                AppLanguage.ENGLISH
            } else {
                AppLanguage.ARABIC
            }
            updateLanguage.changeLanguage(language.value)
            _uiState.update { it.copy(language = language) }
        }
    }


    private fun onGetLanguage() {
        viewModelScope.launch {
            if (updateLanguage.getAppLanguage() == AppLanguage.ENGLISH.value) {
                _uiState.update { it.copy(language = AppLanguage.ENGLISH) }
            } else {
                _uiState.update { it.copy(language = AppLanguage.ARABIC) }
            }
        }
    }

    fun onChangeTheme(selectedItemIndex: Int) {
        val theme = when (selectedItemIndex) {
            0 -> ThemeType.LIGHT
            1 -> ThemeType.DARK
            else -> ThemeType.LIGHT
        }
        _uiState.update { it.copy(theme = theme) }
    }
}