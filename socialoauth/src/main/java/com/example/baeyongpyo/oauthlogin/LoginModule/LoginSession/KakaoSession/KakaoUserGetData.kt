package com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession

import android.content.Context
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response


class KakaoUserGetData(private val context : Context) : MeV2ResponseCallback(){
    override fun onSuccess(result: MeV2Response?){ }
    override fun onSessionClosed(errorResult: ErrorResult?){}
}