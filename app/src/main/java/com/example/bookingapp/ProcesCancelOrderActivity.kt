package com.example.bookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bookingapp.Model.ResponseMessageModel
import retrofit2.Call
import retrofit2.Response

class ProcesCancelOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_proces_cancel_order)

        val intent = intent
        var id = intent.getStringExtra("id")
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val delete = serviceGenerator.deleteOrder(id.toString().toInt())

        serviceGenerator.deleteOrder(id.toString().toInt()).enqueue(object :
            retrofit2.Callback<ResponseMessageModel> {
            override fun onResponse(
                call: Call<ResponseMessageModel>,
                response: Response<ResponseMessageModel>
            ) {
                if (response.isSuccessful){
                    response.body()!!.message.toString()
                    Log.e("response", response.body()!!.message.toString())
                    Log.e("responseID", response.body()!!.id.toString())
                }
            }

            override fun onFailure(call: Call<ResponseMessageModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())

            }

        })
        onBackPressed();
    }
}