package blue_bay.app.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val sharedPref: SharedPreferences) : UserRepository {

    override fun setToken(token: String) =
        sharedPref.edit { putString(TOKEN, token) }

    override fun getToken(): String =
        sharedPref.getString(TOKEN, "")

    companion object {
        const val TOKEN = "TOKEN"
    }
}