package com.example.baeyongpyo.oauthlogin.LoginModule.VO

import android.app.Activity
import android.util.Log
import com.example.baeyongpyo.oauthlogin.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


fun Activity.Login(fireBaseInstance: FirebaseAuth, tokenToString: String, successFuntion: () -> Unit) {
    GoogleAuthProvider.getCredential(tokenToString, null).let { fireBaseInstance.signInWithCredential(it) }
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    successFuntion.invoke()
                } else {
                    this.toast("Fail FireBase")
                    Log.e("fireBaseErrorMessage", it.exception.toString())
                }
            }
}