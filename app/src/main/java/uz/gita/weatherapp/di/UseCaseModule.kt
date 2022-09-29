package uz.gita.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.weatherapp.useCase.GeoUseCase
import uz.gita.weatherapp.useCase.GraphUseCase
import uz.gita.weatherapp.useCase.WeatherUseCase
import uz.gita.weatherapp.useCase.impl.GeoUseCaseImpl
import uz.gita.weatherapp.useCase.impl.GraphUseCaseImpl
import uz.gita.weatherapp.useCase.impl.WeatherUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun geoUseCaseBind(impl: GeoUseCaseImpl): GeoUseCase

    @Binds
    fun graphUseCaseBind(impl: GraphUseCaseImpl): GraphUseCase

    @Binds
    fun weatherUseCaseBind(impl: WeatherUseCaseImpl): WeatherUseCase
}