package com.example.searchgithub.ui


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatDrawableManager.preload
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchgithub.databinding.ItemGithubRepositoryBinding
import com.example.searchgithub.model.RepositoryModel
import com.example.searchgithub.ui.MainActivity.Companion.page
import kotlin.coroutines.coroutineContext

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>()  {

    private var repositoryList = ArrayList<RepositoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemGithubRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount() = repositoryList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        (holder as? MainViewHolder)?.onBind(repositoryList[position])
    }

    fun  clearList(){
        repositoryList.clear()
    }
    fun setList(avatar_url: String, full_name: String, language: String) {
        repositoryList.add(RepositoryModel(avatar_url,full_name,language))
        notifyDataSetChanged()

    }
    fun moreList(avatar_url: String, full_name: String, language: String) {
        repositoryList.add(RepositoryModel(avatar_url,full_name,language))
        //   notifyDataSetChanged()
        Log.d("tag",page.toString())
        if(page==0){
            notifyItemInserted(page)
        }else{
            notifyItemRangeInserted(page*10,(page+1)*10)
        }

    }

    class MainViewHolder(private val binding: ItemGithubRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: RepositoryModel) {
            with(binding){
                language.text = item.language
                fullName.text = item.full_name
                Glide.with(this@MainViewHolder.itemView.context)
                    .load(item.avatar_url)
                    .thumbnail(0.1f)
                    .into(avatarUrl)

            }
        }
    }
}