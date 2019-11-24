package blue_bay.app.features.main

import blue_bay.app.data.Resource

data class MainState(
    var step: Resource<MainOptions> = Resource.Empty
)

sealed class MainOptions {
}