package com.example.beon.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beon.designsystem.components.BeOnLogo
import com.example.beon.designsystem.components.BeOnLogoSize
import com.example.beon.designsystem.theme.BeOnTheme
import com.example.beon.feature.home.homeNavItems

@Composable
fun HomeTopNav(
    modifier: Modifier = Modifier,
) {
    var activeTab by remember { mutableStateOf(homeNavItems.first()) }
    val horizontalInset = BeOnTheme.safeArea.mobile

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = BeOnTheme.spacing.xs),
    ) {
        BeOnLogo(
            modifier = Modifier.padding(horizontal = horizontalInset),
            size = BeOnLogoSize.Nav,
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BeOnTheme.spacingScale.space3),
            contentPadding = PaddingValues(horizontal = horizontalInset),
            horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.md),
        ) {
            items(homeNavItems) { item ->
                HomeNavTab(
                    label = item,
                    isActive = item == activeTab,
                    onClick = { activeTab = item },
                )
            }
        }
    }
}

@Composable
private fun HomeNavTab(
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = BeOnTheme.colors

    Column(
        modifier = modifier.clickable(onClick = onClick),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = BeOnTheme.spacing.xxs),
            color = if (isActive) colors.accent.primary else colors.text.secondary,
            fontSize = 14.sp,
            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Medium,
        )
        Box(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(
                    color = if (isActive) colors.accent.primary else colors.background.primary,
                    shape = BeOnTheme.shapes.full,
                ),
        )
    }
}
