package lat.pam.myrecyclerviewdicoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import lat.pam.myrecyclerviewdicoding.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    // Ambil ViewModel yang sama
    private val orderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)

        // Amati perubahan di keranjang
        orderViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                binding.tvOrderItems.text = "Keranjang kosong"
                binding.btnKirim.isEnabled = false // Matikan tombol jika kosong
            } else {
                // Tampilkan nama item
                val itemNames = items.joinToString(separator = "\n") { "- ${it.name}" }
                binding.tvOrderItems.text = itemNames
                binding.btnKirim.isEnabled = true
            }
        }

        binding.btnKirim.setOnClickListener {
            // Pindah ke Fragment Shipping (Screen 7)
            findNavController().navigate(R.id.action_orderFragment_to_shippingFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}