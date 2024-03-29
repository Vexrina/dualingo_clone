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
import com.example.dualingo_clone.main.ui.MainScreen
import com.example.dualingo_clone.motherLanguage.ui.MotherLanguageScreen
import com.example.dualingo_clone.motherLanguage.ui.MotherLanguageViewModel
import com.example.dualingo_clone.onboard.ui.OnboardScreen
import com.example.dualingo_clone.signIn.ui.LoginScreen
import com.example.dualingo_clone.signIn.ui.LoginViewModel
import com.example.dualingo_clone.splash.SplashScreen
import com.example.dualingo_clone.ui.theme.AppTheme
import com.example.dualingo_clone.ui.theme.Dualingo_cloneTheme
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
fun CreateNavHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
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
        composable(route = "greetings") { Greeting(name = "Android") }
    }
}

@Composable
fun SplashScreenContent(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val isLoad = viewModel.isLoading.collectAsState()
    val showOnboarding = viewModel.showOnboarding.collectAsState()
    if (isLoad.value) {
        SplashScreen()
    } else if (showOnboarding.value) {
        CreateNavHost(
            navController = navController,
            startDestination = "signin"
        )
    } else {
        CreateNavHost(
            navController = navController,
            startDestination = "main"
        )
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