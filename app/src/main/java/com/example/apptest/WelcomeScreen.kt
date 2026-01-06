package com.example.apptest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptest.ui.theme.BluePrimary

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onHomeClicked: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // 1. The Background Image
        Image(
            painter = painterResource(id = R.drawable.welcome_bg), // Make sure your image is named welcome_bg
            contentDescription = "Welcome Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 2. A Gradient Overlay (Make text readable)
        // This fades from transparent at the top to dark black at the bottom
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f) // Darker at bottom
                        ),
                        startY = 300f // Start gradient partway down
                    )
                )
        )

        // 3. The Content (Text and Button)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom // Push everything to bottom
        ) {
            // Main Headline
            Text(
                text = "Din guide til\nbillig indkøb", // "Your guide to cheap shopping"
                style = MaterialTheme.typography.displayMedium, // Big text
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Find de bedste tilbud nær dig og spar penge hver dag.",
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            // The "Kom i gang" Button
            Button(
                onClick = onHomeClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary // Uses the Blue we defined earlier
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Kom i gang", // "Get Started"
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp)) // Extra space at very bottom
        }
    }
}