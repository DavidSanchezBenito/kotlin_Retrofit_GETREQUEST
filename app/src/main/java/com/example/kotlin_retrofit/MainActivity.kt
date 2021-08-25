package com.example.kotlin_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import kotlin.math.log

/*
https://www.youtube.com/watch?v=5gFrXGbQsc8
https://www.youtube.com/watch?v=_bVWsL5CHh4
I have to create an Interface and a DAta Class, thanks to install plugin: Kotlin data class file from JSON
 */

class MainActivity : AppCompatActivity() {

val BASE_URL = "https://jsonplaceholder.typicode.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        getMyData()
    }


    private fun getMyData() {
       val retrofitBuilder = Retrofit.Builder()

         .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
           .build()
           .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        //ctrl + shift + space to generate all the code
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                //responseBody contains all the data from the web
              val responseBody = response.body()!!  //add nullsafe operator


                val myStringBuilder = StringBuilder()
             /*
                for (i in responseBody) {
                    myStringBuilder.append(i.id)
                    myStringBuilder.append(("\n"))
                    // myStringBuilder.append((i.body))
                    // myStringBuilder.append(i.title)
                    // myStringBuilder.append(i.userId)
                }
*/
                val adaptadorCreado =myAdapter(baseContext, responseBody)
                id_recicler.setHasFixedSize(true)
                id_recicler.layoutManager=LinearLayoutManager(this@MainActivity)
                id_recicler.adapter =adaptadorCreado

           //     id_textView.text = myStringBuilder
                }




            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("Cuidado", "on failure: " + t.message)
            }
        })
    }
}