package com.example.bookingapp

import com.example.bookingapp.Model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("doitac/")
    fun getDoitac() : Call<MutableList<DoitacModel>>

    @GET("danhmuc/")
    fun getDanhmuc() : Call<MutableList<DanhmucModel>>

    @GET("danhmuc/{id}")
    fun getDanhmucDetail(@Path("id") id:Int) : Call<MutableList<DoitacModel>>

    @GET("doitac/imageAll/{id}")
    fun getAllImageDoitac(@Path("id") id : Int) : Call<MutableList<ModelSlide>>

    @GET("doitac/tienichAll/{id}")
    fun getAllTienichDoitac(@Path("id") id : Int) : Call<MutableList<TienichModel>>

    @GET("doitac/phobienAll/{id}")
    fun getAllPhobienDoitac(@Path("id") id : Int) : Call<MutableList<PhobienModel>>

    @GET("doitac/roomAll/{id}")
    fun getAllRoomDoitac(@Path("id") id : Int) : Call<MutableList<RoomModel>>

    @POST("login/")
    fun sendReqLogin(@Body postLoginModel: PostLoginModel) : Call<ResponseMessageModel>

    @POST("payment/")
    fun sendReqPayment(@Body pymentModel : PymentModel) : Call<ResponseMessageModel>

    @GET("payment/detail1/{id}/{status}")
    fun getAllDetailPayment1(@Path("id") id : Int, @Path("status") status : Int) : Call<MutableList<DetailOrderModel>>

    @GET("payment/delete/{id}")
    fun deleteOrder(@Path("id") id : Int) : Call<ResponseMessageModel>
}