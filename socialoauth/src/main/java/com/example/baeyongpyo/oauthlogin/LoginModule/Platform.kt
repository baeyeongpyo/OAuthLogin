package com.example.baeyongpyo.oauthlogin.LoginModule

import com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession.KakaoGetTokenInfo
import com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession.KakaoLoginSessionCallBack
import com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession.KakaoLogoutSessionCallback
import com.example.baeyongpyo.oauthlogin.LoginModule.VO.UserInfo

enum class Platform {
    KAKAO;

    fun <R> login(success: () -> Unit, fail : (String)-> Unit) : R =
            when (this){
                KAKAO -> KakaoLoginSessionCallBack(success, fail) as R
            }

    fun <R> logout(logout : () -> Unit): R =
            when (this) {
                KAKAO -> KakaoLogoutSessionCallback(logout) as R
            }

    fun <R> getUserInfo(success: (UserInfo) -> Unit) : R =
            when (this){
                KAKAO -> KakaoGetTokenInfo(success) as R
            }

}