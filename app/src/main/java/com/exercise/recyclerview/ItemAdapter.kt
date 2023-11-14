package com.exercise.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.recyclerview.databinding.ItemRecyclerviewBinding

class ItemAdapter(private var users: MutableList<Users>, val listener : OnItemClick)
    :RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = users[position]
        holder.bind(model)
    }

    inner class ItemViewHolder(private var binding: ItemRecyclerviewBinding):
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(model: Users){
            binding.tvName.text = model.name
            binding.tvSurName.text = model.surName
            binding.tvMail.text = model.email

            binding.btnUpdate.setOnClickListener {
                listener.onUpdate(model)
                notifyDataSetChanged()
            }

            binding.btnDelete.setOnClickListener {
                listener.onDelete(model)
                notifyDataSetChanged()
            }
        }
    }

}