package com.sena.appsenasoft.ui.viewObject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sena.appsenasoft.R
import com.sena.appsenasoft.ui.information.ContactFragment
import com.sena.appsenasoft.ui.information.InformationObjectFragment

class ViewObjectActivity : AppCompatActivity() {
    lateinit var buttonService:Button
    lateinit var buttonContact:Button
    var fragmentInformation = InformationObjectFragment()
    var fragmentContact = ContactFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sena.appsenasoft.R.layout.activity_view_object)
        buttonService = findViewById(R.id.buttonServices)
        buttonContact = findViewById(R.id.buttonContant)
    }
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val trasaction = supportFragmentManager.beginTransaction()
            trasaction.replace(R.id.fragments_replace,fragment)
            trasaction.commit()
        }

    }

    fun onClick(view:View) {
        when (view.getId()) {
             R.id.buttonServices ->replaceFragment(fragmentInformation)
            R.id.buttonContant->replaceFragment(fragmentContact)
        }
    }
}