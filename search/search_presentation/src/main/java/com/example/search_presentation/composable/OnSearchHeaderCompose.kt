package com.example.search_presentation.composable

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.Purple

@ExperimentalComposeUiApi
@Composable
fun TopSearchBar(
    valueState: String,
    label: String,
    onTextChangeListener: (String) -> Unit,
    modifier: Modifier = Modifier,
    isSingleLine: Boolean,
    onImeAction: () -> Unit = {}


) {
    val keyboardController = LocalSoftwareKeyboardController.current



        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .border(
                    BorderStroke(width = 2.dp, color = Color.Black),
                )
                .background(color = Color.White)
        ) {

            TextField(
                value = valueState, placeholder = { Text(label, fontSize = 11.sp)},
                onValueChange = onTextChangeListener,
                modifier  = Modifier

                    .height(50.dp)
                    .width(300.dp)
                    .border(
                        border = BorderStroke(2.dp, color = Color.Black),
                        shape = RoundedCornerShape(50)
                    ),
                singleLine = isSingleLine,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Money Icon", modifier = Modifier.clickable {
                            onTextChangeListener("")
                        }
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 11.sp, color = MaterialTheme.colors.onBackground),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    onImeAction()
                    keyboardController?.hide()
                })
            )

        }

}

