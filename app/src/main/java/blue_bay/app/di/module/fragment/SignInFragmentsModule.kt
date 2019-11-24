package blue_bay.app.di.module.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import blue_bay.app.features.menu.login.LoginFragment
import blue_bay.app.features.menu.reset_password.ResetPasswordFragment
import blue_bay.app.features.menu.menu.SignInMenuFragment

@Module
abstract class SignInFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeResetPasswordFragment(): ResetPasswordFragment

    @ContributesAndroidInjector
    abstract fun contributeSignInMenuFragment() : SignInMenuFragment
}