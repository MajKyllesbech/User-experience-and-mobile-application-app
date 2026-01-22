package com.example.apptest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptest.ui.theme.BluePrimary
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    selectedStore: StoreChain,
    onStoreSelected: (StoreChain) -> Unit
) {
    var showStoreSelector by remember { mutableStateOf(false) }

    // Frontend til profilskærm
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // --- 1. Header Section ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Name
            Text(
                text = "Mads Hansen",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "mads.hansen@email.dk",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- 2. Menu Sections ---

        // REPLACED "Account" with "Preferences" (More relevant for finding groceries)
        ProfileSectionTitle("Præferencer")
        ProfileMenuItem(
            icon = Icons.Default.Store,
            title = "Mine Butikker",
            onClick = { showStoreSelector = true }
        )

        ProfileMenuItem(icon = Icons.Default.Eco, title = "Kost & Allergener") // Gluten free, Organic, etc.

        Spacer(modifier = Modifier.height(16.dp))

        // Settings Section
        ProfileSectionTitle("Indstillinger")
        ProfileMenuItem(icon = Icons.Default.Notifications, title = "Notifikationer") // Alerts for sales
        ProfileMenuItem(icon = Icons.Default.Language, title = "Sprog")
        ProfileMenuItem(icon = Icons.Default.Help, title = "Hjælp & Support")

        Spacer(modifier = Modifier.height(32.dp))

        // --- 3. Log Out Button ---
        Button(
            onClick = { /* Handle Logout */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE), contentColor = Color.Red)
        ) {
            Text("Log ud", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ProfileSectionTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit = {}
    ) {

    // Frontend til profilmenu
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

    // Box ikon
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(BluePrimary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = BluePrimary)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Titel
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )

        // Pil ikon
        Icon(
            Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.LightGray
        )
    }
    // Tynd linje som adskiller
    HorizontalDivider(
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}