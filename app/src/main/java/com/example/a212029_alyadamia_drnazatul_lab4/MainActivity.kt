package com.example.a212029_alyadamia_drnazatul_lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a212029_alyadamia_drnazatul_lab4.ui.theme.AppTheme


enum class Screen {
    Home,
    AddPeriod,
    Calendar
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(dynamicColor = false) {
                PeriodApp()
            }
        }
    }
}

@Composable
fun PeriodApp(viewModel: PeriodViewModel = viewModel()) {//tarikh yng disimpan boleh dibaca oleh skrin yng lain

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        // Skrin 1: Home (MainScreen)
        composable(route = Screen.Home.name) {
            PeriodScreen(
                onAddClick = { navController.navigate(Screen.AddPeriod.name) },
                onCalendarClick = { navController.navigate(Screen.Calendar.name) }
            )
        }

        // Skrin 2: Add Period
        composable(route = Screen.AddPeriod.name) {
            AddPeriodScreen(
                onBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }

        // Skrin 3: Calendar View
        composable(route = Screen.Calendar.name) {
            CalendarScreen(
                onBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}



// Preview untuk Light Mode
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLight() {
    AppTheme(dynamicColor = false) {
        PeriodScreen(onAddClick = {}, onCalendarClick = {})
    }
}

// Preview untuk Dark Mode
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewDark() {
    AppTheme(dynamicColor = false) {
        PeriodScreen(onAddClick = {}, onCalendarClick = {})
    }
}