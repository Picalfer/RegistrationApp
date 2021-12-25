package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.registration.constance.Constance
import com.example.registration.constance.Constance.AVATAR_ID
import com.example.registration.constance.Constance.LOGIN
import com.example.registration.constance.Constance.NAME
import com.example.registration.constance.Constance.PASSWORD
import com.example.registration.constance.Constance.PATRONYMIC
import com.example.registration.constance.Constance.REQUEST_CODE_SIGN_IN
import com.example.registration.constance.Constance.REQUEST_CODE_SIGN_UP
import com.example.registration.constance.Constance.SIGN_IN_STATE
import com.example.registration.constance.Constance.SIGN_STATE
import com.example.registration.constance.Constance.SIGN_UP_STATE
import com.example.registration.constance.Constance.SURNAME
import com.example.registration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var launcherIn: ActivityResultLauncher<Intent>? = null
    private var launcherUp: ActivityResultLauncher<Intent>? = null
    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var surname: String = "empty"
    private var patronymic: String = "empty"
    private var imIdAvatar: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcherIn = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result:ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val l = result.data?.getStringExtra(LOGIN)
                val p = result.data?.getStringExtra(PASSWORD)
                if (login == l && password == p) {
                    binding.imAvatar.visibility = VISIBLE
                    binding.imAvatar.setImageResource(imIdAvatar)
                    val textInfo = "$name $surname $patronymic"
                    binding.tvInfo.text = textInfo
                    binding.bSignUp.visibility = GONE
                    binding.bSignIn.text = "Sign out"
                } else {
                    binding.imAvatar.visibility = VISIBLE
                    binding.imAvatar.setImageResource(R.drawable.ups)
                    binding.tvInfo.text = "No such account exists"
                }
            }
        }

        launcherUp = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result:ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                login = result.data?.getStringExtra(LOGIN)!!
                password = result.data?.getStringExtra(PASSWORD)!!
                name = result.data?.getStringExtra(NAME)!!
                surname = result.data?.getStringExtra(SURNAME)!!
                patronymic = result.data?.getStringExtra(PATRONYMIC)!!
                imIdAvatar = result.data?.getIntExtra(AVATAR_ID, 0)!!
                binding.imAvatar.visibility = VISIBLE
                binding.imAvatar.setImageResource(imIdAvatar)
                val textInfo = "$name $surname $patronymic"
                binding.tvInfo.text = textInfo
                binding.bSignUp.visibility = GONE
                binding.bSignIn.text = "Sign out"
            }
        }
    }

    fun onClickSignIn(view: View) {
        if (binding.imAvatar.isVisible && binding.tvInfo.text.toString() != "No such account exists") {
            binding.imAvatar.visibility = INVISIBLE
            binding.tvInfo.text = ""
            binding.bSignUp.visibility = VISIBLE
            binding.bSignIn.text = getString(R.string.sign_in)
        } else {
            val i = Intent(this, SignInUpActivity::class.java).apply {
                putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            }
            launcherIn?.launch(i)
        }
    }

    fun onClickSignUp(view: View) {
        val i = Intent(this, SignInUpActivity::class.java).apply {
            putExtra(Constance.SIGN_STATE,Constance.SIGN_UP_STATE)
        }
        launcherUp?.launch(i)
    }
}