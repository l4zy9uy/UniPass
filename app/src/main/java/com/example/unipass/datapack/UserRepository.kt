package com.example.unipass.datapack

import androidx.lifecycle.LiveData
import com.example.unipass.datapack.dao.UserDao

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) : Long {
        return userDao.addUser(user)
    }

}