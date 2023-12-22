package com.dardev.wikiappcours.presentation.screen.detail

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.dardev.wikiappcours.R
import com.dardev.wikiappcours.domain.model.Character
import com.dardev.wikiappcours.presentation.common.PopUpContent
import com.dardev.wikiappcours.presentation.components.InfoBox
import com.dardev.wikiappcours.ui.theme.Caelid
import com.dardev.wikiappcours.ui.theme.Caria
import com.dardev.wikiappcours.ui.theme.EXPANDED_RADIUS_LEVEL
import com.dardev.wikiappcours.ui.theme.FarumAzul
import com.dardev.wikiappcours.ui.theme.FireGiant
import com.dardev.wikiappcours.ui.theme.INFO_ICON_SIZE
import com.dardev.wikiappcours.ui.theme.L_PADDING
import com.dardev.wikiappcours.ui.theme.Leyndell
import com.dardev.wikiappcours.ui.theme.Limgrave
import com.dardev.wikiappcours.ui.theme.MIN_SHEET_HEIGHT
import com.dardev.wikiappcours.ui.theme.M_PADDING
import com.dardev.wikiappcours.ui.theme.NightModeP
import com.dardev.wikiappcours.ui.theme.Roundtable
import com.dardev.wikiappcours.ui.theme.S_PADDING
import com.dardev.wikiappcours.ui.theme.TitleItemColor
import com.dardev.wikiappcours.ui.theme.Volcano
import com.dardev.wikiappcours.ui.theme.XL_PADDING
import com.dardev.wikiappcours.ui.theme.otherArea
import com.dardev.wikiappcours.util.Constants.ABOUT_TEXT_MAX_LINES
import com.dardev.wikiappcours.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailContent(
    navController: NavHostController,
    selectedCharacter: Character?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = true){
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(parseColor(darkVibrant))
        )
    }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            XL_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedCharacter?.let {
                BottomSheetContent(
                    selectedCharacter = it,
                    infoBoxIconColor = Color(parseColor(onDarkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedCharacter?.let { character ->
                BackgroundContent(
                    characterImage = character.picture,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    characterImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "${characterImage}"
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        placeholder = painterResource(id = R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = S_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    selectedCharacter: Character,
    infoBoxIconColor: Color = Color.Black,
    contentColor: Color = NightModeP
) {
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(
                color = when (selectedCharacter.part) {
                    "Limgrave" -> Limgrave
                    "Caelid" -> Caelid
                    "Liurnia" -> Caria
                    "Leyndell" -> Leyndell
                    "MountainTop" -> FireGiant
                    "Rykard" -> Volcano
                    "FarumAzula" -> FarumAzul
                    "Other" -> otherArea
                    else -> Roundtable
                }
            )
            .padding(all = L_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = L_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier
                    .weight(6f),
                text = selectedCharacter.name,
                color = colors.TitleItemColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = S_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_strength),//Strength
                iconColor = infoBoxIconColor,
                bigText = "${selectedCharacter.strength}",
                smallText = stringResource(R.string.strength_character),//Strength
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_brain),//Intelligence
                iconColor = infoBoxIconColor,
                bigText = "${selectedCharacter.intelligence}",
                smallText = stringResource(R.string.intelligence_character),//Intelligence
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_hp),//hp
                iconColor = infoBoxIconColor,
                bigText = "${selectedCharacter.hp}",
                smallText = stringResource(R.string.health_character),//hp
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_dex),//Precision
                iconColor = infoBoxIconColor,
                bigText = "${selectedCharacter.dexterity}",
                smallText = stringResource(R.string.dexterity_character),//Precision
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),//Precision
                iconColor = infoBoxIconColor,
                bigText = "${selectedCharacter.arcane}",
                smallText = stringResource(R.string.arcane_character),//Precision
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_potential),//Precision
                iconColor = infoBoxIconColor,
                bigText = "${selectedCharacter.potential}",
                smallText = stringResource(R.string.potential_character),//Precision
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about_character),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .padding(bottom = M_PADDING)
                .clickable{
                    openDialog.value = true
                },
            text = selectedCharacter.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES,
            overflow = TextOverflow.Ellipsis,
        )
        if (openDialog.value) {
            PopUpContent(
                title = stringResource(R.string.about_character),
                content = selectedCharacter.about,
                partColor = selectedCharacter.part,
                openDialog = openDialog
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //Quote
            Column {
                if(selectedCharacter.quote != "") {
                    Text(
                        modifier = Modifier.padding(bottom = S_PADDING),
                        text = stringResource(id = R.string.quote_character),
                        color = contentColor,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = S_PADDING),
                        text = "\" ${selectedCharacter.quote} \"",
                        color = contentColor,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        fontWeight = FontWeight.Normal
                    )
                }

            }

        }
    }
}


@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0f + fraction
            else -> fraction
        }
    }