package lat.pam.droidcafe

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // --- KODE BARU UNTUK SPINNER ---
        // 1. Temukan Spinner dari layout berdasarkan ID-nya.
        val spinnerKota: Spinner = findViewById(R.id.spinner_city)

        // 2. Buat ArrayAdapter menggunakan array string dari strings.xml.
        val adapter = ArrayAdapter.createFromResource(
            this, // Context
            R.array.spinner_city, // Sumber data (dari res/values/strings.xml)
            android.R.layout.simple_spinner_item // Layout standar untuk item yang terpilih
        )

        // 3. Tentukan layout yang akan digunakan saat daftar pilihan muncul (dropdown).
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 4. Terapkan adapter ke Spinner.
        spinnerKota.adapter = adapter

        // 5. Tambahkan Listener untuk merespons pilihan pengguna.
        spinnerKota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Ambil nama kota yang dipilih dari adapter berdasarkan posisinya
                val kotaTerpilih: String = parent.getItemAtPosition(position).toString()

                // Tampilkan Toast untuk memberitahu kota mana yang dipilih.
                displayToast("Asal kota: $kotaTerpilih")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Bisa dikosongkan jika tidak ada aksi khusus yang diperlukan.
            }
        }
        // --- BATAS KODE BARU ---
    }

    /**
     * Fungsi ini (yang sudah ada sebelumnya) menangani klik pada RadioButton
     * untuk metode pengiriman.
     */
    fun onRadioButtonClicked(view: View) {
        // Cek apakah tombol radiobutton dicentang.
        val checked = (view as RadioButton).isChecked

        // Cek RadioButton mana yang diklik.
        when (view.id) {
            R.id.sameday -> if (checked) // Layanan Same day
                displayToast(getString(R.string.same_day_messenger_service))

            R.id.nextday -> if (checked) // Pengiriman Next day
                displayToast(getString(R.string.next_day_ground_delivery))

            R.id.pickup -> if (checked) // Ambil sendiri
                displayToast(getString(R.string.pick_up))

            else -> {
                // Tidak melakukan apa-apa jika ID tidak cocok
            }
        }
    }

    /**
     * Fungsi helper untuk menampilkan pesan Toast singkat.
     */
    private fun displayToast(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}