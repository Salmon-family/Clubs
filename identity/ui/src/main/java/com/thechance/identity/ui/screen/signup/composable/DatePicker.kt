package com.thechance.identity.ui.screen.signup.composable

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.extension.convertToDayMonthYearFormat
import com.thechance.identity.ui.theme.LightCardColor
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.Typography
import java.util.*

@Composable
fun DatePicker(
    image: Int = 0,
    birthDate: String,
    onDateChange: (String) -> Unit
) {

    val now = Calendar.getInstance()
    val mYear = now.get(Calendar.YEAR)
    val mMonth = now.get(Calendar.MONTH)
    val mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            onDateChange(cal.time.convertToDayMonthYearFormat())
        }, mYear, mMonth, mDay
    )

    Row(
        Modifier
            .padding(8.dp)
            .background(
                color = LightCardColor,
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = birthDate,
            style = Typography.subtitle2,
            color = LightPrimaryBlackColor,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    datePickerDialog.show()
                }
        )
    }
}
