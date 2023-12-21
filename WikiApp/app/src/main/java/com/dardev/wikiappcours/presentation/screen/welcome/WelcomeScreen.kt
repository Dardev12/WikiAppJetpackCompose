package com.dardev.wikiappcours.presentation.screen.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dardev.wikiappcours.domain.model.OnBoardingPage
import com.dardev.wikiappcours.ui.theme.welcomeScreenBackgroundColor
import org.koin.androidx.compose.koinViewModel
import com.dardev.wikiappcours.R
import com.dardev.wikiappcours.navigation.Screen
import com.dardev.wikiappcours.ui.theme.S_PADDING
import com.dardev.wikiappcours.ui.theme.XL_PADDING
import com.dardev.wikiappcours.ui.theme.buttonBackgroundColor
import com.dardev.wikiappcours.ui.theme.buttonTextColor
import com.dardev.wikiappcours.ui.theme.descriptionColor
import com.dardev.wikiappcours.ui.theme.titleColor

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = koinViewModel()
)  {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val currentScreen = remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        when(currentScreen.value){
            1-> {
              PageScreen(onBoardingPage = pages[0])
                Spacer(modifier = Modifier.height(30.dp))
                FinishButton(modifier = Modifier,text="Next") {
                    currentScreen.value = pages[0].goTo
                }
            }
            2-> {
                PageScreen(onBoardingPage = pages[1])
                Spacer(modifier = Modifier.height(30.dp))
                Row(Modifier.fillMaxWidth()) {
                    FinishButton(modifier = Modifier,text="Next") {
                        currentScreen.value = pages[1].goTo
                    }
                }
            }
            3-> {
                PageScreen(onBoardingPage = pages[2])
                Spacer(modifier = Modifier.height(30.dp))
                FinishButton(modifier = Modifier, text = "Finish") {
                    navController.navigate(Screen.Home.route)
                    viewModel.saveOnBoardingState(completed = true)
                }
            }
        }
    }
}

@Composable
fun PageScreen(onBoardingPage: OnBoardingPage){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(70))
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f)
            ,
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),

            text = stringResource(id = onBoardingPage.title),
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = XL_PADDING)
                .padding(top = S_PADDING),
            text = stringResource(id = onBoardingPage.description),
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}



@Composable
fun FinishButton(
    modifier: Modifier,
    text:String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = XL_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = true
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = MaterialTheme.colors.buttonTextColor
                )
            ) {
                Text(text = text)
            }
        }
    }
}