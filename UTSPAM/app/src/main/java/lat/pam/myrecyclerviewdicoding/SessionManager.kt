package lat.pam.myrecyclerviewdicoding

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        private const val PREF_NAME = "AuthAppPrefs"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        // Key untuk menyimpan nama
        private const val KEY_NAME = "name"
    }

    /**
     * Menyimpan data pengguna saat registrasi
     */
    fun registerUser(name: String, email: String, pass: String) {
        editor.putString(KEY_NAME, name) // Simpan nama
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PASSWORD, pass)
        editor.apply()
    }

    /**
     * Memeriksa kredensial pengguna saat login
     */
    fun loginUser(email: String, pass: String): Boolean {
        val savedEmail = prefs.getString(KEY_EMAIL, null)
        val savedPassword = prefs.getString(KEY_PASSWORD, null)

        if (email == savedEmail && pass == savedPassword) {
            // Jika cocok, ambil nama yang sudah terdaftar
            val name = prefs.getString(KEY_NAME, email) // Default ke email jika nama null

            // Buat sesi login dengan nama tersebut
            createLoginSession(name!!)
            return true
        }
        return false
    }

    /**
     * Membuat sesi login, set flag isLoggedIn = true
     */
    private fun createLoginSession(name: String) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_NAME, name) // Simpan nama pengguna yang sedang login
        editor.apply()
    }

    /**
     * Memeriksa status login
     */
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    /**
     * Mengambil nama pengguna yang sedang login
     */
    fun getUserName(): String? {
        // Ambil nama, jika tidak ada, default ke "Pengguna"
        return prefs.getString(KEY_NAME, "Pengguna")
    }

    /**
     * Menghapus sesi login
     */
    fun logout() {
        editor.clear() // Hapus semua data
        editor.apply()
    }
}