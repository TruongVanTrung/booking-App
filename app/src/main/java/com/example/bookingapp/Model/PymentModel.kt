package com.example.bookingapp.Model

data class PymentModel(
    val idroom : Int ?= null,
    val iduser : Int ?= null,
    val name : String ?= null,
    val phone : String ?= null,
    val email : String ?= null,
    val count : Int ?= null,
    val dateNhan : String ?= null,
    val dateTra : String ?= null,
)
