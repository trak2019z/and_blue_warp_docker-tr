package blue_bay.app.utils

import com.mlykotom.valifi.ValiFieldBase
import com.mlykotom.valifi.fields.ValiFieldEmail
import com.mlykotom.valifi.fields.ValiFieldText
import blue_bay.app.R

class ValiFieldPlaceholder : ValiFieldText(){
    override fun addMinLengthValidator(errorResource: Int, minLength: Int): ValiFieldText {
        val error = getString(R.string.error_placeholder, getString(errorResource))
        return super.addMinLengthValidator(error, minLength)
    }

    override fun addVerifyFieldValidator(
        errorResource: Int,
        targetField: ValiFieldBase<String>?
    ): ValiFieldPlaceholder {
        return super.addVerifyFieldValidator(errorResource, targetField) as ValiFieldPlaceholder
    }
}

class ValiFieldEmailPlaceholder : ValiFieldEmail(){
    fun addPlaceholderText(errorResource: Int) : ValiFieldEmailPlaceholder{
        addEmailValidator(getString(R.string.error_placeholder, getString(errorResource)))
        return this
    }
}