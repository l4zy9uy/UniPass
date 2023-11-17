package com.example.unipass.datapack.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unipass.datapack.SecurityLog
import com.example.unipass.datapack.Vault

@Dao
interface SecurityLogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVault(log: SecurityLog) : Long
    @Query("SELECT * FROM `Security Log`")
    fun readAllData(): LiveData<List<SecurityLog>>
}