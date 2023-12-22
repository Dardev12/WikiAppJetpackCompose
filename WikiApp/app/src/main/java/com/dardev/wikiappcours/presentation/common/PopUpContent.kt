package com.dardev.wikiappcours.presentation.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dardev.wikiappcours.ui.theme.Caelid
import com.dardev.wikiappcours.ui.theme.Caria
import com.dardev.wikiappcours.ui.theme.FarumAzul
import com.dardev.wikiappcours.ui.theme.FireGiant
import com.dardev.wikiappcours.ui.theme.Leyndell
import com.dardev.wikiappcours.ui.theme.Limgrave
import com.dardev.wikiappcours.ui.theme.Roundtable
import com.dardev.wikiappcours.ui.theme.Volcano
import com.dardev.wikiappcours.ui.theme.otherArea

@Composable
fun PopUpContent(
    title:String,
    content:String,
    partColor:String,
    openDialog: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(
                text=title,
                fontWeight= FontWeight.Bold,
                fontSize = 16.sp
            )
        },
        text = {
            Text(
                text=content,
                fontSize = 16.sp
            )
        },

        confirmButton = {
            IconButton(onClick = {openDialog.value = false }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "check")
            }
        },
        dismissButton = {
            /*IconButton(onClick = {openDialog.value = false }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
            }*/
        },
        backgroundColor = when (partColor) {
            "Limgrave" -> Limgrave
            "Caelid" -> Caelid
            "Liurnia" -> Caria
            "Leyndell" -> Leyndell
            "MountainTop" -> FireGiant
            "Rykard" -> Volcano
            "FarumAzula" -> FarumAzul
            "Other" -> otherArea
            else -> Roundtable
        },
        contentColor = Color.White
    )
}