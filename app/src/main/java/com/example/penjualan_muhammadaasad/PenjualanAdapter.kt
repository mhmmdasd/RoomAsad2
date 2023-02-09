package com.example.penjualan_muhammadaasad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan_muhammadaasad.room.tbBarang
import kotlinx.android.synthetic.main.activity_adapter.view.*
import kotlinx.android.synthetic.main.activity_edit.view.*

class PenjualanAdapter (private val barang:ArrayList<tbBarang>,private val listener: OnAdapterListener)
    : RecyclerView.Adapter<PenjualanAdapter.BarangViewHolder>(){

        class BarangViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {

        return BarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter,parent,false)
        )

    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val bar = barang[position]
        holder.view.TNama.text = bar.nm_brg
        holder.view.imageEdit.setOnClickListener{listener.onUpdate(bar)}
        holder.view.imageDelete.setOnClickListener{listener.onDelete(bar)}
        holder.view.TNama.setOnClickListener{listener.onClick(bar)}

    }

    override fun getItemCount() = barang.size

    fun setData(list: List<tbBarang>){
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClick(tbBarang: tbBarang)
        fun onDelete(tbBarang: tbBarang)
        fun onUpdate(tbBarang: tbBarang)

    }
}

