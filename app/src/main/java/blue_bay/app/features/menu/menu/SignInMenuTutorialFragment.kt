package blue_bay.app.features.menu.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import blue_bay.app.R
import blue_bay.app.databinding.FragmentTutorialBinding
import blue_bay.app.utils.AutoClearedValue

class SignInMenuTutorialFragment : Fragment() {

    private lateinit var mBinding: AutoClearedValue<FragmentTutorialBinding>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = AutoClearedValue(
            this,
            FragmentTutorialBinding.inflate(inflater, container, false)
        )

        mBinding.get().tvTutorialTitle.setText(
            when (arguments?.getInt(POSITION_EXTRA)) {
                0 -> {
                    R.string.tutorial_title_1
                }
                1 -> {
                    R.string.tutorial_title_2
                }
                2 -> {
                    R.string.tutorial_title_3
                }
                else -> {
                    R.string.tutorial_title_4
                }
            }
        )
        return mBinding.get().root
    }

    companion object {
        const val POSITION_EXTRA = "POSITION"

        fun getFragment(position: Int): SignInMenuTutorialFragment {
            val fragment = SignInMenuTutorialFragment()
            val bundle = bundleOf(POSITION_EXTRA to position)
            fragment.arguments = bundle
            return fragment
        }
    }
}