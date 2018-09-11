package com.example.baeyongpyo.oauthlogin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baeyongpyo.oauthlogin.LoginModule.LoginAdapter.KakaoSDKAdpater
import com.example.baeyongpyo.oauthlogin.LoginModule.Platform
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.kakao.auth.ISessionCallback
import com.kakao.auth.KakaoSDK
import com.kakao.auth.Session
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private val fragCtx by lazy { activity?.applicationContext!! }

    var loginSuccess : ()->Unit ={}
    var loginFail : (String) -> Unit = {}

    private val kakaoLoginCallback by lazy { Platform.KAKAO.login(loginSuccess, loginFail) as ISessionCallback }
    private val KakaoSession by lazy {
        Session.getCurrentSession().apply {
            addCallback(kakaoLoginCallback)
            checkAndImplicitOpen()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KakaoSDK.init(KakaoSDKAdpater(fragCtx))
        KakaoSession
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("web client id")
                .requestEmail()
                .build()

        val mGoogleApiClient = GoogleApiClient.Builder(fragCtx)
//                .enableAutoManage(this@LoginFragment as FragmentActivity) { }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        google_login_button.setOnClickListener {
            Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient).let {
                startActivityForResult(it, SIGN_IN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (KakaoSession.handleActivityResult(requestCode, resultCode, data)) return
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        KakaoSession.removeCallback(kakaoLoginCallback)
    }
}