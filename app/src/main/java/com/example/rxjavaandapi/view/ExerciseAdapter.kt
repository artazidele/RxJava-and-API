package com.example.rxjavaandapi.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaandapi.R
import com.example.rxjavaandapi.model.Exercise
import io.reactivex.annotations.NonNull

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.AssetViewHolder>() {
    private var data: List<Exercise> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return AssetViewHolder(v)
    }

    override fun onBindViewHolder(@NonNull holder: AssetViewHolder, position: Int) {
        holder.updateAsset(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun bindViewModel(exercise: Exercise) {
        data += exercise
        notifyDataSetChanged()
    }

    class AssetViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val nameTextView: TextView
        private val view: View
        init {
            view = v
            nameTextView = v.findViewById(R.id.exercise_name)
        }

        fun updateAsset(exercise: Exercise) {
            nameTextView.text = exercise.name
        }

    }
}