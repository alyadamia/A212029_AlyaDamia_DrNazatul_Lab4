package com.example.a212029_alyadamia_drnazatul_lab4

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.interaction.MutableInteractionSource

@Composable
fun PeriodScreen(
    onAddClick: () -> Unit,
    onCalendarClick: () -> Unit
) {
    var isFinished by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        bottomBar = { BottomNavigationBar(onAddClick = onAddClick, onCalendarClick = onCalendarClick) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
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
                        color = Color(0xFF4CAF50),
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