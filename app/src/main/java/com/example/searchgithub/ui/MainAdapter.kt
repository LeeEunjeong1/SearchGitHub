package com.example.searchgithub.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchgithub.databinding.ItemGithubRepositoryBinding
import com.example.searchgithub.model.response.RepositoryModel

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>()  {

    private var gitHubList = ArrayList<RepositoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemGithubRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount() = gitHubList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        (holder as? MainViewHolder)?.onBind(gitHubList[position])
    }

    fun  clearList(){
        gitHubList.clear()
    }
    fun setList(avatar_url: String, full_name: String, language: String) {
        gitHubList.add(RepositoryModel(avatar_url,full_name,language))
        notifyDataSetChanged()

    }
//    fun moreList(avatar_url: String, full_name: String, language: String) {
//        repositoryList.add(RepositoryModel(avatar_url,full_name,language))
//        //   notifyDataSetChanged()
//        Log.d("tag",page.toString())
//        notifyItemRangeInserted(page*10,(page+1)*10)
//
//    }

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