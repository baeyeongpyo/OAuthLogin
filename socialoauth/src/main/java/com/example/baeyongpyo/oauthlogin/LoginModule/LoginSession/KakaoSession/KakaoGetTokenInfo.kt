package com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession

import android.content.Context
import com.example.baeyongpyo.oauthlogin.LoginModule.VO.UserInfo
import com.example.baeyongpyo.oauthlogin.toast
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.network.response.AccessTokenInfoResponse
import com.kakao.network.ErrorResult


class KakaoGetTokenInfo(private val sucess : (UserInfo) -> Unit) : ApiResponseCallback<AccessTokenInfoResponse>() {
    override fun onSuccess(result: AccessTokenInfoResponse?){
        result?.run {
            UserInfo(userId.toString()).let { sucess.invoke(it) }
        }

        /*result?.run {
            """
            |$userId
            |$expiresInMillis
            |""".trimMargin().let { context.toast(it) }
        }*/
    }
    override fun onSessionClosed(errorResult: ErrorResult?){}
    override fun onNotSignedUp() {}
}