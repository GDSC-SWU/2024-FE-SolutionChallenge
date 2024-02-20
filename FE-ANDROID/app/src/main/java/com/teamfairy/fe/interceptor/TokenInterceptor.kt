package com.teamfairy.fe.interceptor

import android.app.Application
import com.teamfairy.BuildConfig.FE_BASE_URL
import com.teamfairy.data.datasource.SharedPreferenceDataSource
import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.response.ResponseRefreshTokenDto
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import javax.inject.Inject

class TokenInterceptor
@Inject
constructor(
    private val userInfoDataSource: SharedPreferenceDataSource,
    private val json: Json,
    private val context: Application,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val accessToken = userInfoDataSource.accessToken
        val refreshToken = userInfoDataSource.refreshToken

        // 기존 request
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $accessToken").build()
        val response = chain.proceed(request)
        Timber.tag("interceptor").d(accessToken)

        when (response.code) {
            // 기존 request가 401 : access token 이상
            401 -> {
                Timber.d("토큰 만료됨!!!")

                response.close()

                val requestBodyJson = "{\"accessToken\": \"$accessToken\"}"

                val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), requestBodyJson)

                // access token 재발급 request
                val accessTokenRequest =
                    chain.request().newBuilder()
                        .url("${FE_BASE_URL}/auth/refresh")
                        .post(requestBody)
                        .addHeader("Authorization", "Bearer $accessToken")
                        .build()

                val refreshAccessTokenResponse = chain.proceed(accessTokenRequest)

                val refreshAccessToken =
                    json.decodeFromString<BaseResponse<ResponseRefreshTokenDto>>(
                        refreshAccessTokenResponse.body?.string()
                            ?: throw IllegalStateException("\"refreshTokenResponse is null $refreshAccessTokenResponse\""),
                    )

                when (refreshAccessToken.status) {
                    // access token 재발급 성공
                    200 -> {
                        userInfoDataSource.accessToken = refreshAccessToken.data?.accessToken

                        refreshAccessTokenResponse.close()

                        val newRequest =
                            chain.request().newBuilder().addHeader(
                                "Authorization",
                                "Bearer ${userInfoDataSource.accessToken}",
                            ).build()

                        return chain.proceed(newRequest)
                    }

                    // access token, refresh token 둘 다 만료되면
                    else -> {
                        with(context) {
                            userInfoDataSource.clear()
                        }
                    }
                }
            }
        }
        return response
    }
}
