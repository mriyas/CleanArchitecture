package com.riyas.cleanarchitecture.di

import com.riyas.cleanarchitecture.data.remote.ApiService
import com.riyas.cleanarchitecture.data.remote.RecipeRemoteSource
import com.riyas.cleanarchitecture.domain.repositories.RecipeRepository
import com.riyas.cleanarchitecture.data.repositories.RecipeRepositoryImpl
import com.riyas.cleanarchitecture.data.utils.NetworkUtils
import com.riyas.cleanarchitecture.domain.usecases.RecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideRecipeRepository(
        recipeRemoteSource: RecipeRemoteSource,
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeRemoteSource = recipeRemoteSource)
    }

    @Provides
    fun provideRecipeRemoteSource(
        apiService: ApiService,
        networkUtils: NetworkUtils,
        @IoDispatcher  dispatcher: CoroutineDispatcher,
    ): RecipeRemoteSource {
        return RecipeRemoteSource(
            apiService = apiService,
            networkUtils = networkUtils,
            dispatcher = dispatcher,
        )
    }

    @Provides
    fun provideRecipeUseCase(recipeRepository: RecipeRepository): RecipeUseCase {
        return RecipeUseCase(recipeRepository)
    }
}