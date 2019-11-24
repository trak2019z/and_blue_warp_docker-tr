package blue_bay.app.di.module.fragment

import blue_bay.app.features.main.details.ArticleDetailsFragment
import blue_bay.app.features.main.filter.ArticlesFilterFragment
import blue_bay.app.features.main.list.ArticlesListFragment
import blue_bay.app.features.register.part_1.RegisterFragmentPart1
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticlesFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeArticlesListFragment(): ArticlesListFragment

    @ContributesAndroidInjector
    abstract fun contributeArticlesDetailsFragment(): ArticleDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeArticlesFilterFragment(): ArticlesFilterFragment

}