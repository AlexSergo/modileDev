package com.example.firstapp

import androidx.annotation.VisibleForTesting
import kotlin.Exception

object UserHolder {

    private val map = mutableMapOf<String, User>()
    private val phoneFormat = Regex("""^[+][\d]{11}""")
    private val phoneValid = Regex("""^(?:\+|\d)[\d\-\(\) ]{9,}\d""")

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User = User.makeUser(fullName, email = email, password = password)
        .also { user ->
            if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this email already exists")
            else map[user.login] = user
        }

    fun loginUser(login: String, password: String): String? {
        val phoneLogin = cleanPhone(login)
        print(password)
        return (if (phoneLogin.isNotEmpty()) {
            map[phoneLogin]
        } else {
            map[login.trim()]
        })?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }
    }


    fun registerUserByPhone(fullName: String, rawPhone: String): User {
        val cleanphone = cleanPhone(rawPhone)
        if (!isNumberValid(rawPhone))  throw IllegalArgumentException("Invalid number! ")
        if (map[cleanphone] != null)
            throw IllegalArgumentException(fullName + " already registered!")

        val user: User = User.makeUser(fullName, null, null, cleanphone)
        map[cleanphone] = user
        return user
    }

    private fun isNumberValid(rawPhone: String): Boolean {
        return rawPhone.matches(phoneValid)
    }

    fun requestAccessCode(login: String) {
        val cleanphone = cleanPhone(login)
        map[cleanphone]?.let {
            val code = it.generateAccessCode()
            it.sendAccessCodeToUser(it.phone!!, code)
            it.accessCode = code
            it.passwordHash = it.encrypt(code)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }

    private fun cleanPhone(phone: String): String {
        return phone.replace("""[^+\d]""".toRegex(), "")
    }
}