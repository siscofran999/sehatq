package com.siscofran.sehatq.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.siscofran.sehatq.R
import com.siscofran.sehatq.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = LoginActivity::class.qualifiedName
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val callbackManager = CallbackManager.Factory.create()
    var mProfileTracker: ProfileTracker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_login_google.setOnClickListener(this)
        btn_login_fb.setOnClickListener(this)
        btn_login_fb.setReadPermissions(listOf("email","public_profile"))
        btn_login.setOnClickListener(this)

        if(Profile.getCurrentProfile() != null || GoogleSignIn.getLastSignedInAccount(this) != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login_google -> {
                val account = GoogleSignIn.getLastSignedInAccount(this)
                if(account == null){
                    val signInIntent = mGoogleSignInClient?.signInIntent
                    startActivityForResult(signInIntent, 1)
                }else{
                    // masuk ke main
                    startActivity(Intent(application, MainActivity::class.java))
                    finish()
                }
            }

            R.id.btn_login_fb -> {
                btn_login_fb.registerCallback(callbackManager, object: FacebookCallback<LoginResult>{
                    override fun onSuccess(result: LoginResult) {
                        if(Profile.getCurrentProfile() == null){
                            mProfileTracker = object : ProfileTracker(){
                                override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                                    mProfileTracker?.stopTracking()
                                    startActivity(Intent(application, MainActivity::class.java))
                                    finish()
                                }
                            }
                        }else{
                            startActivity(Intent(application, MainActivity::class.java))
                            finish()
                        }
                    }

                    override fun onCancel() {
                        Log.i(TAG,"cancel fb broo")
                        Toast.makeText(application, "Maaf, anda cancel", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: FacebookException) {
                        Toast.makeText(application, "Maaf, anda kendala silakan coba lagi", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            R.id.btn_login -> {
                if(edt_user.text?.isNotEmpty()!! && edt_pass.text?.isNotEmpty()!!){
                    startActivity(Intent(application, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
            else -> {
                callbackManager.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            //val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
            // masuk ke main
            startActivity(Intent(application, MainActivity::class.java))
            finish()
        } catch (e: ApiException) {
            Toast.makeText(application, "Maaf, anda kendala silakan coba lagi", Toast.LENGTH_SHORT).show()
        }
    }
}