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
import com.example.bookingapp.R
import com.example.bookingapp.fragment.CategoryFragment
import com.example.bookingapp.fragment.HomeFragment
import com.squareup.picasso.Picasso


class DanhmucAdapter(val danhmucModel: MutableList<DanhmucModel>): RecyclerView.Adapter<DanhmucAdapter.DanhmucViewHolder>()  {

    inner class DanhmucViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        val imagedanhmuc : ImageView = itemView.findViewById(R.id.imageCardCategory)
        val tendanhmuc : TextView = itemView.findViewById(R.id.textCardCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhmucViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_category, parent, false)
        return DanhmucViewHolder(view)
    }

    override fun onBindViewHolder(holder: DanhmucViewHolder, position: Int) {
        Picasso.get().load(danhmucModel[position].imagedanhmuc).into(holder.imagedanhmuc)
        holder.tendanhmuc.text = danhmucModel[position].tendanhmuc

        holder.itemView.setOnClickListener { view ->
            val id : Int ?= danhmucModel[position].id
            val tendanhmuc : String? = danhmucModel[position].tendanhmuc

            val bundle = Bundle()
            if (id != null) {
                bundle.putInt("id", id)
            }
            bundle.putString("tendanhmuc", tendanhmuc)
            val activity =view.context as AppCompatActivity
            val ac = view.context as FragmentActivity
            val detailCateFragment = CategoryFragment()
            detailCateFragment.arguments = bundle



            val f: Fragment = ac.supportFragmentManager.findFragmentById(R.id.relative_main)!!

            if (f is CategoryFragment){

                Log.e("current fragment","1")
                activity.supportFragmentManager.beginTransaction().replace(R.id.homeCate,detailCateFragment).addToBackStack(null).commit()

            }else{
                activity.supportFragmentManager.beginTransaction().replace(R.id.layoutHome,detailCateFragment).addToBackStack(null).commit()
            }

        }
    }

    override fun getItemCount(): Int {
        return danhmucModel.size
    }


}
