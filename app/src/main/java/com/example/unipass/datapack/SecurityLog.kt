package com.example.unipass.datapack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Security Log",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("UserID"),
        childColumns = arrayOf("UserID"),
    )])
class SecurityLog (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "LogID")
    val logId: Int,
    @ColumnInfo(name = "UserID")
    val userId: Int,
    @ColumnInfo(name = "Event")
    val event: String,
    @ColumnInfo(name = "Date of Event")
    val date: String,
){
}