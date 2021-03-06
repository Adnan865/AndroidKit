package com.mustahsan.androidkit.ktx

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.mustahsan.androidkit.alert.toastShort

@SuppressLint("MissingPermission")
fun Context.vibrateTick() {
    if (PermissionChecker.checkSelfPermission(this, Manifest.permission.VIBRATE) == PermissionChecker.PERMISSION_GRANTED) {
        val vibrator = ContextCompat.getSystemService(this, Vibrator::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
            } else {
                VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
            }
            vibrator?.vibrate(effect)
        } else {
            vibrator?.vibrate(50)
        }
    }else{
        toastShort("VIBRATE missing in Manifest")
    }
}