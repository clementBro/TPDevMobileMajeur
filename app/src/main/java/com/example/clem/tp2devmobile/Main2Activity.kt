package com.example.clem.tp2devmobile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.clem.tp2devmobile.adapter.EcoleAdapter
import com.example.clem.tp2devmobile.models.EcolePrimaire
import com.example.clem.tp2devmobile.services.IEcoleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Main2Activity : AppCompatActivity() {

    private val url = "http://10.69.1.87:8080"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        //instanciate retrofit for request
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        //create our service IEcoleService
        val iEcoleService = retrofit.create(IEcoleService::class.java)

        // do request
        getEcolePrimaire(iEcoleService)


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
                    val adapter : EcoleAdapter = EcoleAdapter(applicationContext, ecoles)
                    //get listView
                    val listViewEcoles = findViewById<ListView>(R.id.listSchool)
                    //set adapter
                    listViewEcoles.adapter = adapter

                    listViewEcoles.setOnItemClickListener { parent, view, position, id ->
                        val intent = Intent(this,MapsActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

            // if error
            override fun onFailure(call: Call<List<EcolePrimaire>>, t: Throwable) {
                error("KO")
            }
        })
    }
}
