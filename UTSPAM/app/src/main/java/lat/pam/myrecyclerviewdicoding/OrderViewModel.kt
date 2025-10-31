package lat.pam.myrecyclerviewdicoding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {

    // Menyimpan daftar item yang dipesan
    private val _cartItems = MutableLiveData<ArrayList<Hero>>(arrayListOf())
    val cartItems: LiveData<ArrayList<Hero>> = _cartItems

    // Fungsi untuk menambah item ke keranjang
    fun addItem(hero: Hero) {
        val currentList = _cartItems.value ?: arrayListOf()
        currentList.add(hero)
        _cartItems.postValue(currentList)
    }

    // Fungsi untuk membersihkan keranjang setelah order selesai
    fun clearCart() {
        _cartItems.postValue(arrayListOf())
    }
}