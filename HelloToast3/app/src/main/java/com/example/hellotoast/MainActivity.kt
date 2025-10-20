package com.example.hellotoast


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private var mCount = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonMap = findViewById<Button>(R.id.button_map)
        val buttonDial = findViewById<Button>(R.id.button_dial)
        val buttonShare = findViewById<Button>(R.id.button_share)


        buttonCountUp.setOnClickListener(View.OnClickListener {
            mCount++;
            Log.d("mCount", Integer.toString(mCount))
            if (mShowCount != null)
                mShowCount.text = mCount.toString()
        })


        buttonToast.setOnClickListener(View.OnClickListener {
            val tulisan: String = mShowCount?.text.toString()
            val toast: Toast = Toast.makeText(this, "Angka yang dimunculkan "+tulisan, Toast.LENGTH_LONG)
            toast.show()
        })


        buttonSwitchPage.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        })

        buttonBrowser.setOnClickListener(View.OnClickListener {
            val intentbrowse = Intent(Intent.ACTION_VIEW)
            intentbrowse.setData(Uri.parse("https://www.google.com/"))
            startActivity(intentbrowse)
        })


        buttonMap.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=Kampus 3 UIN Sunan Gunung Djati Bandung")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        buttonDial.setOnClickListener {
            val phoneNumber = "081000000000"
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialIntent)
        }

        buttonShare.setOnClickListener {
            val shareText = "Halo, Semoga Harimu Menyenangkan."
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
        }


    }

}
