package com.sena.appsenasoft.ui.service

import android.util.Log
import com.huawei.hms.push.HmsMessageService
import android.text.TextUtils

import android.os.Bundle
import com.huawei.hms.common.ApiException

import com.huawei.hms.aaid.HmsInstanceId

import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.push.RemoteMessage

class ServiceHMS:HmsMessageService() {
    private  var TAG:String = "SERVICE_HMS"
    override fun onNewToken(token: String) {
        Log.i(TAG, "received refresh token:$token")
        if (!TextUtils.isEmpty(token)) {
            refreshedTokenToServer(token)
        }
    }

    // If the version of the Push SDK you integrated is 5.0.4.302 or later, you also need to override the method.
    override fun onNewToken(token: String, bundle: Bundle?) {
        Log.i(TAG, "have received refresh token $token")
        if (!TextUtils.isEmpty(token)) {
            refreshedTokenToServer(token)
        }
    }

    private fun refreshedTokenToServer(token: String) {
        Log.i(TAG, "sending token to server. token:$token")
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        message?.let {
            val map = it.dataOfMap
            Log.i(TAG,map["messager"]?:"NULL");
        }
    }



}
