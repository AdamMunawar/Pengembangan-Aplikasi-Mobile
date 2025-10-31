package lat.pam.myrecyclerviewdicoding

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import lat.pam.myrecyclerviewdicoding.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    private val orderViewModel: OrderViewModel by activityViewModels()

    // Deklarasikan SessionManager
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inisialisasi SessionManager
        sessionManager = SessionManager(requireContext())

        // 1. Setup Toolbar
        val toolbar: Toolbar = binding.mainToolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true) // Beri tahu fragment ini punya menu

        // 2. Ambil nama dan atur judul Toolbar
        val userName = sessionManager.getUserName()
        toolbar.title = "Halo, $userName" // Atur judul secara dinamis

        // 3. Setup RecyclerView
        rvHeroes = binding.rvHeroes
        rvHeroes.setHasFixedSize(true)

        list.clear()
        list.addAll(getListHeroes())
        showRecyclerList()

        return view
    }

    // 4. Menu Logic (List/Grid)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvHeroes.layoutManager = LinearLayoutManager(requireContext())
            }
            R.id.action_grid -> {
                rvHeroes.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 5. Fungsi Bantuan (getListHeroes)
    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1)
            )
            listHero.add(hero)
        }
        dataPhoto.recycle()
        return listHero
    }

    // 6. Fungsi Bantuan (showRecyclerList)
    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(requireContext())
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHeroAndOrder(data)
            }
        })
    }

    // 7. Logika Klik Item
    private fun showSelectedHeroAndOrder(hero: Hero) {
        orderViewModel.addItem(hero)
        Toast.makeText(requireContext(), "${hero.name} ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()

        // Pindah ke tab Order
        val bottomNavView: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav_view)
        bottomNavView?.selectedItemId = R.id.nav_order
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}