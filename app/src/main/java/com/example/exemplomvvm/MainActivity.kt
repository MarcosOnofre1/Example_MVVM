package com.example.exemplomvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exemplomvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()

        binding.buttonLogin.setOnClickListener(this)

       // binding.textWelcome.text = "ola"

    }

    private fun setObserver() {
        /**
          observe ficara sempre atento se acaso houver mudança no welcome(), que por sua vez, é um LiveDate que retorna do textWelcome, que é um MutableLivadeData,
         pode ser alterado.
         **/
        viewModel.welcome().observe(this, Observer {
            binding.textWelcome.text = it
        })
        viewModel.login().observe(this, Observer {
           if (it){
               Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(this, "ACCESS DENIED!", Toast.LENGTH_SHORT).show()
           }
        })
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            viewModel.doLogin(email, password)

        }
    }
}