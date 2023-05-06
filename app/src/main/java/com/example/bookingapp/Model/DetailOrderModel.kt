package com.example.bookingapp.Model

data class DetailOrderModel(
    val id : Int ?= null,
    val img : String ?= null,
    val nameRoom : String ?= null,
    val soluong : Int ?= null,
    val dateNhan : String ?= null,
    val dateTra : String ?= null,
    val gia : Int ?= null,
    val totalPrice : Int ?= null,
)
