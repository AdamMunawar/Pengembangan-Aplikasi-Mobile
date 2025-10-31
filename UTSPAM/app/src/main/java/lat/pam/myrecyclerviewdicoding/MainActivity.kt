package lat.pam.myrecyclerviewdicoding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // HAPUS SEMUA LOGIKA RECYCLERVIEW, SESSION, DAN MENU DARI SINI

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Hubungkan Bottom Nav dengan NavController
        bottomNavView.setupWithNavController(navController)

        // Atur padding untuk system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Hanya atur padding bawah untuk BottomNav, sisanya biarkan (atau atur di fragment)
            v.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }
    }
}