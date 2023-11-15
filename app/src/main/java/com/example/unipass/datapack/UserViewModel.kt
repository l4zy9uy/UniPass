package com.example.unipass.datapack

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.Calendar
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData : LiveData<List<User>>
    private val repository: UserRepository
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.addUser(user)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registerUser(name: String, email: String, password: String, salt: ByteArray): User {
        val hashedPassword = hashPassword(password, salt)
        val calendar = Calendar.getInstance()

        val creationDate = LocalDateTime.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1, // Adjust for zero-based month
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND)
        )
        return User(name, email, hashedPassword.toHex(), salt.toHex(), creationDate.toString())
    }
    private fun hashPassword(password: String, salt: ByteArray) : ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, 65536, 128)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val key = factory.generateSecret(spec)
        return key.encoded
    }
    fun generateSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }
    private fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
}