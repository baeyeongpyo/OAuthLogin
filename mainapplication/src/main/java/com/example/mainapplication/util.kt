package com.example.mainapplication

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

internal fun AppCompatActivity.replace(@IdRes frameId : Int, fragment : Fragment, tag : String? = null){
    supportFragmentManager
            .beginTransaction()
            .replace(frameId, fragment, tag)
            .commit()
}
internal fun Context.toast(msg : String, time : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, msg, time).show()
}