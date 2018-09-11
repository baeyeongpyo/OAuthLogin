package com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession

import com.kakao.auth.ISessionCallback
import com.kakao.util.exception.KakaoException

class KakaoLoginSessionCallBack(
        private val success: () -> Unit,
        private val fail : (String) -> Unit = {}) : ISessionCallback {
    override fun onSessionOpenFailed(exception: KakaoException?){
        fail.invoke(exception?.message?:"null")
    }
    override fun onSessionOpened(){
        success.invoke()
    }
}