package com.devfalah.viewmodels.main

import androidx.lifecycle.ViewModel
import com.devfalah.usecases.user.CheckIfLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class MainViewModel @Inject constructor(
//    private val checkIfLoggedInUseCase: CheckIfLoggedInUseCase,
//    private val getLanguageUseCase: GetLanguageUseCase,
//): ViewModel() {
//
//    fun checkIfLoggedIn() = checkIfLoggedInUseCase()
//
//    fun onGetLanguage() = getLanguageUseCase
//
//}