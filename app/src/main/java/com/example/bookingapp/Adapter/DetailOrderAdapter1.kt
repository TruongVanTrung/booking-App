package com.example.bookingapp.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.ApiService
import com.example.bookingapp.Model.DetailOrderModel
import com.example.bookingapp.Model.ResponseMessageModel
import com.example.bookingapp.PaymentActivity
import com.example.bookingapp.R
import com.example.bookingapp.ServiceGenerator
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class DetailOrderAdapter1(val detailOrder: MutableList<DetailOrderModel>): RecyclerView.Adapter<DetailOrderAdapter1.DetailOrderViewHolder>()  {

    inner class DetailOrderViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        val imageRoom = itemView.findViewById<ImageView>(R.id.imageRoom)
        val tenphong = itemView.findViewById<TextView>(R.id.tenphong)
        val iddonhang = itemView.findViewById<TextView>(R.id.iddonhang)
        val soluong = itemView.findViewById<TextView>(R.id.soluong)
        val price = itemView.findViewById<TextView>(R.id.price)
        val text_price = itemView.findViewById<TextView>(R.id.text_price)
        val dateNhan = itemView.findViewById<TextView>(R.id.text_people)
        val dateTra = itemView.findViewById<TextView>(R.id.text_wifi)
        val submit = itemView.findViewById<Button>(R.id.submit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_payment1, parent, false)
        return DetailOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailOrderViewHolder, position: Int) {
        Picasso.get().load(detailOrder[position].img).into(holder.imageRoom)
        holder.tenphong.text = detailOrder[position].nameRoom
        holder.iddonhang.text = detailOrder[position].id.toString()
        holder.soluong.text = detailOrder[position].soluong.toString()
        holder.price.text = detailOrder[position].gia.toString()
        holder.text_price.text = detailOrder[position].totalPrice.toString()
        holder.dateNhan.text = detailOrder[position].dateNhan
        holder.dateTra.text = detailOrder[position].dateTra

        holder.submit.setOnClickListener { view ->
            val activity =view.context as AppCompatActivity

            //activity?.let {
                //val intent = Intent(it, PaymentActivity::class.java)
                //intent.putExtra("id", detailOrder[position].id.toString())
                //it.startActivity(intent)
                //activity?.finish()
            //}
            val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
            val idd = detailOrder[position].id.toString().toInt()
            val delete = serviceGenerator.deleteOrder(idd)

            delete.enqueue(object :
                retrofit2.Callback<ResponseMessageModel> {
                override fun onResponse(
                    call: Call<ResponseMessageModel>,
                    response: Response<ResponseMessageModel>
                ) {
                    if (response.isSuccessful){
                        response.body()!!.message.toString()
                        Log.e("response", response.body()!!.message.toString())
                            activity.onBackPressed()
                    }
                }

                override fun onFailure(call: Call<ResponseMessageModel>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error", t.message.toString())

                }

            })
        }
    }

    override fun getItemCount(): Int {
        return detailOrder.size
    }


}