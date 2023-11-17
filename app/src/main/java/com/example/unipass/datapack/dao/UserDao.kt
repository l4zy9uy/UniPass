package com.example.unipass.datapack.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unipass.datapack.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User) : Long

    @Query("SELECT * FROM User")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT COUNT(email) FROM User WHERE Name = :username")
    fun isExistedUser(username: String) : Boolean

}