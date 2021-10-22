package com.sena.quehaypahacer.GPS

import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import com.huawei.hms.api.Api
import com.huawei.hms.common.ApiException
import com.huawei.hms.location.LocationCallback
import com.huawei.hms.location.*

public class GPS(val context: Context):LocationCallback() {
    private var TAG="GPS TRACKER"
    var isStarted:Boolean=false
    var gpsEventListener:OnGPSEventListener? = null
    private val fusedLocationProviderClient:FusedLocationProviderClient=
        LocationServices.getFusedLocationProviderClient(context)

    fun starLocationsRequest(interval:Long=1000){
        val settingsClient:SettingsClient = LocationServices.getSettingsClient(context)
        val mlocationRequest = LocationRequest()
        mlocationRequest.interval = interval
        mlocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mlocationRequest)
        var locationSettingsRequest = builder.build()
        settingsClient.checkLocationSettings(locationSettingsRequest)
            .addOnSuccessListener{
                Log.i(TAG,"check location setting success")
                fusedLocationProviderClient.requestLocationUpdates(
                    mlocationRequest,
                    this,
                    Looper.getMainLooper()
                ).addOnSuccessListener{
                    Log.i(TAG,"requestLocationUpdatesWithCallBack onSucces")
                    isStarted=true
                }
                    .addOnFailureListener{ e->
                        Log.e(TAG,"requestLocationUpdatesWithCallBack onFailure: "+e.message)

                    }
            }
            .addOnFailureListener{e->
                Log.e(TAG,"checkLocationSetting onFailure: "+e.message)
                val apiException:ApiException = e as ApiException
                when(apiException.statusCode){
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->{
                        Log.e(TAG,"resolution requited")
                        gpsEventListener?.onResolutionRequited(e)
                    }
                }
            }
    }
    fun removeLocationUpdatesWithCallBack(){
        try {
            fusedLocationProviderClient.removeLocationUpdates(this)
                .addOnSuccessListener{
                    isStarted=false
                    Log.i(TAG,"removeLocationUpdatesWithCallBack onSucces")
                }
                .addOnFailureListener{e->
                    Log.e(TAG,"removeLocationUpdatesWithCallBack onFailure "+e.message)
                }
        }catch (e:Exception){
            Log.e(TAG,"removeLocationUpdatesWithCallBack : "+e.message)
        }
    }
    interface OnGPSEventListener{
        fun onResolutionRequited(e:Exception)
        fun onLastKnownLocation(lat:Double,lon:Double)
    }

    override fun onLocationResult(localtionResult: LocationResult?) {
        if (localtionResult!=null){
            val locations:List<Location> = localtionResult.locations
            val lastLocation=localtionResult.lastLocation
            gpsEventListener?.onLastKnownLocation(lastLocation.latitude,lastLocation.longitude)
            if(locations.isNotEmpty()){
                for (location in locations){
                    Log.e(TAG,
                        "onLocationResult location longitude.latitud.Accoracy"+location.longitude.toString()+
                                " : "+location.latitude.toString()+" : "+location.accuracy
                        )
                }
            }
        }

    }
}