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
        val imageroom : ImageView = itemView.findViewById(R.id.imageRoom)
        val songuoi : TextView = itemView.findViewById(R.id.text_people)
        val sogiuong : TextView = itemView.findViewById(R.id.text_giuong)
        val gia : TextView = itemView.findViewById(R.id.text_price)
        val note : TextView = itemView.findViewById(R.id.note)
        val submit : Button = itemView.findViewById(R.id.submit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        Picasso.get().load(roomModel[position].imageroom).into(holder.imageroom)
        holder.tenroom.text = roomModel[position].tenroom
        holder.songuoi.text = roomModel[position].peoples
        holder.sogiuong.text = roomModel[position].sogiuong
        holder.gia.text = roomModel[position].gia
        holder.note.text = roomModel[position].note


        holder.submit.setOnClickListener { view ->
            val ac = view.context as FragmentActivity
            val sharePreference =  ac!!.getSharedPreferences("user", Context.MODE_PRIVATE)
            val getEmail = sharePreference.getString("Email","").toString()
            val getID = sharePreference.getString("ID","").toString()

            val id : Int ?= roomModel[position].id
            val img : String? = roomModel[position].imageroom
            val tenroom : String? = roomModel[position].tenroom
            val songuoi : String? = roomModel[position].peoples
            val sogiuong : String? = roomModel[position].sogiuong
            val note : String? = roomModel[position].note
            val gia : String? = roomModel[position].gia
            val activity =view.context as AppCompatActivity
            if (getID.isNotEmpty()){
                activity?.let {
                    val intent = Intent(it, PaymentActivity::class.java)
                    intent.putExtra("id", id.toString())
                    intent.putExtra("img", img)
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
