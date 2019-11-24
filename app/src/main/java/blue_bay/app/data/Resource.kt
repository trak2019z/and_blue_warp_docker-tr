package blue_bay.app.data

import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import blue_bay.app.BuildConfig
import blue_bay.app.data.api.ErrorApi
import blue_bay.app.data.api.ErrorResponse
import java.io.IOException

sealed class Resource<out T> {

    abstract fun <R> map(f: (T) -> R): Resource<R>

    class Success<out T>(val data: T) : Resource<T>() {
        override fun <R> map(f: (T) -> R): Resource<R> = Success(f(data))
    }

    class Error(val errorResponse: ErrorResponse) : Resource<Nothing>() {
        constructor(t: Throwable) : this(errorResponse(t))

        override fun <R> map(f: (Nothing) -> R): Resource<R> = this
    }


    object Loading : Resource<Nothing>() {

        override fun <R> map(f: (Nothing) -> R): Resource<R> = this
    }

    object Empty : Resource<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): Resource<R> = this
    }
}

fun <T> Resource<T>.orElse(defaultValue: T): T = (this as? Resource.Success)?.data ?: defaultValue

fun errorResponse(throwable: Throwable): ErrorResponse {
    if (throwable.message.toString().startsWith("Unable to resolve host"))
        return ErrorResponse(message="No internet connection")
    try {
        val responseBodyError = (throwable as HttpException).response().errorBody()
        val json = JSONObject(String(responseBodyError!!.bytes()))
        val gson = Gson()
        return gson.fromJson<ErrorResponse>(json.toString(), ErrorResponse::class.java)
    } catch (e: JSONException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: ClassCastException) {
        return ErrorResponse(
            message =  if (BuildConfig.DEBUG) throwable.cause?.cause?.cause?.message
                        ?: e.localizedMessage
        else "Unknown error")

    }
    return ErrorResponse(message =  "Unknown error")
}