package com.kelas.mugnificent.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kelas.mugnificent.MainActivity
import com.kelas.mugnificent.R
import com.kelas.mugnificent.data.UserData
import com.kelas.mugnificent.preferences.AppPreference

class SplashActivity : AppCompatActivity() {
    private val splashDelay: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AppPreference.init(this)

        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this, OnboardingActivity::class.java)

            if (AppPreference.getBool("is_introduced", false)) {
                val auth = FirebaseAuth.getInstance()

                if (auth.currentUser != null) {
                    val database = FirebaseDatabase.getInstance()
                    val user = database.getReference("user").child(auth.currentUser?.uid!!)

                    user.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                intent = Intent(this@SplashActivity, MainActivity::class.java)
                                intent.putExtra("EXTRA_USER", snapshot.getValue(UserData::class.java))
                            } else {
                                intent = Intent(this@SplashActivity, SignUpActivity::class.java)
                                auth.signOut()
                            }

                            startActivity(intent)
                            finish()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            auth.signOut()
                            intent = Intent(this@SplashActivity, SignUpActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
                } else {
                    intent = Intent(this, SignUpActivity::class.java)
                }
            } else {
                startActivity(intent)
                finish()
            }
        }, splashDelay)
    }
}