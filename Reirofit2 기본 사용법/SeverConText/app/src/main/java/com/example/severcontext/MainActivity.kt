package com.example.severcontext

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        val call = service.getPosts("1")

            call!!.enqueue(object : Callback<PostResult?> {
                override fun onResponse(
                    call: Call<PostResult?>,
                    response: Response<PostResult?>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        Log.d(ContentValues.TAG, "onResponse : 성공, 결과 ${result.toString()}".trimIndent())
                    } else {
                        Log.d(ContentValues.TAG, "onResponse : 실패")
                    }
                }

                override fun onFailure(
                    call: Call<PostResult?>,
                    t: Throwable
                ) {
                    Log.d(ContentValues.TAG, "onFailure: " + t.message)
                }
            })

    }
}