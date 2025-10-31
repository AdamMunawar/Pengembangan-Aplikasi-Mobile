package lat.pam.myrecyclerviewdicoding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import lat.pam.myrecyclerviewdicoding.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Inisialisasi SessionManager
        sessionManager = SessionManager(this)

        // === TAMBAHAN LOGIKA AUTO-LOGIN ===
        // Tahan splash screen sampai kita selesai cek login
        var keepSplashScreenOn = true
        splashScreen.setKeepOnScreenCondition { keepSplashScreenOn }

        if (sessionManager.isLoggedIn()) {
            // Jika sudah login, langsung ke MainActivity
            keepSplashScreenOn = false // Hentikan splash screen
            goToMainActivity()
        } else {
            // Jika belum login, tampilkan layout WelcomeActivity
            keepSplashScreenOn = false // Hentikan splash screen

            enableEdgeToEdge()
            binding = ActivityWelcomeBinding.inflate(layoutInflater)
            setContentView(binding.root)

            ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            binding.btnLogin.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            binding.btnRegister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}