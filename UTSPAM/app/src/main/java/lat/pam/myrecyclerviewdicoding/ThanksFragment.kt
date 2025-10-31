package lat.pam.myrecyclerviewdicoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import lat.pam.myrecyclerviewdicoding.databinding.FragmentThanksBinding

class ThanksFragment : Fragment() {

    private var _binding: FragmentThanksBinding? = null
    private val binding get() = _binding!!

    // Ambil ViewModel untuk membersihkan keranjang
    private val orderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThanksBinding.inflate(inflater, container, false)

        // Bersihkan keranjang saat masuk ke layar ini
        orderViewModel.clearCart()

        binding.btnSelesai.setOnClickListener {
            // Kembali ke Home
            findNavController().navigate(R.id.action_thanksFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}