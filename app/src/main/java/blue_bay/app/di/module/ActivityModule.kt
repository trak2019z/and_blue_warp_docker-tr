package blue_bay.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import blue_bay.app.di.ActivityScope
import blue_bay.app.di.module.fragment.ArticlesFragmentModule
import blue_bay.app.di.module.fragment.RegisterFragmentsModule
import blue_bay.app.di.module.fragment.SignInFragmentsModule
import blue_bay.app.features.main.MainActivity
import blue_bay.app.features.menu.SignInMenuActivity
import blue_bay.app.features.register.RegisterActivity

@Module
abstract class ActivityModule{
    @ActivityScope
    @ContributesAndroidInjector(modules = [(SignInFragmentsModule::class)])
    abstract fun contributeSignInMenuActivity() : SignInMenuActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(RegisterFragmentsModule::class)])
    abstract fun contributeRegisterActivity() : RegisterActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(ArticlesFragmentModule::class)])
    abstract fun contributeMainActivity() : MainActivity
}