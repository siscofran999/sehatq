package com.siscofran.sehatq.ui.main.profile

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.*
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.siscofran.sehatq.R
import com.siscofran.sehatq.ui.history.HistoryActivity
import com.siscofran.sehatq.ui.login.LoginActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : DaggerFragment(), View.OnClickListener {

    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }

        if(Profile.getCurrentProfile() != null){
            val params = Bundle()
            params.putString("fields", "id,email,gender,cover,picture.type(large)")
            GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                GraphRequest.Callback { response ->
                    if (response != null) {
                        try {
                            val data = response.jsonObject
                            if (data.has("picture")) {
                                val profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url")
                                setProfile(Uri.parse(profilePicUrl), Profile.getCurrentProfile()?.name)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }).executeAsync()
        }else if(GoogleSignIn.getLastSignedInAccount(activity) != null){
            val profile = GoogleSignIn.getLastSignedInAccount(activity)
            setProfile(profile?.photoUrl, profile?.displayName)
        }

        btn_logout.setOnClickListener(this)
        btn_history.setOnClickListener(this)
    }

    private fun setProfile(photoUrl: Uri?, displayName: String?) {
        activity?.let {
            Glide.with(it)
                .load(photoUrl)
                .circleCrop()
                .apply(RequestOptions().override(350, 350))
                .into(img)
        }
        txv_title.text = displayName
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_logout -> {
                if(Profile.getCurrentProfile() != null){
                    LoginManager.getInstance().logOut()
                    startActivity(Intent(activity, LoginActivity::class.java))
                    activity?.finish()
                }else if(GoogleSignIn.getLastSignedInAccount(activity) != null){
                    mGoogleSignInClient?.signOut()?.addOnCompleteListener {
                        startActivity(Intent(activity, LoginActivity::class.java))
                        activity?.finish()
                    }
                }
            }

            R.id.btn_history -> {
                startActivity(Intent(activity, HistoryActivity::class.java))
                activity?.finish()
            }
        }
    }
}