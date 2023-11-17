package com.example.unipass.datapack.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unipass.datapack.User
import com.example.unipass.datapack.Vault

@Dao
interface VaultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVault(vault: Vault) : Long
    @Query("SELECT * FROM Vault")
    fun readAllData(): LiveData<List<Vault>>
}