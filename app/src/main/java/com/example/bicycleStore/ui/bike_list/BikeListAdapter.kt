package com.example.bikestore.ui.bike_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bicycleStore.data.Bike
import com.example.bicycleStore.databinding.BikeRowBinding

class BikeListAdapter(private val list: List<Bike>, private val viewModel: BikeListViewModel):
    RecyclerView.Adapter<BikeListAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: BikeRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val tv_producer = binding.tvProducer
        val tv_name = binding.tvName
        val tv_price = binding.tvPrice
        val iv_bike = binding.ivBike
        val bt_cart = binding.btCart
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bind = BikeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_producer.text = list[position].producer
        holder.tv_name.text = list[position].name
        holder.tv_price.text = ("Cena: " + list[position].price.toString() + " PLN")
        Glide.with(context).load(list[position].image_url).into(holder.iv_bike)

        holder.bt_cart.setOnClickListener {
            viewModel.addToCart(list[position].id)
            if (list[position].in_cart == 0) {
                Toast.makeText(
                    context,
                    "Dodano " + list[position].name + " do koszyka",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    list[position].name + " jest już w koszyku",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}