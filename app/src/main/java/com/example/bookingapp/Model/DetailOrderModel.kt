package com.example.bookingapp.Model

data class DetailOrderModel(
    val id : Int ?= null,
    val image : ArrayList<String> ?= null,
    val name : String ?= null,
    val quantity : Int ?= null,
    val check_out : String ?= null,
    val check_in : String ?= null,
    val price : Int ?= null,
    val totalPrice : Int ?= null,
)
