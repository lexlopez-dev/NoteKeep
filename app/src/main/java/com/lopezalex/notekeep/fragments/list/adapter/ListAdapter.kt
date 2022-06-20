package com.lopezalex.notekeep.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lopezalex.notekeep.data.models.NoteData
import com.lopezalex.notekeep.databinding.RowLayoutBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<NoteData>()

    class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(noteData: NoteData) {
            binding.noteData = noteData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
         }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    fun setData(noteData: List<NoteData>) {
        val noteDiffUtil = NoteDiffUtil(dataList, noteData)
        val noteDiffResult = DiffUtil.calculateDiff(noteDiffUtil)
        this.dataList = noteData
        noteDiffResult.dispatchUpdatesTo(this)
    }
}