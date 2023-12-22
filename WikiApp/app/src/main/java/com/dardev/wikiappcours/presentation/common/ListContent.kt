package com.dardev.wikiappcours.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.dardev.wikiappcours.R
import com.dardev.wikiappcours.domain.model.Character
import com.dardev.wikiappcours.navigation.Screen
import com.dardev.wikiappcours.ui.theme.CHARACTER_ITEM_HEIGHT
import com.dardev.wikiappcours.ui.theme.Caelid
import com.dardev.wikiappcours.ui.theme.Caria
import com.dardev.wikiappcours.ui.theme.FarumAzul
import com.dardev.wikiappcours.ui.theme.FireGiant
import com.dardev.wikiappcours.ui.theme.L_PADDING
import com.dardev.wikiappcours.ui.theme.Leyndell
import com.dardev.wikiappcours.ui.theme.Limgrave
import com.dardev.wikiappcours.ui.theme.M_PADDING
import com.dardev.wikiappcours.ui.theme.Roundtable
import com.dardev.wikiappcours.ui.theme.S_PADDING
import com.dardev.wikiappcours.ui.theme.TitleItemColor
import com.dardev.wikiappcours.ui.theme.Volcano
import com.dardev.wikiappcours.ui.theme.otherArea

@Composable
fun ListContent(
    characters: List<Character>,
    navController: NavHostController
) {
    LazyColumn(
        Modifier.padding(),
        verticalArrangement = Arrangement.spacedBy(S_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(characters) { character ->
            CharacterItem(character, navController)
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    navController: NavHostController
){
    val painter = rememberAsyncImagePainter(model = character.picture)

    Box(
        modifier = Modifier
            .height(CHARACTER_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passCharacterId(characterId = character.id))
            }
            .padding(start = 5.dp, end = 5.dp),
        contentAlignment = Alignment.BottomStart
    ){
        Surface(shape = RoundedCornerShape(size = L_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = when(character.part){
                "Limgrave" -> Limgrave.copy(alpha = ContentAlpha.medium)
                "Caelid" -> Caelid.copy(alpha = ContentAlpha.medium)
                "Liurnia" -> Caria.copy(alpha = ContentAlpha.medium)
                "Leyndell" -> Leyndell.copy(alpha = ContentAlpha.medium)
                "MountainTop" -> FireGiant.copy(alpha = ContentAlpha.medium)
                "Rykard" -> Volcano.copy(alpha = ContentAlpha.medium)
                "FarumAzula" -> FarumAzul.copy(alpha = ContentAlpha.medium)
                "Other" -> otherArea.copy(alpha = ContentAlpha.medium)
                else -> Roundtable.copy(alpha = ContentAlpha.medium)
            },
            shape = RoundedCornerShape(
                bottomStart = L_PADDING,
                bottomEnd = L_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = M_PADDING)
            ) {
                //titre + description
                Text(
                    text = character.name,
                    color = MaterialTheme.colors.TitleItemColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = character.about,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun HeroItemPreview() {
    CharacterItem(
        character = Character(
            id = "1",
            name = "Malenia",
            picture = "",
            about = "Lorem ispum, Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum",
            strength = 0,
            intelligence = 0,
            hp = 0,
            dexterity = 0,
            arcane = 0,
            potential = 0,
            quote = "Im malenia blade of Miquella",
            part = ""
        ),
        navController = rememberNavController()
    )
}

@ExperimentalCoilApi
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HeroDarkItemPreview() {
    CharacterItem(
        character = Character(
            id = "1",
            name = "Malenia",
            picture = "",
            about = "Lorem ispum, Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum,Lorem ispum",
            strength = 0,
            intelligence = 0,
            hp = 0,
            dexterity = 0,
            arcane = 0,
            potential = 0,
            quote = "Im malenia blade of Miquella",
            part = "Caelid"
        ),
        navController = rememberNavController()
    )
}