package com.home.thirdlaba

import android.view.LayoutInflater
import com.bumptech.glide.Glide
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.thirdlaba.databinding.AdapterMovieBinding

class UsersAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var usersList= mutableListOf<Users>()
    fun setUsers(users: List<Users>){
        this.usersList=users.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       val users = usersList[position]
        holder.binding.name.text=users.name
        Glide.with(holder.itemView.context).load(users.imageUrl).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
      return usersList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}
