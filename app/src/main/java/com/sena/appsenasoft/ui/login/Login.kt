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




class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val url =  "http://192.168.1.63:1406/"
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
}