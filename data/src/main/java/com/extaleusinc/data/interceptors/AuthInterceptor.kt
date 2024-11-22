package com.extaleusinc.data.interceptors

//import com.extaleusinc.data.utils.RequiresAppJson
import com.extaleusinc.data.persistent.UserDataHolder
import com.extaleusinc.data.utils.RequiresToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())

        containedOnInvocation(invocation).forEach { annotation ->
            request = handleAnnotation(annotation, request)
        }

        return chain.proceed(request)
    }

    private fun containedOnInvocation(invocation: Invocation): Set<Annotation> {
        return invocation.method().annotations.toSet()
    }

    private fun handleAnnotation(
        annotation: Annotation,
        request: Request,
    ): Request {
        return when (annotation) {
            is RequiresToken -> addAuthToken(request)
//            is RequiresAppJson -> addAppJson(request)
            else -> request
        }
    }

    private fun addAuthToken(request: Request): Request {
        return request.newBuilder()
            .addHeader("Authorization", "Bearer ${UserDataHolder.getToken()}")
//            .addHeader("Accept", "application/json")
            .build()
    }

//    private fun addAppJson(request: Request): Request {
//        return request.newBuilder()
//            .addHeader("Accept", "application/json")
//            .build()
//    }
}