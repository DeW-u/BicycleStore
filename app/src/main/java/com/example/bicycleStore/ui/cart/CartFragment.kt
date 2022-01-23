package com.example.bikestore.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycleStore.R
import com.example.bicycleStore.data.Bike
import com.example.bicycleStore.databinding.CartFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {
    companion object {
        fun newInstance() = CartFragment()
    }

    private var _binding: CartFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: CartAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: LiveData<List<Bike>>
    private lateinit var totalPrice: LiveData<Int>

    private val viewModel by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CartFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTotalPriceTitle

        list = viewModel.getCart()
        totalPrice = viewModel.getTotalPrice()

        recyclerView = binding.cartRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viewLifecycleOwner.lifecycleScope.launch {
            list.observe(viewLifecycleOwner, { it ->

                adapter = CartAdapter(it, viewModel)

                recyclerView.adapter = adapter
            })
            totalPrice.observe(viewLifecycleOwner, {
                if (it == null || it == 0) {
                    binding.tvTotalPriceTitle.text = ("Twój koszyk jest pusty.")
                } else {
                    binding.tvTotalPriceTitle.text = ("Całkowita suma do zapłaty: $it PLN")
                }
            })
        }

        binding.btCartSubmit.setOnClickListener {
            viewModel.checkout()
            Toast.makeText(
                context,
                "Dziękujemy za zakupy!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}