package blue_bay.app.features.menu.menu

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SignInMenuAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int) = SignInMenuTutorialFragment.getFragment(position)

    override fun getCount() = 4

}