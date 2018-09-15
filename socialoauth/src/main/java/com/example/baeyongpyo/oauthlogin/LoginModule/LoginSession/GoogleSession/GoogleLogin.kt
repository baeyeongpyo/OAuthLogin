package com.example.baeyongpyo.oauthlogin.LoginModule.LoginSession.GoogleSession

import android.app.Activity
import com.example.baeyongpyo.oauthlogin.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient

class GoogleLogin(private val activity: Activity) {
    val client by lazy {
        with(activity) {
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(resources.getString(R.string.google_client_web_key))
                    .requestEmail()
                    .build().let { gso ->
                        GoogleApiClient.Builder(this)
                                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                                .build()
                    }

        }
    }
}