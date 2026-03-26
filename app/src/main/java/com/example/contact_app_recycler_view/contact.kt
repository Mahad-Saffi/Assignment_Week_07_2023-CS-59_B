package com.example.contact_app_recycler_view

data class Contact(
    val id: Long = System.currentTimeMillis(),
    var name: String,
    var phone: String,
    var photoUri: String? = null
)

