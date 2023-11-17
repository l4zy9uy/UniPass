package com.example.unipass.datapack

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unipass.datapack.dao.SecurityLogDao
import com.example.unipass.datapack.dao.UserDao
import com.example.unipass.datapack.dao.VaultDao

@Database(entities = [User::class, Vault::class, SecurityLog::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun vaultDao() : VaultDao
    abstract fun securityLogDao() : SecurityLogDao
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}