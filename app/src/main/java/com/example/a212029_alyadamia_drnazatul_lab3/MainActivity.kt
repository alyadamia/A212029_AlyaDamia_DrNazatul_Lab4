package com.example.a212029_alyadamia_drnazatul_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a212029_alyadamia_drnazatul_lab3.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // This will automatically apply the colors you generate from the Material Theme Builder
            AppTheme (
                dynamicColor = false
                ) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var isFinished by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_app),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                alpha = 0.4f
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { focusManager.clearFocus() }
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                TopHeader()
                Spacer(modifier = Modifier.height(20.dp))

                // Hanya panggil sekali sahaja di sini
                MainInfo()
                Spacer(modifier = Modifier.height(20.dp))

                ButtonBox(onButtonClick = {
                    isFinished = true
                    focusManager.clearFocus()
                })

                if (isFinished) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Congratulations, you have finished updating your period day for this month.",
                        color = Color(0xFF4CAF50), // Keeping standard green for success message
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                AverageSection()
                Spacer(modifier = Modifier.height(30.dp))

                FeelingCard()
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun TopHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // Tolak item ke kiri dan kanan
        verticalAlignment = Alignment.CenterVertically
    ) {

        // BAHAGIAN KIRI: Logo + Teks "Today"
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                // GANTIKAN 'logo_app' DENGAN NAMA FAIL GAMBAR LOGO ANDA
                painter = painterResource(id = R.drawable.period_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp) // Jarak sikit antara logo dan perkataan "Today"
            )
            Text(
                text = "Today",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // BAHAGIAN KANAN: Butang Notification (Kekal seperti asal)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
@Composable
fun MainInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Period", fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
        Text(
            text = "21 DAYS LEFT",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary // Upgraded to Material Theme
        )
        Text(text = "Apr 20 - Next Period", fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Composable
fun ButtonBox(onButtonClick: () -> Unit) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text = "Period Ends", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
    }
}

// TASK 2 & 3: Wrapped in a Material Card AND added an expansion animation
@Composable
fun AverageCard(title: String, value: String, icon: Int, description: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded } // Toggle expansion
            .animateContentSize( // Smooth animation when expanding
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = title, fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    modifier = Modifier.size(24.dp)
                )
            }

            // Expanded content
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
fun AverageSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AverageCard(
            title = "Average period",
            value = "6 Days",
            icon = R.drawable.ic_drop,
            description = "Based on your last 6 logged cycles."
        )
        AverageCard(
            title = "Average cycle",
            value = "25 Days",
            icon = R.drawable.ic_cycle,
            description = "A standard cycle ranges from 21 to 35 days."
        )
    }
}

// TASK 2: Upgraded from a Box/Column to a Material Card
@Composable
fun FeelingCard() {
    var moodInput by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Daily Log", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
            Text(text = "How are you feeling today?", fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = moodInput,
                onValueChange = { moodInput = it },
                placeholder = { Text("Example: Happy, Tired...", fontSize = 14.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (moodInput.isNotEmpty()) {
                            displayText = "My Feeling Today: $moodInput"
                            focusManager.clearFocus()
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (moodInput.isNotEmpty()) {
                        displayText = "My Feeling Today: $moodInput"
                        focusManager.clearFocus()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Save Feeling", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            }

            if (displayText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = displayText,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem("Home", Icons.Default.Home)
        NavItem("Calendar", Icons.Default.DateRange)
        NavItem("Settings", Icons.Default.Settings)
    }
}

@Composable
fun NavItem(label: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = label, tint = MaterialTheme.colorScheme.primary)
        Text(text = label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface)
    }
}

// Preview untuk Light Mode
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLight() {
    AppTheme(dynamicColor = false) {
        MainScreen()
    }
}

// Preview untuk Dark Mode
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES // Paksa preview jadi gelap
)
@Composable
fun PreviewDark() {
    AppTheme(dynamicColor = false) {
        MainScreen()
    }
}