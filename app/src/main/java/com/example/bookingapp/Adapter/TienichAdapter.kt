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
import com.example.bookingapp.Model.TienichModel
import com.example.bookingapp.R
import com.example.bookingapp.fragment.CategoryFragment
import com.example.bookingapp.fragment.HomeFragment
import com.squareup.picasso.Picasso


class TienichAdapter(val tienichModel: MutableList<TienichModel>): RecyclerView.Adapter<TienichAdapter.TienichViewHolder>()  {

    inner class TienichViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        val tentienich : TextView = itemView.findViewById(R.id.tentienich)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TienichViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_tienich, parent, false)
        return TienichViewHolder(view)
    }

    override fun onBindViewHolder(holder: TienichViewHolder, position: Int) {
        holder.tentienich.text = tienichModel[position].tentienich

    }

    override fun getItemCount(): Int {
        return tienichModel.size
    }


}
