package com.example.mainapplication

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.baeyongpyo.oauthlogin.LoginFragment
import com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.GoogleSession.GoogleLogin
import com.example.baeyongpyo.oauthlogin.LoginModule.Platform
import com.example.baeyongpyo.oauthlogin.LoginModule.VO.UserInfo
import com.example.baeyongpyo.oauthlogin.fireBaseInstance
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.auth.AuthService
import com.kakao.usermgmt.UserManagement
import kotlinx.android.synthetic.main.activity_main.*

// BkOnXb3u5Al6+Om0tW+cOmaINmU=

class MainActivity : AppCompatActivity() {
    private val success: (UserInfo) -> Unit = { toast(it.user) }
    private val logout: () -> Unit = { toast("Logout") }
    private val googleLogin by lazy { GoogleLogin(this).client }
    private val googleInstance by lazy { fireBaseInstance() }
    private val kakaoInstance by lazy { UserManagement.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        googleLogin.connect()

        val googleLogout: (View) -> Unit = {
            googleInstance.signOut()
            toast("Logout Click")
        }
        val googleUser : (View)->Unit = {googleInstance.currentUser?.let { toast(it.displayName.toString()) }}

        val kakaoLogout: (View) -> Unit = { kakaoInstance.requestLogout(Platform.KAKAO.logout(logout)) }
        val kakaoUser: (View) -> Unit = { AuthService.getInstance().requestAccessTokenInfo(Platform.KAKAO.getUserInfo(success)) }

        replace(R.id.login_fragment, LoginFragment().apply {
            loginSuccess = { toast("Success"); Log.i("LOGIN", "success") }
            loginFail = { toast(it); Log.i("LOGIN", "fail") }
        })

        firebase_logout_button.setOnClickListener(googleLogout)
        firebase_get_user_info_button.setOnClickListener(googleUser)
        logout_button.setOnClickListener(kakaoLogout)
        get_user_info_button.setOnClickListener(kakaoUser)


    }
}
