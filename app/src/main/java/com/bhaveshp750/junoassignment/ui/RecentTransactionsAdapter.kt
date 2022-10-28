package com.bhaveshp750.junoassignment.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhaveshp750.junoassignment.R
import com.bhaveshp750.junoassignment.common.doesEndWithNumber
import com.bhaveshp750.junoassignment.common.loadImage
import com.bhaveshp750.junoassignment.modal.Transaction

class RecentTransactionsAdapter(
    private val itemsList: ArrayList<Transaction>
): RecyclerView.Adapter<RecentTransactionsAdapter.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.icon.visibility = View.GONE
        holder.buttonLayout.visibility = View.GONE
        holder.subtitle2.setTextColor(Color.parseColor("#BEBEBE"))

        holder.image.loadImage(itemsList[position].txnLogo)
        holder.title.text = itemsList[position].title
        holder.subtitle.text = itemsList[position].txnTime
        holder.title2.text = "$"+itemsList[position].txnAmount
        itemsList[position].txnSubAmount.let {
            if(it.doesEndWithNumber())
                holder.subtitle2.text = it
            else
                holder.subtitle2.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    // update new dataset
    fun updateList(allTxn: List<Transaction>) {
        itemsList.clear()
        itemsList.addAll(allTxn)
        notifyDataSetChanged()
    }

    // on click on Buy Button, add transaction for respective crypto in runtime.
    fun addTxn(txn: Transaction) {
        itemsList.add(txn)
        notifyItemChanged(itemsList.size)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val icon: ImageView = view.findViewById(R.id.icon)
        val title: TextView = view.findViewById(R.id.title)
        val subtitle: TextView = view.findViewById(R.id.subtitle)
        val buttonLayout: LinearLayout = view.findViewById(R.id.button_layout)
        val title2: TextView = view.findViewById(R.id.title2)
        val subtitle2: TextView = view.findViewById(R.id.subtitle2)
    }
}
