package com.sena.quehaypahacer

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import com.sena.quehaypahacer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var TAG:String = "ACTIVITY"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getToken()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,R.id.navigation_search,R.id.navigation_login, R.id.viewObjectActivity2
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        supportFragmentManager
    }
    private fun getToken() {
        // Create a thread.
        object : Thread() {
            override fun run() {
                try {
                    // Obtain the app ID from the agconnect-service.json file.
                    val appId = AGConnectServicesConfig.fromContext(this@MainActivity)
                        .getString("client/app_id")
                    // Set tokenScope to HCM.
                    val tokenScope = "HCM"
                    val token =
                        HmsInstanceId.getInstance(this@MainActivity).getToken(appId, tokenScope)
                    Log.e(TAG, "get token: $token")

                    // Check whether the token is empty.
                    if (!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token)
                    }
                } catch (e: ApiException) {
                    Log.e(TAG, "get token failed, $e")
                }
            }
        }.start()
    }
    private fun sendRegTokenToServer(token: String) {
        Log.i(TAG, "sending token to server. token:$token")
    }
}