package com.example.e_commerceapp.Hilt

import com.example.e_commerceapp.repo.CartPageDAORepository
import com.example.e_commerceapp.repo.MainPageDAORepository
import com.example.e_commerceapp.repo.ProductPageDAORepository
import com.example.e_commerceapp.repo.ProfileDAORepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideProductPageDAORepository():ProductPageDAORepository{
        return ProductPageDAORepository()
    }
    @Provides
    @Singleton
    fun provideProfileDAORepository():ProfileDAORepository{
        return ProfileDAORepository()
    }

    @Provides
    @Singleton
    fun provideCartPageDAORepository():CartPageDAORepository{
        return CartPageDAORepository()
    }




    @Singleton
    @Provides
    fun provideMainPageDAORepository():MainPageDAORepository{
        return MainPageDAORepository()
    }
}