package blue_bay.app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import blue_bay.app.App
import blue_bay.app.di.module.ActivityModule
import blue_bay.app.di.module.ApiModule
import blue_bay.app.di.module.AppModule
import blue_bay.app.di.module.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ApiModule::class,
    ActivityModule::class, DataModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}