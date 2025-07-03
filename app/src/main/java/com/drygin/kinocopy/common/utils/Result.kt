package com.drygin.kinocopy.common.utils

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()
}