package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.*
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.isEditedButtonEnabled

@Composable
fun InputDialogView(
    state: CommentUIState,
    onDismiss: () -> Unit,
    onExit: () -> Unit,
    text: String,
    onValueChanged: (String) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Edit Comment",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onSurface
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    value = text,
                    maxLines = 6,
                    onValueChange = onValueChanged,
                    placeholder = {
                        Text(
                            text = "Enter your comment...",
                            color = LightTernaryBlackColor,
                            style = AppTypography.body1
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomButtonDialog(
                        modifier = Modifier.weight(1f),
                        titleButton = "Cancel",
                        titleColor = MaterialTheme.colors.primaryVariant,
                        backgroundColor = MaterialTheme.colors.onBackground,
                        onClick = onExit
                    )
                    WidthSpacer8()
                    CustomButtonDialog(
                        modifier = Modifier.weight(1f),
                        titleButton = "Edit",
                        backgroundColor = LightPrimaryBrandColor,
                        isEnabled = state.isEditedButtonEnabled(),
                        onClick = onDismiss
                    )
                }
            }
        }
    }
}

@Composable
fun CustomButtonDialog(
    modifier: Modifier = Modifier,
    titleButton: String,
    titleColor: Color = WhiteColor,
    backgroundColor: Color,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        enabled = isEnabled,
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = titleColor
        )
    ) {
        Text(text = titleButton, style = AppTypography.button)
    }
}
