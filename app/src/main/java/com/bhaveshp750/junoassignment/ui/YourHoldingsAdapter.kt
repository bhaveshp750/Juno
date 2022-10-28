package com.bhaveshp750.junoassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bhaveshp750.junoassignment.R
import com.bhaveshp750.junoassignment.common.loadImage
import com.bhaveshp750.junoassignment.modal.YourCryptoHolding

class YourHoldingsAdapter(
    private val itemsList: ArrayList<YourCryptoHolding>,
    // 0: Empty State, 1: Value State
    private var state: Int,
    private val listener: OnButtonClickListener
): RecyclerView.Adapter<YourHoldingsAdapter.MyViewHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(state == 0){
            holder.buttonLayout.visibility = View.VISIBLE
            holder.priceLayout.visibility = View.GONE
        } else {
            holder.buttonLayout.visibility = View.GONE
            holder.priceLayout.visibility = View.VISIBLE
        }
        holder.image.loadImage(itemsList[position].logo)
        holder.title.text = itemsList[position].title
        holder.subtitle.text = itemsList[position].currentBalanceInToken
        holder.title2.text = "$${itemsList[position].currentBalanceInUSD}"
        holder.subtitle2.text = "$${itemsList[position].currentBalanceInUSD}"
        holder.depositButton.setOnClickListener {
            listener.onDepositClicked(itemsList[position].title)
        }
        holder.buyButton.setOnClickListener {
            listener.onBuyClicked(itemsList[holder.adapterPosition].title)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun updateList(yourHolding: List<YourCryptoHolding>, state: Int) {
        this.state = state
        itemsList.clear()
        itemsList.addAll(yourHolding)
        notifyDataSetChanged()
    }

    interface OnButtonClickListener {
        fun onDepositClicked(cryptoName: String)
        fun onBuyClicked(cryptoName: String)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val subtitle: TextView = view.findViewById(R.id.subtitle)
        val buttonLayout: LinearLayout = view.findViewById(R.id.button_layout)
        val depositButton: Button = view.findViewById(R.id.deposit_button)
        val buyButton: Button = view.findViewById(R.id.buy_button)
        val priceLayout: ConstraintLayout = view.findViewById(R.id.price_layout)
        val title2: TextView = view.findViewById(R.id.title2)
        val subtitle2: TextView = view.findViewById(R.id.subtitle2)
    }
}
