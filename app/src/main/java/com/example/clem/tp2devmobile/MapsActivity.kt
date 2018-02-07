package com.example.clem.tp2devmobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.clem.tp2devmobile.models.EcolePrimaire
import com.example.clem.tp2devmobile.services.IEcoleService

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private lateinit var mMap: GoogleMap
    private val url = "http://10.69.1.87:8080"
    private val centerMap = LatLng(45.750000, 4.850000)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        //instanciate retrofit for request
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        //create our service IEcoleService
        val iEcoleService = retrofit.create(IEcoleService::class.java)

        // get all school
        getEcolePrimaire(iEcoleService)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerMap, 10f))
    }

    private fun getEcolePrimaire(iEcoleService : IEcoleService) {

        // call GetListEcole
        val ecoleRequest = iEcoleService.getEcolesPrimaire()

        // on server response
        ecoleRequest.enqueue(object : Callback<List<EcolePrimaire>> {

            //if response
            override fun onResponse(call: Call<List<EcolePrimaire>>, response: Response<List<EcolePrimaire>>) {
                val ecoles = response.body()
                if (ecoles != null) {
                    for(ec in ecoles){
                        mMap.addMarker(MarkerOptions().position(LatLng(ec.latitude, ec.longitude)).title(ec.nom).snippet(ec.addresse))
                    }
                }
            }

            // if error
            override fun onFailure(call: Call<List<EcolePrimaire>>, t: Throwable) {
                error("KO")
            }
        })
    }

    override fun onMarkerClick(m: Marker?): Boolean {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(m?.position))
        return true
    }

}
