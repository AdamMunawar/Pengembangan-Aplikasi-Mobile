package lat.pam.myrecyclerviewdicoding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lat.pam.myrecyclerviewdicoding.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnRegisterSubmit.setOnClickListener {
            // Ambil teks dari semua field
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Validasi input
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Nama, Email, dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                // Kirim semua data ke SessionManager
                sessionManager.registerUser(name, email, password)

                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()

                // Tutup activity register
                finish()
            }
        }
    }
}