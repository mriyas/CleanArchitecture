package com.riyas.cleanarchitecture.di

import com.riyas.cleanarchitecture.data.api.ApiService
import com.riyas.cleanarchitecture.data.repository.RecipeRepository
import com.riyas.cleanarchitecture.data.usecases.RecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(
        apiService: ApiService,
        @IoDispatcher  dispatcher: CoroutineDispatcher,
    ): RecipeRepository {
        return RecipeRepository(apiService = apiService, dispatcher = dispatcher)
    }

    @Provides
    fun provideRecipeUseCase(recipeRepository: RecipeRepository): RecipeUseCase {
        return RecipeUseCase(recipeRepository)
    }
}