package com.example.onboarding_presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.example.core_ui.DarkGray
import com.example.core_ui.spacing
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.example.core_ui.fontSize


@Composable
fun OnBottomButtonCompose(
    navController: NavController,
    skipButtonName: String,
    nextButtonName: String,
    fontFamily: FontFamily,
    buttonColor: Color = DarkGray,
    skipTo: String,
    nextOnClick: () -> Unit,
    arrowDrawableId: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(
            horizontal = MaterialTheme.spacing.view_4x,
            vertical = MaterialTheme.spacing.view_4x
        )
    ) {

        Text(
            text = skipButtonName, Modifier.clickable {
                navController.popBackStack()
                navController.navigate(skipTo)
            }, color = buttonColor,
            fontFamily = fontFamily,
            fontSize = MaterialTheme.fontSize.view_16x
        )


        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(
                horizontal = MaterialTheme.spacing.view_4x, vertical = MaterialTheme.spacing.view_4x
            )
        ) {

            Text(
                text = nextButtonName,
                modifier = Modifier.clickable {
                    nextOnClick()
                },
                color = buttonColor,
                fontFamily = fontFamily,
                fontSize = MaterialTheme.fontSize.view_16x
            )

            if (arrowDrawableId != 0) {
                Image(
                    painter = painterResource(id = arrowDrawableId),
                    contentDescription = "nextArrow",
                    colorFilter = ColorFilter.tint(buttonColor)
                )
            }

        }
    }


}