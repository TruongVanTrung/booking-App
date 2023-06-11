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
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookingapp.ApiService
import com.example.bookingapp.Model.DetailOrderModel
import com.example.bookingapp.Model.ResponseMessageModel
import com.example.bookingapp.PaymentActivity
import com.example.bookingapp.R
import com.example.bookingapp.ServiceGenerator
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class DetailOrderAdapter2(val detailOrder: MutableList<DetailOrderModel>): RecyclerView.Adapter<DetailOrderAdapter2.DetailOrderViewHolder>()  {

    inner class DetailOrderViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        val imageSlider : ImageSlider = itemView.findViewById(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_fragment2, parent, false)
        return DetailOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailOrderViewHolder, position: Int) {
        for (i in detailOrder[position].image!!){
            Log.e("image_slide", i)
            holder.imageList.add(SlideModel(i))
        }
        holder.imageSlider.setImageList(holder.imageList, ScaleTypes.CENTER_CROP)
        holder.tenphong.text = detailOrder[position].name
        holder.iddonhang.text = detailOrder[position].id.toString()
        holder.soluong.text = detailOrder[position].quantity.toString()
        holder.price.text = detailOrder[position].price.toString()
        holder.text_price.text = detailOrder[position].totalPrice.toString()
        holder.dateNhan.text = detailOrder[position].check_in
        holder.dateTra.text = detailOrder[position].check_out


    }

    override fun getItemCount(): Int {
        return detailOrder.size
    }


}