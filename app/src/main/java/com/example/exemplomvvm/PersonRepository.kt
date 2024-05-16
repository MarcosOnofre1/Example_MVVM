package com.example.exemplomvvm

class PersonRepository {

    fun login (email: String, password: String): Boolean {
        return (email != "" && password != "")

    }
}