package com.example.baeyongpyo.oauthlogin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baeyongpyo.oauthlogin.LoginModule.LoginAdapter.KakaoSDKAdpater
import com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.GoogleSession.GoogleLogin
import com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.KakaoSession.KakaoUserGetData
import com.example.baeyongpyo.oauthlogin.LoginModule.Platform
import com.example.baeyongpyo.oauthlogin.LoginModule.VO.Login
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.kakao.auth.AuthService
import com.kakao.auth.ISessionCallback
import com.kakao.auth.KakaoSDK
import com.kakao.auth.Session
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private val fragCtx by lazy { activity?.applicationContext!! }
    private val mAuth by lazy { FirebaseAuth.getInstance() }
    private val googleLogin by lazy { GoogleLogin(activity!!).client }
    var loginSuccess: () -> Unit = {
        AuthService.getInstance().requestAccessTokenInfo(Platform.KAKAO.getUserInfo{
            fireBaseAuth(it.toString())
        })
    }
    var loginFail: (String) -> Unit = {}

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
        kakao_login_button.setOnClickListener {
            KakaoSession
        }
        google_login_button.setOnClickListener {
            Auth.GoogleSignInApi.getSignInIntent(googleLogin).let {
                startActivityForResult(it, SIGN_IN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (KakaoSession.handleActivityResult(requestCode, resultCode, data)) return
        if (requestCode == SIGN_IN) {
            GoogleSignIn.getSignedInAccountFromIntent(data).run {
                getResult(ApiException::class.java).run { fireBaseAuth(idToken.toString()) }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun fireBaseAuth(tokenToString: String): Unit =
            activity!!.Login(mAuth, tokenToString) {
                mAuth.currentUser.let { fragCtx.toast("success") }
            }


    override fun onDestroy() {
        super.onDestroy()
        KakaoSession.removeCallback(kakaoLoginCallback)
    }
}

