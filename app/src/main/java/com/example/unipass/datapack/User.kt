package com.example.unipass.datapack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User")
data class User(
    @ColumnInfo(name = "UserID")
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "Password")
    val hashedPassword: String,
    @ColumnInfo(name = "Salt")
    val salt: String,
    @ColumnInfo(name = "CreationDate")
    val creationDate: String
)