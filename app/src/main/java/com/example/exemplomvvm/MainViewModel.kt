package com.example.exemplomvvm

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    // dessa maneira a ViewModel e a activity conseguem intender as notificações.
    private var textWelcome = MutableLiveData<String>()
    // verifica o login
    private var login = MutableLiveData<Boolean>()
    private val personRepository = PersonRepository()

    init {
        textWelcome.value = "Olá!"
    }

    // aqui agora tem o livedata, onde nao pode mais ser alterado. Agora voce pode ser usado na activity
    fun welcome(): LiveData<String> {
        return textWelcome
    }

    fun login(): LiveData<Boolean> {
        return login
    }

    //verifica o login se esta correto
    fun doLogin(email: String, password: String) {
       login.value = personRepository.login(email, password)

    }

}