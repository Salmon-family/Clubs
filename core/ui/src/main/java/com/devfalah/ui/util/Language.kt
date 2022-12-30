package com.devfalah.ui.util

import android.content.Context
import android.content.res.Configuration
import java.util.*

class Language {

    fun updateResources(context: Context, language: String) {
        context.resources.apply {
            val locale = Locale(language)
            val config = Configuration(configuration)

            context.createConfigurationContext(configuration)
            Locale.setDefault(locale)
            config.setLocale(locale)
            config.setLayoutDirection(locale)
            context.resources.updateConfiguration(config, displayMetrics)
        }
    }
}