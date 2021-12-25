package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.registration.constance.Constance
import com.example.registration.constance.Constance.LOGIN
import com.example.registration.constance.Constance.PASSWORD
import com.example.registration.constance.Constance.REQUEST_CODE_SIGN_IN
import com.example.registration.constance.Constance.REQUEST_CODE_SIGN_UP
import com.example.registration.constance.Constance.SIGN_IN_STATE
import com.example.registration.constance.Constance.SIGN_STATE
import com.example.registration.constance.Constance.SIGN_UP_STATE
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
                
            }
        }

        launcherUp = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result:ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val l = result.data?.getStringExtra(LOGIN)

            }
        }
    }

    fun onClickSignIn(view: View) {
        val i = Intent(this, SignInUpActivity::class.java).apply {
            putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
        }
        launcherIn?.launch(i)
    }

    fun onClickSignUp(view: View) {
        val i = Intent(this, SignInUpActivity::class.java).apply {
            putExtra(Constance.SIGN_STATE,Constance.SIGN_UP_STATE)
        }
        launcherUp?.launch(i)
    }
}