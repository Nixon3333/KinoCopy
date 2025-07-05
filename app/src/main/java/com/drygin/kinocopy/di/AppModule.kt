package com.drygin.kinocopy.di

import androidx.room.Room
import com.drygin.kinocopy.common.data.local.AppDatabase
import com.drygin.kinocopy.common.data.remote.api.FilmApi
import com.drygin.kinocopy.common.data.repository.FilmRepositoryImpl
import com.drygin.kinocopy.common.network.NetworkConstants
import com.drygin.kinocopy.features.home.domain.repository.FilmRepository
import com.drygin.kinocopy.features.home.presentation.viewmodel.MainFragmentViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .build()
    }

    single<FilmApi> {
        get<Retrofit>().create(FilmApi::class.java)
    }

    single { get<AppDatabase>().filmDao() }
    single { get<AppDatabase>().genreDao() }

    singleOf(::FilmRepositoryImpl) bind FilmRepository::class

    viewModelOf(::MainFragmentViewModel)
}