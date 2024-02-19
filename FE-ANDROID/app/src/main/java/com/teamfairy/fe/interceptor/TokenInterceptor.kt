package com.teamfairy.fe.interceptor

import android.app.Application
import com.teamfairy.BuildConfig.FE_BASE_URL
import com.teamfairy.data.datasource.SharedPreferenceDataSource
import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.response.ResponseRefreshTokenDto
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
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
                // access token 재발급 request
                val accessTokenRequest =
                    chain.request().newBuilder()
                        .url("${FE_BASE_URL}/auth/refresh")
                        .get()
                        .addHeader("Authorization", "Bearer $accessToken")
                        .addHeader("Refresh", "Bearer $refreshToken")
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
