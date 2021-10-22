package com.sena.quehaypahacer.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sena.quehaypahacer.MainActivity
import com.sena.quehaypahacer.databinding.ActivityLoginBinding
import com.sena.quehaypahacer.ui.register.Register
import org.json.JSONObject
import com.android.volley.VolleyError

import org.json.JSONException

import com.android.volley.RequestQueue
import com.huawei.hmf.tasks.Task
import com.huawei.hms.common.ApiException
import com.huawei.hms.support.account.AccountAuthManager
import com.huawei.hms.support.account.request.AccountAuthParams
import com.huawei.hms.support.account.request.AccountAuthParamsHelper
import com.huawei.hms.support.account.result.AuthAccount
import com.huawei.hms.support.account.service.AccountAuthService


class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val url =  "http://192.168.1.63:1406/"
    private val REQUEST_CODE_SIGN_IN = 1000
    private val TAG = "Account"
    private var mAuthService: AccountAuthService? = null
    private var mAuthParam: AccountAuthParams? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvRegistrarse.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        binding.btnIngresar.setOnClickListener {
            binding.idLoadingPB.visibility =View.VISIBLE
            volleyPost()
        }
        binding.btnHuawei.setOnClickListener {
            silentSignInByHwId()
        }
    }

    fun volleyPost() {
        val postUrl = "https://appsenasoft.herokuapp.com/"
        val requestQueue = Volley.newRequestQueue(this)
        val postData = JSONObject()
        try {
            postData.put("email", binding.etEmail.text)
            postData.put("password", binding.etPassword.text)
            postData.put("type",1)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, postUrl, postData,
            {
                    response ->  binding.idLoadingPB.visibility =View.GONE
                    Log.e("INFO",response.toString())
                val intent = Intent(this, MainActivity::class.java).apply {  }
                startActivity(intent)
                finish()
            }
        ) {
                error ->
            binding.idLoadingPB.visibility =View.GONE
                Log.e("INFO",error.toString())
        }
        requestQueue.add(jsonObjectRequest)
    }

    private fun silentSignInByHwId() {
        mAuthParam = AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
            .setEmail()
            .setIdToken()
            .createParams()
        mAuthService = AccountAuthManager.getService(this, mAuthParam)
        val task: Task<AuthAccount> = mAuthService!!.silentSignIn()
        task.addOnSuccessListener({ authAccount ->
            dealWithResultOfSignIn(authAccount)
            val intent = Intent(this, MainActivity::class.java).apply {  }
            startActivity(intent)
            finish()
        })
        task.addOnFailureListener({ e ->
            if (e is ApiException) {
                val apiException = e
                val signInIntent = mAuthService!!.getSignInIntent()
                startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
            }
        })
    }
    private fun dealWithResultOfSignIn(authAccount: AuthAccount) {
        Log.i(TAG, "idToken:" + authAccount.idToken)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            Log.i(
                TAG,
                "onActivitResult of sigInInIntent, request code: $REQUEST_CODE_SIGN_IN"
            )
            val authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data)
            if (authAccountTask.isSuccessful) {
                // The sign-in is successful, and the authAccount object that contains the HUAWEI ID information is obtained.
                val authAccount = authAccountTask.result
                dealWithResultOfSignIn(authAccount)
                Log.i(
                    TAG,
                    "onActivitResult of sigInInIntent, request code: $REQUEST_CODE_SIGN_IN"
                )
                intent = Intent(this, MainActivity::class.java).apply {  }
                startActivity(intent)
                finish()
            } else {
                // The sign-in fails. Find the failure cause from the status code. For more information, please refer to the "Error Codes" section in the API Reference.
                Log.e(
                    TAG,
                    "sign in failed : " + (authAccountTask.exception as ApiException).statusCode
                )
            }
        }
    }
}
