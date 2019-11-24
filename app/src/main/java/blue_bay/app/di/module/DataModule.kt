package blue_bay.app.di.module

import blue_bay.app.data.repository.*
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    internal abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    internal abstract fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

    @Binds
    internal abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}