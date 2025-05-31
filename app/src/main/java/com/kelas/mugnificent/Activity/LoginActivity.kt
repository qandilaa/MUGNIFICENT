package com.kelas.mugnificent.Activity

import android.content.Intent
import android.os.Bundle
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
import com.kelas.mugnificent.data.BannerData
import com.kelas.mugnificent.data.UserData
import com.kelas.mugnificent.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.btnSignUp.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            val email = binding.inputName.text.toString()

            auth.signInWithEmailAndPassword(email, "password")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val database = FirebaseDatabase.getInstance()
                        val user = database.getReference("user").child(auth.currentUser?.uid!!)

                        user.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    intent.putExtra("EXTRA_USER", snapshot.getValue(UserData::class.java))
                                    startActivity(intent)
                                    finish()
                                } else {
                                    auth.signOut()
                                    Toast.makeText(this@LoginActivity, "User tidak ditemukan di database", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                auth.signOut()
                                Toast.makeText(this@LoginActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.textLogIn.setOnClickListener {
            val intent = Intent(this, registrasiActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(binding.root)
    }


}