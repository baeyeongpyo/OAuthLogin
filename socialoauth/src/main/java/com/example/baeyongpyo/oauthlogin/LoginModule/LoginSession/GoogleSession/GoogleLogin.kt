package com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.GoogleSession

import android.support.v4.app.FragmentActivity
import com.example.baeyongpyo.oauthlogin.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient

class GoogleLogin(private val activity: FragmentActivity) {
    val client = with(activity) {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(resources.getString(R.string.google_client_web_key))
                .requestEmail()
                .build().let { gso ->
                    GoogleApiClient.Builder(this)
                            .enableAutoManage(this) {}
                            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                            .build()
                }

    }
}