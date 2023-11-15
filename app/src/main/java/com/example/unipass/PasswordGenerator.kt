package com.example.unipass

class PasswordGenerator(
    private var length: Int,
    private var includeUpperCaseLetters: Boolean,
    private var includeLowerCaseLetters: Boolean,
    private var includeSymbols: Boolean,
    private var includeNumbers: Boolean
) {

    private val UPPERCASE = 0
    private val LOWERCASE = 1
    private val NUMBERS = 2
    private val SYMBOLS = 3

    fun generatePassword(): String {
        var password = ""
        val list = ArrayList<Int>()
        if (includeUpperCaseLetters) {
            list.add(UPPERCASE)
        }
        if (includeLowerCaseLetters) {
            list.add(LOWERCASE)
        }
        if (includeNumbers) {
            list.add(NUMBERS)
        }
        if (includeSymbols) {
            list.add(SYMBOLS)
        }

        for (i in 1..length) {
            if (list.isNotEmpty()) {
                when (list.random()) {
                    UPPERCASE -> password += ('A'..'Z').random().toString()
                    LOWERCASE -> password += ('a'..'z').random().toString()
                    NUMBERS -> password += ('0'..'9').random().toString()
                    SYMBOLS -> password += listOf('!', '@', '#', '$', '%', '&', '*', '+', '=', '-', '~', '?', '/', '_').random().toString()
                }
            }
        }
        return password
    }
}