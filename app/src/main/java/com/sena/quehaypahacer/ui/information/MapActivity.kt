package com.sena.quehaypahacer.ui.information

import android.Manifest.permission
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapsInitializer
import com.huawei.hms.maps.OnMapReadyCallback
import com.sena.quehaypahacer.GPS.GPS
import com.sena.quehaypahacer.R
import kotlinx.android.synthetic.main.fragment_contact.*
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.model.LatLng

import com.huawei.hms.maps.CameraUpdateFactory

import android.Manifest.permission.ACCESS_WIFI_STATE

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.util.Log
import com.huawei.hms.maps.map
import kotlinx.android.synthetic.main.activity_map.*


class MapActivity : AppCompatActivity(), OnMapReadyCallback,GPS.OnGPSEventListener{
    private var hMap:HuaweiMap? = null
    private val KEY="CwEAAAAAOHdnKQCNEet8Fu/QKNwBd9U2BaaVtO8nRhP8wKFGzVfYhlhbUA+DwhoaPvQIu/Sw7oZNmC2EM6Is9c2ndb3GoBQ2y6g="
    private val MAPVIEW_BUNDLE_KEY= "MapViewBundleKey"
    lateinit var gps:GPS
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ThemeNotBar)
        super.onCreate(savedInstanceState)
        MapsInitializer.setApiKey(KEY)
        setContentView(R.layout.activity_map)
        initHuaweiMap(savedInstanceState)
        if(checkLocationPermissions()){
            setupGPS()
        }else{
            requestLocationPermissions()
        }
    }

    private fun requestLocationPermissions() {
        if(ContextCompat.checkSelfPermission(this@MapActivity,android.Manifest.permission.ACCESS_FINE_LOCATION)
            !== PackageManager.PERMISSION_GRANTED
        ){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this@MapActivity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this@MapActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            }else{
                ActivityCompat.requestPermissions(this@MapActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if((ContextCompat.checkSelfPermission(this@MapActivity,android.Manifest.permission.ACCESS_FINE_LOCATION)==
                                PackageManager.PERMISSION_GRANTED)){
                        Toast.makeText(this,"PERMISITION GRANDET",Toast.LENGTH_LONG).show()
                    }
                }else {
                    Toast.makeText(this, "PERMISITION DENIED", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
    private fun setupGPS() {
        gps=GPS(this)
        gps.gpsEventListener=this
        gps.starLocationsRequest()

    }



    private fun checkLocationPermissions(): Boolean {
        val location:Int= ContextCompat.checkSelfPermission(this@MapActivity,android.Manifest.permission.ACCESS_FINE_LOCATION)or
                ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
        val backgroundLoaction = if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.Q){
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }else{
            PackageManager.PERMISSION_GRANTED
        }
        return location==PackageManager.PERMISSION_GRANTED&&backgroundLoaction==PackageManager.PERMISSION_GRANTED
    }


    fun initHuaweiMap(savedInstanceStale:Bundle?){
        var mapViewBundle:Bundle? = null
        if(savedInstanceStale!=null){
            mapViewBundle = savedInstanceStale.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mapview_mapview?.apply {
            onCreate(mapViewBundle)
            getMapAsync(this@MapActivity)
        }
    }
    override fun onMapReady(map: HuaweiMap) {
        if(map!=null){
            hMap = map
            hMap?.isMyLocationEnabled = true
            hMap?.uiSettings?.isMyLocationButtonEnabled= true
            val location=LatLng(48.893478, 2.334595)
            val update=CameraUpdateFactory.newLatLngZoom(location,10.0f)
            hMap?.clear()
            hMap?.animateCamera(update)

            //hMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.893478, 2.334595), 10f))

        }
    }

    override fun onStart() {
        mapview_mapview?.onStart()
        super.onStart()
    }

    override fun onStop() {
        mapview_mapview?.onStop()
        super.onStop()
    }

    override fun onPause() {
        mapview_mapview?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mapview_mapview?.onResume()
        super.onResume()
    }
    override fun onDestroy() {
        mapview_mapview?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapview_mapview?.onLowMemory()
        super.onLowMemory()
    }

    override fun onResolutionRequited(e: Exception) {
        Log.e("ERROR LOCATION",e.toString())
    }

    override fun onLastKnownLocation(lat: Double, lon: Double) {
        Log.e("DATA","$lat:$lon")
    }
}