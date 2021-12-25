package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import androidx.core.view.isVisible
import com.example.registration.constance.Constance
import com.example.registration.constance.Constance.SIGN_IN_STATE
import com.example.registration.constance.Constance.SIGN_STATE
import com.example.registration.databinding.ActivitySignInUpBinding

class SignInUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInUpBinding
    private var signState: String = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!

        if (signState == Constance.SIGN_IN_STATE) {
            binding.edName.visibility = GONE
            binding.edSurname.visibility = GONE
            binding.edPatronymic.visibility = GONE
            binding.bAvatar.visibility = INVISIBLE
        }
    }

    fun onClickDone(view: View) {
        if (signState == Constance.SIGN_UP_STATE) {
            val i = Intent()
            i.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            i.putExtra(Constance.PASSWORD, binding.edPassword.text.toString())
            i.putExtra(Constance.NAME, binding.edName.text.toString())
            i.putExtra(Constance.SURNAME, binding.edSurname.text.toString())
            i.putExtra(Constance.PATRONYMIC, binding.edPatronymic.text.toString())
            if(binding.imAvatar.isVisible) i.putExtra(Constance.AVATAR_ID, R.drawable.i)
            setResult(RESULT_OK, i)
            finish()
        } else if(signState == Constance.SIGN_IN_STATE) {
            val i = Intent()
            i.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            i.putExtra(Constance.PASSWORD, binding.edPassword.text.toString())
            setResult(RESULT_OK, i)
            finish()
        }
    }

    fun onClickAvatar(view: View) {
        binding.imAvatar.visibility = VISIBLE
    }
}