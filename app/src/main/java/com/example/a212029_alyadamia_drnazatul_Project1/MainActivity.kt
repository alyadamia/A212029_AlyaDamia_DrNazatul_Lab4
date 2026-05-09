package com.example.a212029_alyadamia_drnazatul_Project1

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
import com.example.a212029_alyadamia_drnazatul_Project1.ui.theme.AppTheme


enum class Screen {
    Home,
    AddPeriod,
    Calendar,
    Symptoms,
    Insight
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
            MainScreen(
                onAddClick = { navController.navigate(Screen.AddPeriod.name) },
                onCalendarClick = { navController.navigate(Screen.Calendar.name) },
                onSymptomsClick = { navController.navigate(Screen.Symptoms.name) },
                onInsightsClick = { navController.navigate(Screen.Insight.name) },
                viewModel = viewModel
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

        //Skrin 4 : Symptoms
        composable(route = Screen.Symptoms.name) {
            SymptomsScreen(
                onBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }

        //Skrin 5 : Insights
        composable(route = Screen.Insight.name) {
            InsightsScreen(
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
        MainScreen(
            onAddClick = {},
            onCalendarClick = {},
            onSymptomsClick = {},
            onInsightsClick = {},
            viewModel = viewModel())
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
        MainScreen(
            onAddClick = {},
            onCalendarClick = {},
            onSymptomsClick = {},
            onInsightsClick = {},
            viewModel = viewModel())
    }
}
