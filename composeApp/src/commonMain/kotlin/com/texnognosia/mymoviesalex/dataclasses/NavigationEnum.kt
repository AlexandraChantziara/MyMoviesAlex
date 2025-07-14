package com.texnognosia.mymoviesalex.dataclasses

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationEnum(val icon: ImageVector, val route: String, val title : String) {
    MOVIES(Icons.Outlined.Movie, "movies","Ταινίες"),
    FAVORITES(Icons.Default.Favorite, "favorites","Αγαπημένα")
}