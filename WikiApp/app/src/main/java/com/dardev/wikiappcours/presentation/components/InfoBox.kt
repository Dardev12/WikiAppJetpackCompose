package com.dardev.wikiappcours.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dardev.wikiappcours.R
import com.dardev.wikiappcours.ui.theme.INFO_ICON_SIZE

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
) {
    //condition optionnel
    if(bigText != "0") {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(
                    modifier = Modifier
                        .size(INFO_ICON_SIZE),
                    painter = icon,
                    contentDescription = stringResource(R.string.info_icon),
                    tint = iconColor
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = bigText,
                    color = textColor,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Black,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = smallText,
                    color = textColor,
                    fontSize = MaterialTheme.typography.overline.fontSize,
                )
            }
        }
    }
}