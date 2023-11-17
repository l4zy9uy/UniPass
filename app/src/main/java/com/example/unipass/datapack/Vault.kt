package com.example.unipass.datapack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Vault",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("UserID"),
        childColumns = arrayOf("UserID"),
    )])
class Vault (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "VaultID")
    val vaultId: Int,
    @ColumnInfo(name = "UserID")
    val userId: Int,
    @ColumnInfo(name = "VaultName")
    val vaultName: String,
    @ColumnInfo(name = "Username")
    val username: String,
    @ColumnInfo(name = "HashedPassword")
    val hashedPassword: String,
    @ColumnInfo(name = "URL")
    val url: String?,
    @ColumnInfo(name = "Comment")
    val comment: String?

){
}