package com.example.dog24.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dog24.R
import com.example.dog24.model.DogBreed
import com.example.dog24.util.getProgressDrawable
import com.example.dog24.util.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogListAdaptor(val dogList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogListAdaptor.DogViewHolder>() {

    fun updateDogList(newList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text = dogList[position].dogBreed
        holder.view.lifespan.text = dogList[position].lifeSpan
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
        }
        holder.view.imageView.loadImage(dogList[position].imageUrl, getProgressDrawable(holder.view.context ))
    }

    class DogViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}

