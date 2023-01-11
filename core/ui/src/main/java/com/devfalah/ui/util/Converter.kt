package com.devfalah.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.CallLog
import android.provider.OpenableColumns
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.devfalah.ui.R
import com.devfalah.viewmodels.util.DateConverterConstants.DAY_AGO
import com.devfalah.viewmodels.util.DateConverterConstants.HOUR_AGO
import com.devfalah.viewmodels.util.DateConverterConstants.JUST_NOW
import com.devfalah.viewmodels.util.DateConverterConstants.MINUTES_AGO
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.max


fun createFileFromContentUri(fileUri: Uri, context: Context, maxSize: Int): File {
    val imageStream = context.contentResolver.openInputStream(fileUri)
    val selectedImage = BitmapFactory.decodeStream(imageStream)
    val resizedImage = getResizedBitmap(selectedImage, maxSize)

    var fileName = ""
    fileUri.let { returnUri ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.contentResolver.query(returnUri,
                null,
                null,
                null
            )
        } else {
            context.contentResolver.query(
                returnUri,
                null,
                CallLog.Calls.NUMBER,
                null,
                CallLog.Calls.DATE
            )
        }
    }?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
    }

    val bos = ByteArrayOutputStream()
    resizedImage.compress(Bitmap.CompressFormat.JPEG, 100, bos)
    val bitmapData = bos.toByteArray()
    val outputFile = File(context.cacheDir, fileName)
    outputFile.createNewFile()
    val fos = FileOutputStream(outputFile)
    fos.write(bitmapData)
    fos.flush()
    fos.close()
    return outputFile
}

private fun getResizedBitmap(bitmap: Bitmap, maxSize: Int): Bitmap {
    val nh = (bitmap.height * (maxSize.toDouble() / bitmap.width)).toInt()
    return Bitmap.createScaledBitmap(bitmap, maxSize, nh, true)
}

@Composable
fun getDataDescription(type: Int, value: String): String {
    val number = value.toIntOrNull() ?: 0
    return when (type) {
        JUST_NOW -> stringResource(id = R.string.just_now)
        MINUTES_AGO -> {
            if (number == 1) {
                stringResource(id = R.string.minute)
            } else {
                stringResource(id = R.string.minutes, number)
            }
        }
        HOUR_AGO -> {
            if (number == 1) {
                stringResource(id = R.string.one_day)
            } else {
                stringResource(id = R.string.hour, value)
            }
        }
        DAY_AGO -> {
            if (number == 1) {
                stringResource(id = R.string.one_day)
            } else {
                stringResource(id = R.string.day, value)
            }
        }
        else -> value
    }
}