package com.example.baeyongpyo.oauthlogin

import android.content.Context
import android.content.pm.PackageManager
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.widget.Toast
import java.security.MessageDigest

fun Context.getAppKeyHash(): String {
    packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)?.run {
        for ( signature in signatures){
            MessageDigest.getInstance("SHA").apply { update(signature.toByteArray()) }
                    .let { return String(Base64.encode(it.digest(), 0)) }
        }
    }
    return "not Find"
}

internal fun AppCompatActivity.replace(@IdRes frameId : Int, fragment : Fragment, tag : String? = null){
    supportFragmentManager
            .beginTransaction()
            .replace(frameId, fragment, tag)
            .commit()
}
internal fun Context.toast(msg : String, time : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, msg, time).show()
}