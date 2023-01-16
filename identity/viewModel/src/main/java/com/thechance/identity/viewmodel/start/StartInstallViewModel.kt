package com.thechance.identity.viewmodel.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.GetStartInstallUseCase
import com.thechance.identity.usecases.SaveTokenUseCase
import com.thechance.identity.usecases.SetStartInstallUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartInstallViewModel @Inject constructor(
    private val getStartInstallUseCase: GetStartInstallUseCase,
    private val setStartInstallUseCase: SetStartInstallUseCase,
    saveToken: SaveTokenUseCase,
) : ViewModel() {

    init {
        setStartInstall()
        viewModelScope.launch {
            saveToken()
        }
    }

    private fun setStartInstall() {
        viewModelScope.launch {
            setStartInstallUseCase(true)
        }
    }

    fun getStartInstall(): Boolean? {
        return getStartInstallUseCase.invoke()
    }
}