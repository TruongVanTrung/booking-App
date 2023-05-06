package com.example.bookingapp.Adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.Model.DanhmucModel
import com.example.bookingapp.Model.PhobienModel
import com.example.bookingapp.Model.TienichModel
import com.example.bookingapp.R
import com.example.bookingapp.fragment.CategoryFragment
import com.example.bookingapp.fragment.HomeFragment
import com.squareup.picasso.Picasso


class PhobienAdapter(val phobienModel: MutableList<PhobienModel>): RecyclerView.Adapter<PhobienAdapter.PhobienViewHolder>()  {

    inner class PhobienViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        val tenphobien : TextView = itemView.findViewById(R.id.tenphobien)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhobienViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_phobien, parent, false)
        return PhobienViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhobienViewHolder, position: Int) {
        holder.tenphobien.text = phobienModel[position].tenphobien

    }

    override fun getItemCount(): Int {
        return phobienModel.size
    }


}
