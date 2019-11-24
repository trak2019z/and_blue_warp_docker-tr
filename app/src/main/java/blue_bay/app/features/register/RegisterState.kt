package blue_bay.app.features.register

import blue_bay.app.data.Resource

data class RegisterState(
    var step: Resource<RegisterOptions> = Resource.Empty
)

sealed class RegisterOptions {
    object RegisterEmail : RegisterOptions()
    object CheckEmail : RegisterOptions()
}