package com.example.bookingapp.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookingapp.LoginActivity
import com.example.bookingapp.Model.DanhmucModel
import com.example.bookingapp.Model.PhobienModel
import com.example.bookingapp.Model.RoomModel
import com.example.bookingapp.Model.TienichModel
import com.example.bookingapp.PaymentActivity
import com.example.bookingapp.R
import com.example.bookingapp.fragment.CategoryFragment
import com.example.bookingapp.fragment.DetailDoitacaFragment
import com.example.bookingapp.fragment.HomeFragment

import com.squareup.picasso.Picasso


class RoomAdapter(val roomModel: MutableList<RoomModel>): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>()  {

    inner class RoomViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        val tenroom : TextView = itemView.findViewById(R.id.tenphong)
        //val imageroom : ImageView = itemView.findViewById(R.id.imageRoom)
        val songuoi : TextView = itemView.findViewById(R.id.text_people)
        val sogiuong : TextView = itemView.findViewById(R.id.text_giuong)
        val gia : TextView = itemView.findViewById(R.id.text_price)
        val note : TextView = itemView.findViewById(R.id.note)
        val submit : Button = itemView.findViewById(R.id.submit)
        val imageSlider : ImageSlider = itemView.findViewById(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
       // Create image list
        for (i in roomModel[position].image!!){
            Log.e("image_slide", i)
            holder.imageList.add(SlideModel(i))
        }

        holder.imageSlider.setImageList(holder.imageList, ScaleTypes.CENTER_CROP)
        //Picasso.get().load(roomModel[position].image).into(holder.imageroom)
        holder.tenroom.text = roomModel[position].name.toString().take(54)
        holder.songuoi.text = roomModel[position].peoples
        holder.sogiuong.text = roomModel[position].giuong
        holder.gia.text = roomModel[position].price
        holder.note.text = roomModel[position].note.toString().take(111)


        holder.submit.setOnClickListener { view ->
            val ac = view.context as FragmentActivity
            val sharePreference =  ac!!.getSharedPreferences("user", Context.MODE_PRIVATE)
            val getEmail = sharePreference.getString("Email","").toString()
            val getID = sharePreference.getString("ID","").toString()
            val editor = sharePreference.edit()
            editor.putInt("activity", 1)
            editor.apply()

            val id : Int ?= roomModel[position].id
            val img : ArrayList<String>? = roomModel[position].image
            val tenroom : String? = roomModel[position].name
            val songuoi : String? = roomModel[position].peoples
            val sogiuong : String? = roomModel[position].giuong
            val note : String? = roomModel[position].note
            val gia : String? = roomModel[position].price
            val activity =view.context as AppCompatActivity
            if (getID.isNotEmpty()){
                activity?.let {
                    val intent = Intent(it, PaymentActivity::class.java)
                    intent.putExtra("id", id.toString())
                    intent.putStringArrayListExtra("img", img)
                    intent.putExtra("tenroom", tenroom)
                    intent.putExtra("songuoi", songuoi)
                    intent.putExtra("sogiuong", sogiuong)
                    intent.putExtra("note", note)
                    intent.putExtra("gia", gia)
                    it.startActivity(intent)
                    //activity?.finish()

                }
            }else{
                ac?.let {
                    val getA = sharePreference.getInt("activity",3).toString()
                    Log.e("activity", getA)
                    it.startActivity(Intent(it, LoginActivity::class.java))
                    //ac?.finish()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return roomModel.size
    }


}
