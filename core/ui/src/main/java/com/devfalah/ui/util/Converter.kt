package com.devfalah.ui.util

import android.content.Context
import android.graphics.Bitmap
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
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

fun createFileFromContentUri(fileUri: Uri, context: Context): File {
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

    val iStream = context.contentResolver.openInputStream(fileUri)!!
    val outputDir = context.cacheDir!!

    val outputFile = File(outputDir, fileName)
    copyStreamToFile(iStream, outputFile)
    iStream.close()
    return outputFile
}

private fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
    inputStream.use { input ->
        val outputStream = FileOutputStream(outputFile)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024)
            while (true) {
                val byteCount = input.read(buffer)
                if (byteCount < 0) break
                output.write(buffer, 0, byteCount)
            }
            output.flush()
        }
    }
}

@Composable
fun getDataDescription(type: Int): String {
    return when (type) {
        JUST_NOW -> stringResource(id = R.string.just_now)
        MINUTES_AGO -> stringResource(id = R.string.minutes)
        HOUR_AGO -> stringResource(id = R.string.hour)
        DAY_AGO -> stringResource(id = R.string.day)
        else -> ""
    }
}


fun resizePhoto(bitmap: Bitmap): Bitmap {
    val actualWidth = bitmap.width
    val actualHeight = bitmap.height
    val aspRat = actualWidth / actualHeight
    val width = 400
    val height = width * aspRat
    val b = Bitmap.createScaledBitmap(bitmap, width, height, false)
    return b
}