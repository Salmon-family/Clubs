package com.devfalah.viewmodels.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
): ViewModel() {


    fun login(){
        viewModelScope.launch {
            val user = loginUseCase("devfalah","20012001")

            Log.e("DEVFALAH",user.toString())
        }

    }

}