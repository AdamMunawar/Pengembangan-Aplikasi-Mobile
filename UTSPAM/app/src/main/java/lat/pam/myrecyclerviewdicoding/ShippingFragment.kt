package lat.pam.myrecyclerviewdicoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import lat.pam.myrecyclerviewdicoding.databinding.FragmentShippingBinding

class ShippingFragment : Fragment() {

    private var _binding: FragmentShippingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShippingBinding.inflate(inflater, container, false)

        binding.btnOrderDanKirim.setOnClickListener {
            // Validasi input
            val nama = binding.etNama.text.toString()
            val alamat = binding.etAlamat.text.toString()

            if (nama.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(requireContext(), "Nama dan Alamat wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                // Pindah ke Fragment Thanks (Screen 8)
                findNavController().navigate(R.id.action_shippingFragment_to_thanksFragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}