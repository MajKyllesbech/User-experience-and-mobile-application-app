package com.example.apptest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.apptest.ui.theme.AppTestTheme

/**
 * Your welcome screen.
 * It has a background image and a button.
 */
@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onHomeClicked: () -> Unit) {
    // Box is used to stack items on top of each other.
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Centers the button
    ) {

        // --- The Background Picture ---
        // IMPORTANT: You still need to add your own image to 'res/drawable'
        // and replace 'R.drawable.ic_launcher_background'
        Image(
            // I'm using 'R.drawable.ic_launcher_background' as a placeholder.
            // You should replace this with your own image.
            painter = painterResource(id = R.drawable.ic_launcher_background), // <-- CHANGE THIS
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // This makes the image fill the screen
        )

        // --- The "Link" (Button) ---
        Button(onClick = onHomeClicked) {
            Text("Find My Groceries")
        }
    }
}


// --- Preview ---
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    AppTestTheme {
        WelcomeScreen(onHomeClicked = {})
    }
}