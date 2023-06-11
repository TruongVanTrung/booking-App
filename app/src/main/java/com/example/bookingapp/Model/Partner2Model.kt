package com.example.bookingapp.Model

data class Partner2Model(
    val id : Int ?= null,
    val image : ArrayList<String>? = null,
    val name :String? = null,
    val namecategory : String? = null,
    val note : String? = null,
    val utilities : ArrayList<String> ?= null,
    val popular :  ArrayList<String>? = null,
    val address : String? = null,
)
