package com.example.unipass

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val name: String, val email: String) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}