package com.drygin.kinocopy.screens.details.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.drygin.kinocopy.R
import com.drygin.kinocopy.common.theme.CustomColors
import com.drygin.kinocopy.common.theme.Dimens

/**
 * Created by Drygin Nikita on 05.07.2025.
 */
@Composable
fun PosterImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    widthFraction: Float = 0.5f,
    aspectRatio: Float = Dimens.PosterAspectRatio,
    cornerRadius: Dp = Dimens.PosterCornerRadius,
    placeholderIcon: Int = R.drawable.ic_placeholder
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .size(Size.ORIGINAL)
            .build()
    )

    val isLoadingOrFailed = painter.state is AsyncImagePainter.State.Loading ||
            painter.state is AsyncImagePainter.State.Error ||
            imageUrl.isNullOrBlank()

    Box(
        modifier = modifier
            .fillMaxWidth(widthFraction)
            .aspectRatio(aspectRatio)
            .clip(RoundedCornerShape(cornerRadius))
            .background(CustomColors.BgPlaceholder),
        contentAlignment = Alignment.Center
    ) {
        if (isLoadingOrFailed) {
            Icon(
                painter = painterResource(placeholderIcon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(Dimens.PosterIconSize)
            )
        } else {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}