package com.kelas.mugnificent.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kelas.mugnificent.MainActivity
import com.kelas.mugnificent.R
import com.kelas.mugnificent.data.BannerData
import com.kelas.mugnificent.data.UserData
import com.kelas.mugnificent.databinding.ActivityRegistrasiBinding

class registrasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)

        binding.textLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSignUp.setOnClickListener {
            if (isInput(binding.inputName) && isInput(binding.inputUsername) && isInput(binding.inputEmail) && isInput(binding.inputPhone)) {
                val auth = FirebaseAuth.getInstance()
                val email = binding.inputEmail.text.toString()

                auth.createUserWithEmailAndPassword(email, "password")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val database = FirebaseDatabase.getInstance()
                            val user = database.getReference("user")
                            val data = UserData(getText(binding.inputName), getText(binding.inputUsername), email, getText((binding.inputPhone)))
                            user.child(auth.currentUser?.uid.toString()).setValue(data)
                            Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("EXTRA_USER", data)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Input tidak boleh kosong", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        setContentView(binding.root)
    }

    private fun isInput(editable: EditText): Boolean {
        return editable.text.isNotEmpty()
    }

    private fun isEmailInput(): Boolean {
        return true
    }

    private fun getText(editable: EditText): String {
        return editable.text.toString()
    }
}