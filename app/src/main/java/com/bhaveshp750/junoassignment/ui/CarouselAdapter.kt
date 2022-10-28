
package com.bhaveshp750.junoassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bhaveshp750.junoassignment.R
import com.bhaveshp750.junoassignment.common.loadImage
import com.bhaveshp750.junoassignment.modal.CryptoPrice

class CarouselAdapter(
    private val itemsList: ArrayList<CryptoPrice>
): RecyclerView.Adapter<CarouselAdapter.MyCarouselViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCarouselViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return MyCarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyCarouselViewHolder, position: Int) {
        holder.image.loadImage(itemsList[position].logo)
        holder.title.text = itemsList[position].title
        holder.price.text = "$"+itemsList[position].currentPriceInUSD
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun updateList(yourHolding: List<CryptoPrice>) {
        itemsList.clear()
        itemsList.addAll(yourHolding)
        notifyDataSetChanged()
    }

    class MyCarouselViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val price: TextView = view.findViewById(R.id.price)
//        val depositButton: Button = view.findViewById(R.id.deposit_button)
//        val buyButton: Button = view.findViewById(R.id.buy_button)
    }
}