package com.example.dualingo_clone.onboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dualingo_clone.ui.commonComponents.ButtonComponent
import com.example.dualingo_clone.ui.theme.AppTheme

@Composable
fun OnboardScreen() {
    val viewModel: OnboardingViewModel = viewModel()
    val onboardingItem by viewModel.onboardingItems.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            .background(color = AppTheme.colors.secondary)
            .wrapContentHeight(align = Alignment.CenterVertically),
    ) {
        ImageComponent(
            imageResId = onboardingItem[0].imageResId,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )
        ThreeDotsComponent(
            modifier = Modifier
                .padding(top = 115.dp)
                .align(Alignment.CenterHorizontally),
            dotIndexInColorA = onboardingItem.size
        )
        BoldText(
            text = onboardingItem[0].textBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(263.dp)
                .padding(top = 40.dp)
        )
        TextComponent(
            text = onboardingItem[0].text,
            modifier = Modifier
                .width(263.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
        ButtonComponent(
            text = onboardingItem[0].buttonText,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(327.dp)
                .padding(top = 50.dp)
        ) {
            if (onboardingItem.size>1){
                viewModel.navigateToNextOnboardingItem()
            }
        }
        LabelWithLinkComponent(
            labelText = "Already a fillolearn user? ",
            linkText = "Log in",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {

        }
    }
}