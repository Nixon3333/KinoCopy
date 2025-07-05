package com.drygin.kinocopy.screens.details.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import com.drygin.kinocopy.R
import com.drygin.kinocopy.common.theme.Dimens
import com.drygin.kinocopy.common.theme.FilmDescriptionStyle
import com.drygin.kinocopy.common.theme.FilmMeta
import com.drygin.kinocopy.common.theme.RatingLabel
import com.drygin.kinocopy.common.theme.RatingValue
import com.drygin.kinocopy.common.domain.model.Film

/**
 * Created by Drygin Nikita on 05.07.2025.
 */
@Composable
fun FilmDetailContent(film: Film) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.SpacingLarge)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            PosterImage(
                imageUrl = film.imageUrl,
                modifier = Modifier.padding(Dimens.SpacingXLarge)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

        Text(
            text = film.localizedName ?: "",
            style = MaterialTheme.typography.titleLarge
        )

        val genresText = if (film.genres.isNotEmpty()) {
            "${film.genres.joinToString(", ") { it.lowercase() }}, " +
                    "${film.year} ${stringResource(R.string.details_year_text)}"
        } else {
            "${film.year} ${stringResource(R.string.details_year_text)}"
        }

        Text(
            text = genresText,
            style = FilmMeta
        )

        Spacer(modifier = Modifier.height(Dimens.Spacing10))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = film.rating.toString(),
                style = RatingValue,
                modifier = Modifier.alignBy(LastBaseline)
            )
            Spacer(modifier = Modifier.width(Dimens.SpacingMedium))
            Text(
                text = stringResource(R.string.rating_label_placeholder),
                style = RatingLabel,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.Spacing14))

        Text(
            text = film.description ?: "",
            style = FilmDescriptionStyle
        )
    }
}