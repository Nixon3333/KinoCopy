package com.drygin.kinocopy.screens.details.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.drygin.kinocopy.screens.details.presentation.components.FilmDetailContent
import com.drygin.kinocopy.screens.details.presentation.viewmodel.DetailsViewModel

/**
 * Created by Drygin Nikita on 05.07.2025.
 */
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel
) {
    val film by viewModel.film.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        film?.let {
            FilmDetailContent(it)
        }
    }
}