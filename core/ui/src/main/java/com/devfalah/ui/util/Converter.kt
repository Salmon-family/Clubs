package com.devfalah.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
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


fun createFileFromContentUri(fileUri: Uri, context: Context, maxSize: Int): File {
    val imageStream = context.contentResolver.openInputStream(fileUri)
    val selectedImage = BitmapFactory.decodeStream(imageStream)
    val resizedImage = getResizedBitmap(selectedImage, maxSize)

    var fileName = ""
    fileUri.let { returnUri ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.contentResolver.query(returnUri, null, null, null)
        } else {
            throw Throwable("Build VERSION CODES NOT SUPPORT")
        }
    }?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
    }

    val bos = ByteArrayOutputStream()
    resizedImage.compress(Bitmap.CompressFormat.PNG, 0, bos)
    val bitmapdata = bos.toByteArray()
    val outputFile = File(context.cacheDir, fileName)
    outputFile.createNewFile()
    val fos = FileOutputStream(outputFile)
    fos.write(bitmapdata)
    fos.flush()
    fos.close()
    return outputFile
}

private fun getResizedBitmap(bitmap: Bitmap, maxSize: Int): Bitmap {
    val actualWidth = bitmap.width
    val actualHeight = bitmap.height
    var aspRat = actualWidth / actualHeight

    return if (aspRat > 0) {
        var height = maxSize * aspRat
        Bitmap.createScaledBitmap(bitmap, maxSize, height, false)
    } else {
        aspRat = actualHeight / actualWidth
        val width = maxSize * aspRat
        Bitmap.createScaledBitmap(bitmap, maxSize, width, false)
    }
}

@Composable
fun getDataDescription(type: Int, value: String): String {
    val number = value.toIntOrNull() ?: 0
    return when (type) {
        JUST_NOW -> stringResource(id = R.string.just_now)
        MINUTES_AGO -> {
            if (number == 1) { stringResource(id = R.string.minute) }
            else { stringResource(id = R.string.minutes, number) }
        }
        HOUR_AGO -> {
            if (number == 1) { stringResource(id = R.string.one_day) }
            else { stringResource(id = R.string.hour, value) }
        }
        DAY_AGO -> {
            if (number == 1) { stringResource(id = R.string.one_day) }
            else { stringResource(id = R.string.day, value) }
        }
        else -> value
    }
}