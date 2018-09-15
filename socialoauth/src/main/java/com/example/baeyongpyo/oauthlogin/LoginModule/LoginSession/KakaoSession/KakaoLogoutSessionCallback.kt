package com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession

import com.kakao.usermgmt.callback.LogoutResponseCallback

class KakaoLogoutSessionCallback(private val logout : () -> Unit) : LogoutResponseCallback() {
    override fun onCompleteLogout(){
        logout.invoke()
    }
}