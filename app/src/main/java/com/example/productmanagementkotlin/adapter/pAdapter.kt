package com.example.productmanagementkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productmanagementkotlin.R
import com.example.productmanagementkotlin.models.ProductModel

class pAdapter(private val empList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<pAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = empList[position]
        holder.tvpName.text = currentEmp.pName
        holder.tvpPrice.text = currentEmp.pPrice
        holder.tvpDescription.text = currentEmp.pDescription
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvpName : TextView = itemView.findViewById(R.id.tvProductName)
        val tvpPrice : TextView = itemView.findViewById(R.id.tvProductPrice)
        val tvpDescription : TextView = itemView.findViewById(R.id.tvProductDescription)
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}