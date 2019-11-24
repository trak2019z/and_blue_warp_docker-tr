package blue_bay.app.di.module.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import blue_bay.app.features.register.part_1.RegisterFragmentPart1

@Module
abstract class RegisterFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeRegisterPart1Fragment(): RegisterFragmentPart1

}