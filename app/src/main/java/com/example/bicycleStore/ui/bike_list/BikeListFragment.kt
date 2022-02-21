package com.example.bikestore.ui.bike_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycleStore.R
import com.example.bicycleStore.data.Bike
import com.google.android.material.transition.MaterialFade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class BikeListFragment : Fragment() {

    private lateinit var adapter: BikeListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: LiveData<List<Bike>>

    private val viewModel by viewModels<BikeListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = viewModel.getAllBikes()

        recyclerView = view.findViewById(R.id.list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        list.observe(viewLifecycleOwner) { it ->

            adapter = BikeListAdapter(it)
            adapter.onClick = {
                viewModel.addToCart(it.id)
                if (it.in_cart == 0) {
                    Toast.makeText(
                        context,
                        "Dodano " + it.name + " do koszyka",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        it.name + " jest już w koszyku",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            recyclerView.adapter = adapter
        }
    }

}