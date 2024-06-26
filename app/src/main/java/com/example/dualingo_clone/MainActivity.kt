package com.example.dualingo_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dualingo_clone.animal_excersise.ui.AnimalExcersiseScreen
import com.example.dualingo_clone.animal_excersise.ui.AnimalExcersiseViewModel
import com.example.dualingo_clone.audition_excersise.ui.AuditionExcersiseScreen
import com.example.dualingo_clone.game_excersise.ui.GameExcersiseScreen
import com.example.dualingo_clone.game_excersise.ui.GameExcersiseViewModel
import com.example.dualingo_clone.main.ui.MainScreen
import com.example.dualingo_clone.motherLanguage.ui.MotherLanguageScreen
import com.example.dualingo_clone.motherLanguage.ui.MotherLanguageViewModel
import com.example.dualingo_clone.onboard.ui.OnboardScreen
import com.example.dualingo_clone.profile.ui.ProfileScreen
import com.example.dualingo_clone.profile.ui.ProfileViewModel
import com.example.dualingo_clone.signIn.ui.LoginScreen
import com.example.dualingo_clone.signIn.ui.LoginViewModel
import com.example.dualingo_clone.splash.SplashScreen
import com.example.dualingo_clone.ui.theme.AppTheme
import com.example.dualingo_clone.ui.theme.Dualingo_cloneTheme
import com.example.dualingo_clone.word_excersise.ui.WordExcersiseScreen
import com.example.dualingo_clone.word_excersise.ui.WordExcersiseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var motherViewModel: MotherLanguageViewModel

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Dualingo_cloneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.primary
                ) {
                    SplashScreenContent(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun CreateNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable(route = "splash") { SplashScreen() }
        composable(route = "onboard") { OnboardScreen(navController) }
        composable(route = "motherLanguage") { MotherLanguageScreen(navController) }
        composable(route = "main") { MainScreen(navController) }
        composable(route = "signin") {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController,
            )
        }
        composable(route = "profile") {
            val profileViewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(
                navController=navController,
                profileViewModel = profileViewModel
            )
        }
        composable(route = "animal_excersise") {
            val animalViewModel = hiltViewModel<AnimalExcersiseViewModel>()
            AnimalExcersiseScreen(
                animalViewModel = animalViewModel,
                navController = navController
            )
        }
        composable(route = "word_excersise") {
           val wordViewModel = hiltViewModel<WordExcersiseViewModel>()
            WordExcersiseScreen(
                wordViewModel = wordViewModel,
                navController = navController
            )
        }
        composable(route = "audition_excersise") { AuditionExcersiseScreen(navController)}
        composable(route = "game_excersise") {
            val gameViewModel = hiltViewModel<GameExcersiseViewModel>()
            GameExcersiseScreen(
                gameViewModel = gameViewModel,
                navController = navController
            )
        }
    }
}

@Composable
fun SplashScreenContent(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val isLoad = viewModel.isLoading.collectAsState()
    val showOnboarding = viewModel.showOnboarding.collectAsState()
    val isLogin = viewModel.isLogin.collectAsState()
    CreateNavHost(navController = navController)
    if (isLoad.value) {
        SplashScreen()
    } else if (showOnboarding.value) {
        navController.navigate("onboard")
    } else if (isLogin.value){
        navController.navigate("main")
    } else {
        navController.navigate("signin")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dualingo_cloneTheme {
        Greeting("Android")
    }
}