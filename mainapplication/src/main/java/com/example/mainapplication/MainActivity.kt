package com.example.mainapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.baeyongpyo.oauthlogin.LoginFragment
import com.example.baeyongpyo.oauthlogin.LoginModule.Platform
import com.example.baeyongpyo.oauthlogin.LoginModule.VO.UserInfo
import com.kakao.auth.AuthService
import com.kakao.usermgmt.UserManagement
import kotlinx.android.synthetic.main.activity_main.*

// BkOnXb3u5Al6+Om0tW+cOmaINmU=

class MainActivity : AppCompatActivity() {
    private val success : (UserInfo)->Unit ={ toast("${it.user}")}
    private val logout : ()->Unit ={ toast("Logout")}
    private val kakaoInstance by lazy { UserManagement.getInstance() }
    private val kakaoLogout : (View)-> Unit = { kakaoInstance.requestLogout(Platform.KAKAO.logout(logout)) }
    private val kakaoUser : (View)-> Unit = { AuthService.getInstance().requestAccessTokenInfo(Platform.KAKAO.getUserInfo(success)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace(R.id.login_fragment, LoginFragment().apply {
            loginSuccess  = {toast("Success") ; Log.i("LOGIN", "success")}
            loginFail = {toast(it) ; Log.i("LOGIN", "fail")}
        })

        logout_button.setOnClickListener(kakaoLogout)
        get_user_info_button.setOnClickListener(kakaoUser)


    }
}
